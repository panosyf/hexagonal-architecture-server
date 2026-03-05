package com.hexagonal.server.infra.spec.transaction;

import com.hexagonal.server.account.application.model.request.transaction.TransactionCreateRequest;
import com.hexagonal.server.account.application.model.request.transaction.TransactionUpdateRequest;
import com.hexagonal.server.account.application.model.response.transaction.TransactionCreationResponse;
import com.hexagonal.server.account.application.model.response.transaction.TransactionUpdateResponse;
import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.infra.common.constant.transaction.Endpoint;
import com.hexagonal.server.infra.common.mock.account.AccountMock;
import com.hexagonal.server.infra.common.mock.transaction.TransactionCreateRequestMock;
import com.hexagonal.server.infra.config.AppIntegrationTest;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum.COMPLETED;
import static com.hexagonal.server.infra.common.mock.transaction.TransactionUpdateRequestMock.generateTransactionUpdateRequest;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionIntegrationTest extends AppIntegrationTest {

    private static final Money BALANCE_5 = Money.of(BigDecimal.valueOf(5));
    private static final Money BALANCE_10 = Money.of(BigDecimal.TEN);
    private static final Money BALANCE_15 = Money.of(BigDecimal.valueOf(15));
    private static final Money BALANCE_20 = Money.of(BigDecimal.valueOf(20));

    @Test
    void userCreatesNewTransactionThenItsCompleted() {
        // given
        Account accountDebtor = AccountMock.generateAccount(BALANCE_20);
        Account accountBeneficiary = AccountMock.generateAccount(BALANCE_5);
        accountRepositoryPort.save(accountDebtor);
        accountRepositoryPort.save(accountBeneficiary);
        TransactionCreateRequest transactionCreateRequest = TransactionCreateRequestMock
                .generateTransactionCreateRequest(
                        accountDebtor.getId().getValue(),
                        accountBeneficiary.getId().getValue());
        // when
        TransactionCreationResponse transactionCreationResponse = requestTestClient.post(Endpoint.CREATE_TRANSACTION, transactionCreateRequest)
                .expectStatus().isCreated()
                .expectBody(TransactionCreationResponse.class)
                .returnResult()
                .getResponseBody();
        //then
        assertThat(transactionCreationResponse.status()).isEqualTo(TransactionStatusEnum.PENDING);
        Account account = accountRepositoryPort.findById(accountDebtor.getId());
        assertThat(account.getBalance()).isEqualTo(BALANCE_15);
        // when
        TransactionUpdateRequest transactionUpdateRequest = generateTransactionUpdateRequest(COMPLETED);
        requestTestClient.put(
                        Endpoint.UPDATE_TRANSACTION.replace("{id}", transactionCreationResponse.id()),
                        transactionUpdateRequest)
                .expectStatus().isOk()
                .expectBody(TransactionUpdateResponse.class)
                .returnResult()
                .equals(new TransactionUpdateResponse(transactionCreationResponse.id(), TransactionStatusEnum.COMPLETED));
        Account beneficiaryaccount = accountRepositoryPort.findById(accountBeneficiary.getId());
        assertThat(beneficiaryaccount.getBalance()).isEqualTo(BALANCE_10);
    }

}
