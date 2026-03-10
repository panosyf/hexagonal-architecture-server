package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request.DebitWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response.DebitWalletResponse;
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

class DebitWalletUseCaseImplTest {

    private final WalletDomainService walletDomainService = mock(WalletDomainService.class);
    private final WalletRepositoryPort walletRepositoryPort = mock(WalletRepositoryPort.class);

    private DebitWalletUseCaseImpl debitWalletUseCase;

    @BeforeEach
    void setUp() {
        debitWalletUseCase = new DebitWalletUseCaseImpl(
                walletDomainService,
                walletRepositoryPort);
    }

    @Test
    void should_debit_wallet() {
        // given
        Id walletId = Id.generate();
        Wallet wallet = Wallet.create(Id.generate());
        DebitWalletRequest request = new DebitWalletRequest(
                walletId.getValue(),
                BigDecimal.valueOf(50),
                "payment");
        wallet.credit(Money.of(BigDecimal.valueOf(100)), "initial balance");
        LedgerEntry ledgerEntry = wallet.debit(Money.of(BigDecimal.valueOf(50)), "payment");
        when(walletRepositoryPort.findById(any())).thenReturn(wallet);
        when(walletDomainService.debit(any())).thenReturn(ledgerEntry);
        // when
        DebitWalletResponse response = debitWalletUseCase.debitWallet(request);
        // then
        verify(walletRepositoryPort).findById(any());
        verify(walletDomainService).debit(any());
        verify(walletRepositoryPort).save(wallet);
        assertThat(response).isNotNull();
        assertThat(response.ledgerEntryId()).isEqualTo(ledgerEntry.getId().getValue());
    }

}

