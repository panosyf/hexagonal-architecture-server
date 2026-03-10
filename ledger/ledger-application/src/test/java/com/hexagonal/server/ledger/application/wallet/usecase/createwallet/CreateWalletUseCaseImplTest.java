package com.hexagonal.server.ledger.application.wallet.usecase.createwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


class CreateWalletUseCaseImplTest {

    private final WalletDomainService walletDomainService = mock(WalletDomainService.class);
    private final WalletRepositoryPort walletRepositoryPort = mock(WalletRepositoryPort.class);
    private CreateWalletUseCaseImpl createWalletUseCase;

    @BeforeEach
    void setUp() {
        createWalletUseCase = new CreateWalletUseCaseImpl(walletDomainService, walletRepositoryPort);
    }

    @Test
    void should_create_wallet() {
        // given
        Id accountId = Id.generate();
        CreateWalletRequest request = new CreateWalletRequest(accountId.getValue());
        Wallet wallet = Wallet.create(accountId);
        when(walletDomainService.createWallet(any())).thenReturn(wallet);
        // when
        CreateWalletResponse response = createWalletUseCase.createWallet(request);
        // then
        verify(walletDomainService).createWallet(any());
        verify(walletRepositoryPort).save(wallet);
        assertThat(response).isNotNull();
        assertThat(response.walletId()).isEqualTo(wallet.getId().getValue());
    }

}

