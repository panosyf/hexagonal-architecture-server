package com.hexagonal.server.identity.infra.persistence.account.adapter.out.config;

import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.infra.persistence.account.adapter.out.AccountJpaRepository;
import com.hexagonal.server.identity.infra.persistence.account.adapter.out.AccountRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class AccountRepositoryAdapterConfig {

    private final AccountJpaRepository accountJpaRepository;
    private final ConversionService conversionService;

    public AccountRepositoryAdapterConfig(
            AccountJpaRepository accountJpaRepository,
            ConversionService conversionService) {
        this.accountJpaRepository = accountJpaRepository;
        this.conversionService = conversionService;
    }

    @Bean
    public AccountRepositoryPort accountRepositoryPort() {
        return new AccountRepositoryAdapter(accountJpaRepository, conversionService);
    }

}
