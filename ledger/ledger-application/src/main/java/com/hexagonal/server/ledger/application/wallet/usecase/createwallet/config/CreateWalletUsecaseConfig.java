package com.hexagonal.server.ledger.application.wallet.usecase.createwallet.config;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.CreateWalletUseCase;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.CreateWalletUseCaseImpl;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateWalletUsecaseConfig {

    private final WalletDomainService walletDomainService;
    private final WalletRepositoryPort walletRepositoryPort;

    public CreateWalletUsecaseConfig(
            WalletDomainService walletDomainService,
            WalletRepositoryPort walletRepositoryPort) {
        this.walletDomainService = walletDomainService;
        this.walletRepositoryPort = walletRepositoryPort;
    }

    @Bean("createWalletUseCase")
    public CreateWalletUseCase createWalletUseCase() {
        return new CreateWalletUseCaseImpl(walletDomainService, walletRepositoryPort);
    }

}
