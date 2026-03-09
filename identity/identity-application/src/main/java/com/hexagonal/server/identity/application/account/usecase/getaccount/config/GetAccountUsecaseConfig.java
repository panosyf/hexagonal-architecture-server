package com.hexagonal.server.identity.application.account.usecase.getaccount.config;

import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.application.account.usecase.getaccount.GetAccountUsecase;
import com.hexagonal.server.identity.application.account.usecase.getaccount.GetAccountUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetAccountUsecaseConfig {

    private final AccountRepositoryPort accountRepositoryPort;

    public GetAccountUsecaseConfig(
            AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Bean
    public GetAccountUsecase getAccountUsecase() {
        return new GetAccountUsecaseImpl(accountRepositoryPort);
    }

}
