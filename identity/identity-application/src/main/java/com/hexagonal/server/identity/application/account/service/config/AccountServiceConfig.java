package com.hexagonal.server.identity.application.account.service.config;

import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import com.hexagonal.server.identity.core.account.service.AccountDomainServiceImpl;
import com.hexagonal.server.identity.core.account.port.out.AccountRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountServiceConfig {

    private final AccountRepositoryPort accountRepositoryPort;

    public AccountServiceConfig(
            AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Bean
    public AccountDomainService accountService() {
        return new AccountDomainServiceImpl(accountRepositoryPort);
    }

}
