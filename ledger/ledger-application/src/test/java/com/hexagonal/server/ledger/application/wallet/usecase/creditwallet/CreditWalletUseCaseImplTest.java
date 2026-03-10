package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.IdempotencyRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreditWalletUseCaseImplTest {

    private final WalletDomainService walletDomainService = mock(WalletDomainService.class);
    private final WalletRepositoryPort walletRepositoryPort = mock(WalletRepositoryPort.class);
    private final IdempotencyRepositoryPort idempotencyRepositoryPort = mock(IdempotencyRepositoryPort.class);

    private CreditWalletUseCaseImpl creditWalletUseCase;

    @BeforeEach
    void setUp() {
        creditWalletUseCase = new CreditWalletUseCaseImpl(
                walletDomainService,
                walletRepositoryPort,
                idempotencyRepositoryPort);
    }

    @Test
    void should_credit_wallet() {
        // given
        Id walletId = Id.generate();
        Id idempotencyKey = Id.generate();
        Wallet wallet = Wallet.create(Id.generate());
        CreditWalletRequest request = new CreditWalletRequest(
                walletId.getValue(),
                BigDecimal.valueOf(100),
                "deposit",
                idempotencyKey.getValue());
        LedgerEntry ledgerEntry = LedgerEntry.credit(walletId, Money.of(BigDecimal.valueOf(100)), "deposit");
        when(idempotencyRepositoryPort.exists(any())).thenReturn(false);
        when(walletRepositoryPort.findById(any())).thenReturn(wallet);
        when(walletDomainService.credit(any())).thenReturn(ledgerEntry);
        // when
        CreditWalletResponse response = creditWalletUseCase.creditWallet(request);
        // then
        verify(idempotencyRepositoryPort).exists(any());
        verify(walletRepositoryPort).findById(any());
        verify(walletDomainService).credit(any());
        verify(walletRepositoryPort).save(wallet);
        verify(idempotencyRepositoryPort).store(any());
        assertThat(response).isNotNull();
        assertThat(response.ledgerEntryId()).isEqualTo(ledgerEntry.getId().getValue());
    }

    @Test
    void should_throw_if_duplicate_credit_request() {
        // given
        Id walletId = Id.generate();
        Id idempotencyKey = Id.generate();
        CreditWalletRequest request = new CreditWalletRequest(
                walletId.getValue(),
                BigDecimal.valueOf(100),
                "deposit",
                idempotencyKey.getValue());
        when(idempotencyRepositoryPort.exists(any())).thenReturn(true);
        // when / then
        assertThatThrownBy(() -> creditWalletUseCase.creditWallet(request))
                .isInstanceOf(IllegalStateException.class);
        verify(walletRepositoryPort, never()).save(any());
        verify(walletDomainService, never()).credit(any());
    }

}

