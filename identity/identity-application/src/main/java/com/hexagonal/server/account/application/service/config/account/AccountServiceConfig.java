package com.hexagonal.server.account.application.service.config.account;

import com.hexagonal.server.account.core.service.account.AccountDomainService;
import com.hexagonal.server.account.core.service.account.AccountDomainServiceImpl;
import com.hexagonal.server.account.core.port.out.account.AccountRepositoryPort;
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
