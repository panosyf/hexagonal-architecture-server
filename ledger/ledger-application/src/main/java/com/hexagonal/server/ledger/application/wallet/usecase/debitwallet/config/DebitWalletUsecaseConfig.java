package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.config;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.LedgerEntryRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.DebitWalletUseCase;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.DebitWalletUseCaseImpl;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebitWalletUsecaseConfig {

    private final WalletDomainService walletDomainService;
    private final WalletRepositoryPort walletRepositoryPort;
    private final LedgerEntryRepositoryPort ledgerEntryRepositoryPort;

    public DebitWalletUsecaseConfig(
            WalletDomainService walletDomainService,
            WalletRepositoryPort walletRepositoryPort,
            LedgerEntryRepositoryPort ledgerEntryRepositoryPort) {
        this.walletDomainService = walletDomainService;
        this.walletRepositoryPort = walletRepositoryPort;
        this.ledgerEntryRepositoryPort = ledgerEntryRepositoryPort;
    }

    @Bean("debitWalletUseCase")
    public DebitWalletUseCase debitWalletUseCase() {
        return new DebitWalletUseCaseImpl(walletDomainService, walletRepositoryPort, ledgerEntryRepositoryPort);
    }

}
