package com.hexagonal.server.identity.application.account.usecase.getaccount.config;

import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.application.account.usecase.getaccount.GetAccountUsecase;
import com.hexagonal.server.identity.application.account.usecase.getaccount.GetAccountUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class GetAccountUsecaseConfig {

    private final AccountRepositoryPort accountRepositoryPort;
    private final ConversionService conversionService;

    public GetAccountUsecaseConfig(
            AccountRepositoryPort accountRepositoryPort,
            ConversionService conversionService) {
        this.accountRepositoryPort = accountRepositoryPort;
        this.conversionService = conversionService;
    }

    @Bean
    public GetAccountUsecase getAccountUsecase() {
        return new GetAccountUsecaseImpl(accountRepositoryPort, conversionService);
    }

}
