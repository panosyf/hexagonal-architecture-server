package com.hexagonal.server.account.application.usecase.transaction.config;

import com.hexagonal.server.account.application.usecase.account.AccountUsecase;
import com.hexagonal.server.account.application.usecase.transaction.TransactionUsecase;
import com.hexagonal.server.account.application.usecase.transaction.TransactionUsecaseImpl;
import com.hexagonal.server.account.core.service.transaction.TransactionDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class TransactionUsecaseConfig {

    private final TransactionDomainService transactionDomainService;
    private final AccountUsecase accountUsecase;
    private final ConversionService conversionService;

    public TransactionUsecaseConfig(TransactionDomainService transactionDomainService, AccountUsecase accountUsecase, ConversionService conversionService) {
        this.transactionDomainService = transactionDomainService;
        this.accountUsecase = accountUsecase;
        this.conversionService = conversionService;
    }

    @Bean
    public TransactionUsecase transactionApi() {
        return new TransactionUsecaseImpl(transactionDomainService, accountUsecase, conversionService);
    }

}
