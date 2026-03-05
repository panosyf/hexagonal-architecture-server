package com.hexagonal.server.account.application.converter.transaction.config;

import com.hexagonal.server.account.application.converter.transaction.in.TransactionCreateRequestToOperation;
import com.hexagonal.server.account.application.converter.transaction.out.TransactionToDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionApiConvertersConfig {

    @Bean
    public TransactionToDto transactionToDto() {
        return new TransactionToDto();
    }

    @Bean
    public TransactionCreateRequestToOperation transactionCreateRequestToOperation() {
        return new TransactionCreateRequestToOperation();
    }

}
