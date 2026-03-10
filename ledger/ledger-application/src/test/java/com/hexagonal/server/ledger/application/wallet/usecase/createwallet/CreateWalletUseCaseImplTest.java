package com.hexagonal.server.ledger.application.wallet.usecase.createwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreateWalletOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CreateWalletUseCaseImplTest {

    private final WalletDomainService walletDomainService = mock(WalletDomainService.class);
    private final WalletRepositoryPort walletRepositoryPort = mock(WalletRepositoryPort.class);
    private CreateWalletUseCaseImpl createWalletUseCase;
    private final ArgumentCaptor<CreateWalletOperation> createWalletOperationCaptor = ArgumentCaptor.forClass(CreateWalletOperation.class);

    @BeforeEach
    void setUp() {
        createWalletUseCase = new CreateWalletUseCaseImpl(walletDomainService, walletRepositoryPort);
    }

    @Test
    void should_create_wallet() {
        // given
        Id accountId = Id.generate();
        CreateWalletRequest createWalletRequest = new CreateWalletRequest(accountId.getValue());
        Wallet wallet = Wallet.create(accountId);
        when(walletDomainService.createWallet(any())).thenReturn(wallet);
        // when
        CreateWalletResponse response = createWalletUseCase.createWallet(createWalletRequest);
        // then
        verify(walletDomainService).createWallet(createWalletOperationCaptor.capture());
        verify(walletRepositoryPort).save(wallet);
        CreateWalletOperation operation = createWalletOperationCaptor.getValue();
        assertAll(
                () -> assertEquals(accountId, operation.accountId()),
                () -> assertEquals(wallet.getId().getValue(), response.walletId())
        );
    }
}


