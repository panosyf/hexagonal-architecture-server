package com.hexagonal.server.ledger.application.wallet.usecase.createwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.mapper.CreateWalletMapper;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreateWalletOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import org.springframework.beans.factory.annotation.Qualifier;

public class CreateWalletUseCaseImpl implements CreateWalletUseCase {

    private final WalletDomainService walletDomainService;
    private final WalletRepositoryPort walletRepositoryPort;

    public CreateWalletUseCaseImpl(
            @Qualifier("walletDomainService") WalletDomainService walletDomainService,
            WalletRepositoryPort walletRepositoryPort) {
        this.walletDomainService = walletDomainService;
        this.walletRepositoryPort = walletRepositoryPort;
    }

    @Override
    public CreateWalletResponse createWallet(CreateWalletRequest createWalletRequest) {
        CreateWalletOperation createWalletOperation = CreateWalletMapper.toCreateWalletOperation(createWalletRequest);
        Wallet wallet = walletDomainService.createWallet(createWalletOperation);
        walletRepositoryPort.save(wallet);
        return CreateWalletMapper.toCreateWalletResponse(wallet);
    }

}

