package com.hexagonal.server.account.application.service.config.transaction;

import com.hexagonal.server.account.core.service.transaction.TransactionDomainService;
import com.hexagonal.server.account.core.service.transaction.TransactionDomainServiceImpl;
import com.hexagonal.server.account.core.port.out.transaction.TransactionRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionServiceConfig {
    private final TransactionRepositoryPort transactionRepositoryPort;

    public TransactionServiceConfig(
            TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Bean
    public TransactionDomainService transactionService() {
        return new TransactionDomainServiceImpl(transactionRepositoryPort);
    }

}
