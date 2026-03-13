package com.hexagonal.server.identity.infra.persistence.account.adapter.out.config;

import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.infra.persistence.account.adapter.out.AccountJpaRepository;
import com.hexagonal.server.identity.infra.persistence.account.adapter.out.AccountRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountRepositoryAdapterConfig {

    private final AccountJpaRepository accountJpaRepository;

    public AccountRepositoryAdapterConfig(
            AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    @Bean("accountRepositoryPort")
    public AccountRepositoryPort accountRepositoryPort() {
        return new AccountRepositoryAdapter(accountJpaRepository);
    }

}
