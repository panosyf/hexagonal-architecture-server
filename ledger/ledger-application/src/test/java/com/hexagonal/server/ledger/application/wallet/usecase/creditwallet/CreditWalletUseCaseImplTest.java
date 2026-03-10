package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet;

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
    private CreditWalletUseCaseImpl creditWalletUseCase;

    @BeforeEach
    void setUp() {
        creditWalletUseCase = new CreditWalletUseCaseImpl(
                walletDomainService,
                walletRepositoryPort);
    }

    @Test
    void should_credit_wallet() {
        // given
        Id walletId = Id.generate();
        Wallet wallet = Wallet.create(Id.generate());
        CreditWalletRequest request = new CreditWalletRequest(
                BigDecimal.valueOf(100),
                "deposit");
        LedgerEntry ledgerEntry = wallet.credit(Money.of(BigDecimal.valueOf(100)), "deposit");
        when(walletRepositoryPort.findById(any())).thenReturn(wallet);
        when(walletDomainService.credit(any())).thenReturn(ledgerEntry);
        // when
        CreditWalletResponse response = creditWalletUseCase.creditWallet(walletId.getValue(), request);
        // then
        verify(walletRepositoryPort).findById(any());
        verify(walletDomainService).credit(any());
        verify(walletRepositoryPort).save(wallet);
        assertThat(response).isNotNull();
        assertThat(response.ledgerEntryId()).isEqualTo(ledgerEntry.getId().getValue());
    }

}

