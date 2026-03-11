package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreditOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreditWalletUseCaseImplTest {

    private final WalletDomainService walletDomainService = mock(WalletDomainService.class);
    private final WalletRepositoryPort walletRepositoryPort = mock(WalletRepositoryPort.class);

    private CreditWalletUseCaseImpl creditWalletUseCase;
    private final ArgumentCaptor<CreditOperation> creditOperationCaptor = ArgumentCaptor.forClass(CreditOperation.class);

    @BeforeEach
    void setUp() {
        creditWalletUseCase = new CreditWalletUseCaseImpl(walletDomainService, walletRepositoryPort);
    }

    @Test
    void should_credit_wallet() {
        // given
        Id walletId = Id.generate();
        Wallet wallet = Wallet.create(Id.generate());
        CreditWalletRequest request = new CreditWalletRequest(BigDecimal.valueOf(50), "credit-ref");
        LedgerEntry ledgerEntry = wallet.credit(Money.of(BigDecimal.valueOf(50)), Description.valueOf("credit-ref"));
        when(walletRepositoryPort.findById(walletId)).thenReturn(wallet);
        when(walletDomainService.credit(any())).thenReturn(ledgerEntry);
        // when
        CreditWalletResponse response = creditWalletUseCase.creditWallet(walletId.getValue(), request);
        // then
        verify(walletRepositoryPort).findById(walletId);
        verify(walletDomainService).credit(creditOperationCaptor.capture());
        verify(walletRepositoryPort).save(wallet);
        CreditOperation creditOperation = creditOperationCaptor.getValue();
        assertAll(
                () -> assertEquals(wallet, creditOperation.wallet()),
                () -> assertEquals(request.amount().setScale(2, RoundingMode.HALF_EVEN), creditOperation.amount().getValue()),
                () -> assertEquals(request.reference(), creditOperation.reference().getValue()),
                () -> assertEquals(ledgerEntry.getId().getValue(), response.ledgerEntryId())
        );
    }
}


