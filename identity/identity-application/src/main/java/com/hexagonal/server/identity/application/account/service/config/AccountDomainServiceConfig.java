package com.hexagonal.server.identity.application.account.service.config;

import com.hexagonal.server.identity.core.account.service.AccountDomainService;
import com.hexagonal.server.identity.core.account.service.AccountDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountDomainServiceConfig {

    public AccountDomainServiceConfig() {
    }

    @Bean
    public AccountDomainService accountService() {
        return new AccountDomainServiceImpl();
    }

}
