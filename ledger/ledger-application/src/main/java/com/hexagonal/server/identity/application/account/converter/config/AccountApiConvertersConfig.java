package com.hexagonal.server.identity.application.account.converter.config;

import com.hexagonal.server.identity.application.account.converter.in.AccountCreateRequestToOperation;
import com.hexagonal.server.identity.application.account.converter.out.AccountToDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountApiConvertersConfig {

    @Bean
    public AccountToDto accountToDto() {
        return new AccountToDto();
    }

    @Bean
    public AccountCreateRequestToOperation accountCreateRequestToOperation() {
        return new AccountCreateRequestToOperation();
    }

}
