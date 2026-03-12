package com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out.config;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.LedgerEntryRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out.LedgerEntryJpaRepository;
import com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out.LedgerEntryRepositoryAdapter;
import com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out.WalletJpaRepository;
import com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out.WalletRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LedgerEntryRepositoryAdapterConfig {

    private final LedgerEntryJpaRepository ledgerEntryJpaRepository;

    public LedgerEntryRepositoryAdapterConfig(LedgerEntryJpaRepository ledgerEntryJpaRepository) {
        this.ledgerEntryJpaRepository = ledgerEntryJpaRepository;
    }

    @Bean("ledgerEntryRepositoryPort")
    public LedgerEntryRepositoryPort ledgerEntryRepositoryPort() {
        return new LedgerEntryRepositoryAdapter(ledgerEntryJpaRepository);
    }

}

