package com.hexagonal.server.identity.infra.persistence.account.converter.config;

import com.hexagonal.server.identity.infra.persistence.account.converter.AccountToDomain;
import com.hexagonal.server.identity.infra.persistence.account.converter.AccountToEntity;
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
