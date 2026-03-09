package com.hexagonal.server.identity.application.account.usecase.createaccount.config;

import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.application.account.usecase.createaccount.CreateAccountUsecase;
import com.hexagonal.server.identity.application.account.usecase.createaccount.CreateAccountUsecaseImpl;
import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class CreateAccountUsecaseConfig {

    private final AccountDomainService accountDomainService;
    private final AccountRepositoryPort accountRepositoryPort;

    public CreateAccountUsecaseConfig(
            AccountDomainService accountDomainService,
            AccountRepositoryPort accountRepositoryPort) {
        this.accountDomainService = accountDomainService;
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Bean("createAccountUsecase")
    public CreateAccountUsecase createAccountUsecase() {
        return new CreateAccountUsecaseImpl(accountDomainService, accountRepositoryPort);
    }

}
