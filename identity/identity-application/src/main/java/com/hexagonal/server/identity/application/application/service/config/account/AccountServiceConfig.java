package com.hexagonal.server.identity.application.application.service.config.account;

import com.hexagonal.server.identity.core.core.service.account.AccountDomainService;
import com.hexagonal.server.identity.core.core.service.account.AccountDomainServiceImpl;
import com.hexagonal.server.identity.core.core.port.out.account.AccountRepositoryPort;
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
