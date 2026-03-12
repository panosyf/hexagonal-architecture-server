package com.hexagonal.server.ledger.flow;

import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request.DebitWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response.DebitWalletResponse;
import com.hexagonal.server.ledger.common.constant.Endpoint;
import com.hexagonal.server.ledger.config.BaseLedgerIntegrationTest;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class WalletIntegrationTest extends BaseLedgerIntegrationTest {

    @Test
    void walletCreationCreditAndDebitTest() {
        // init create wallet request
        Id accountId = Id.generate();
        CreateWalletRequest createWalletRequest = new CreateWalletRequest(accountId.getValue());
        // create wallet invocation
        CreateWalletResponse createWalletResponse = requestTestClient
                .post(Endpoint.CREATE_WALLET, createWalletRequest)
                .expectStatus().isCreated()
                .expectBody(CreateWalletResponse.class)
                .returnResult()
                .getResponseBody();
        // wallet created
        assertThat(walletRepositoryPort.findTotalEntries()).isEqualTo(1);
        assertThat(createWalletResponse).isNotNull();
        String walletId = createWalletResponse.walletId();
        assertThat(walletId).isNotNull();
        // init credit wallet request
        BigDecimal creditAmount = BigDecimal.valueOf(80);
        CreditWalletRequest creditWalletRequest = new CreditWalletRequest(creditAmount, "credit");
        // debit wallet
        CreditWalletResponse creditWalletResponse = requestTestClient.post(
                        Endpoint.CREDIT_WALLET.replace("{id}", walletId), creditWalletRequest)
                .expectStatus().isOk()
                .expectBody(CreditWalletResponse.class)
                .returnResult()
                .getResponseBody();
        // wallet credited
        assertThat(creditWalletResponse).isNotNull();
        assertThat(creditWalletResponse.ledgerEntryId()).isNotNull();
        assertThat(creditWalletResponse.walletId()).isEqualTo(walletId);
        assertThat(creditWalletResponse.amount()).isEqualTo(creditAmount.setScale(2, RoundingMode.HALF_EVEN));
        // init debit wallet request
        DebitWalletRequest debitWalletRequest = new DebitWalletRequest(BigDecimal.valueOf(50), "payment");
        // debit wallet
        DebitWalletResponse debitResponse = requestTestClient.post(
                        Endpoint.DEBIT_WALLET.replace("{id}", walletId), debitWalletRequest)
                .expectStatus().isOk()
                .expectBody(DebitWalletResponse.class)
                .returnResult()
                .getResponseBody();

        // wallet debited
        assertThat(debitResponse).isNotNull();
        assertThat(debitResponse.ledgerEntryId()).isNotNull();
        assertThat(debitResponse.walletId()).isEqualTo(walletId);
        assertThat(debitResponse.amount()).isEqualTo(BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_EVEN));
    }

}

