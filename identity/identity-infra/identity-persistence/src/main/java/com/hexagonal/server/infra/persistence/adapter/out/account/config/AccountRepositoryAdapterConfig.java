package com.hexagonal.server.infra.persistence.adapter.out.account.config;

import com.hexagonal.server.account.core.port.out.account.AccountRepositoryPort;
import com.hexagonal.server.infra.persistence.adapter.out.account.AccountJpaRepository;
import com.hexagonal.server.infra.persistence.adapter.out.account.AccountRepositoryAdapter;
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
