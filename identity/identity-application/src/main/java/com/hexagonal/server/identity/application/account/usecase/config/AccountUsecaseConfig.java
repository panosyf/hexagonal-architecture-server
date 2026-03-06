package com.hexagonal.server.identity.application.account.usecase.config;

import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.application.account.usecase.AccountUsecase;
import com.hexagonal.server.identity.application.account.usecase.AccountUsecaseImpl;
import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class AccountUsecaseConfig {

    private final AccountDomainService accountDomainService;
    private final AccountRepositoryPort accountRepositoryPort;
    private final ConversionService conversionService;

    public AccountUsecaseConfig(
            AccountDomainService accountDomainService,
            AccountRepositoryPort accountRepositoryPort,
            ConversionService conversionService) {
        this.accountDomainService = accountDomainService;
        this.accountRepositoryPort = accountRepositoryPort;
        this.conversionService = conversionService;
    }

    @Bean
    public AccountUsecase accountApi() {
        return new AccountUsecaseImpl(accountDomainService, accountRepositoryPort, conversionService);
    }

}
