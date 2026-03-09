package com.hexagonal.server.identity.application.account.mapper.config;

import com.hexagonal.server.identity.application.account.mapper.AccountCreateRequestToOperation;
import com.hexagonal.server.identity.application.account.mapper.AccountToDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountMappersConfig {

    @Bean
    public AccountToDto accountToDto() {
        return new AccountToDto();
    }

    @Bean
    public AccountCreateRequestToOperation accountCreateRequestToOperation() {
        return new AccountCreateRequestToOperation();
    }

}
