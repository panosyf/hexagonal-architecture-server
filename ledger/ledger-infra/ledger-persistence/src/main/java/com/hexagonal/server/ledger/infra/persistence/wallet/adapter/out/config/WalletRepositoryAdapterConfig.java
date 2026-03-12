package com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out.config;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out.WalletJpaRepository;
import com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out.WalletRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletRepositoryAdapterConfig {

    private final WalletJpaRepository walletJpaRepository;

    public WalletRepositoryAdapterConfig(WalletJpaRepository walletJpaRepository) {
        this.walletJpaRepository = walletJpaRepository;
    }

    @Bean("walletRepositoryPort")
    public WalletRepositoryPort walletRepositoryPort() {
        return new WalletRepositoryAdapter(walletJpaRepository);
    }

}

