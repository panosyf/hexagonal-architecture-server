package com.hexagonal.server.infra.persistence.adapter.out.transaction.config;

import com.hexagonal.server.account.core.port.out.transaction.TransactionRepositoryPort;
import com.hexagonal.server.infra.persistence.adapter.out.transaction.TransactionJpaRepository;
import com.hexagonal.server.infra.persistence.adapter.out.transaction.TransactionRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class TransactionRepositoryAdapterConfig {

    private final TransactionJpaRepository transactionJpaRepository;
    private final ConversionService conversionService;

    public TransactionRepositoryAdapterConfig(
            TransactionJpaRepository transactionJpaRepository,
            ConversionService conversionService) {
        this.transactionJpaRepository = transactionJpaRepository;
        this.conversionService = conversionService;
    }

    @Bean
    public TransactionRepositoryPort transactionRepositoryPort() {
        return new TransactionRepositoryAdapter(transactionJpaRepository, conversionService);
    }

}
