package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.config;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.IdempotencyRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.CreditWalletUseCase;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.CreditWalletUseCaseImpl;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditWalletUsecaseConfig {

    private final WalletDomainService walletDomainService;
    private final WalletRepositoryPort walletRepositoryPort;
    private final IdempotencyRepositoryPort idempotencyRepositoryPort;

    public CreditWalletUsecaseConfig(
            WalletDomainService walletDomainService,
            WalletRepositoryPort walletRepositoryPort,
            IdempotencyRepositoryPort idempotencyRepositoryPort) {
        this.walletDomainService = walletDomainService;
        this.walletRepositoryPort = walletRepositoryPort;
        this.idempotencyRepositoryPort = idempotencyRepositoryPort;
    }

    @Bean("creditWalletUseCase")
    public CreditWalletUseCase creditWalletUseCase() {
        return new CreditWalletUseCaseImpl(walletDomainService, walletRepositoryPort, idempotencyRepositoryPort);
    }

}
