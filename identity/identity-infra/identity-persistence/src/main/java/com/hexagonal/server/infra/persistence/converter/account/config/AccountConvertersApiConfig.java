package com.hexagonal.server.infra.persistence.converter.account.config;

import com.hexagonal.server.infra.persistence.converter.account.AccountToDomain;
import com.hexagonal.server.infra.persistence.converter.account.AccountToEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConvertersApiConfig {

    @Bean
    public AccountToDomain accountToDomain() {
        return new AccountToDomain();
    }

    @Bean
    public AccountToEntity accountToEntity() {
        return new AccountToEntity();
    }

}
