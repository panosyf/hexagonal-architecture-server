package com.hexagonal.server.account.application.converter.account.config;

import com.hexagonal.server.account.application.converter.account.in.AccountCreateRequestToOperation;
import com.hexagonal.server.account.application.converter.account.out.AccountToDto;
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
