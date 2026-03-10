package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request.DebitWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response.DebitWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.DebitOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
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

class DebitWalletUseCaseImplTest {

    private final WalletDomainService walletDomainService = mock(WalletDomainService.class);
    private final WalletRepositoryPort walletRepositoryPort = mock(WalletRepositoryPort.class);

    private DebitWalletUseCaseImpl debitWalletUseCase;
    private final ArgumentCaptor<DebitOperation> debitOperationCaptor = ArgumentCaptor.forClass(DebitOperation.class);

    @BeforeEach
    void setUp() {
        debitWalletUseCase = new DebitWalletUseCaseImpl(walletDomainService, walletRepositoryPort);
    }

    @Test
    void should_debit_wallet() {
        // given
        Id walletId = Id.generate();
        Wallet wallet = Wallet.create(Id.generate());
        wallet.credit(Money.of(BigDecimal.valueOf(100)), "initial");
        DebitWalletRequest debitWalletRequest = new DebitWalletRequest(BigDecimal.valueOf(50), "payment");
        LedgerEntry ledgerEntry = wallet.debit(Money.of(BigDecimal.valueOf(50)), "payment");
        when(walletRepositoryPort.findById(walletId)).thenReturn(wallet);
        when(walletDomainService.debit(any())).thenReturn(ledgerEntry);
        // when
        DebitWalletResponse response = debitWalletUseCase.debitWallet(walletId.getValue(), debitWalletRequest);
        // then
        verify(walletRepositoryPort).findById(walletId);
        verify(walletDomainService).debit(debitOperationCaptor.capture());
        verify(walletRepositoryPort).save(wallet);
        DebitOperation debitOperation = debitOperationCaptor.getValue();
        assertAll(
                () -> assertEquals(wallet, debitOperation.wallet()),
                () -> assertEquals(debitWalletRequest.amount().setScale(2, RoundingMode.HALF_EVEN), debitOperation.amount().getValue()),
                () -> assertEquals(debitWalletRequest.reference(), debitOperation.reference()),
                () -> assertEquals(ledgerEntry.getId().getValue(), response.ledgerEntryId())
        );
    }
}


