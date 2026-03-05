package com.hexagonal.server.identity.application.usecase.account.config;

import com.hexagonal.server.identity.application.usecase.account.AccountUsecase;
import com.hexagonal.server.identity.application.usecase.account.AccountUsecaseImpl;
import com.hexagonal.server.identity.core.service.account.AccountDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class AccountUsecaseConfig {

    private final AccountDomainService accountDomainService;
    private final ConversionService conversionService;

    public AccountUsecaseConfig(AccountDomainService accountDomainService, ConversionService conversionService) {
        this.accountDomainService = accountDomainService;
        this.conversionService = conversionService;
    }

    @Bean
    public AccountUsecase accountApi() {
        return new AccountUsecaseImpl(accountDomainService, conversionService);
    }

}
