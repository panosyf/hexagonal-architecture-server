package com.hexagonal.server.account.infra.integration.test.config;

import com.hexagonal.server.account.core.port.out.account.AccountRepositoryPort;
import com.hexagonal.server.account.core.port.out.transaction.TransactionRepositoryPort;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.hexagonal.server.shared.kernel.testing.config.AbstractIntegrationTest;

public class BaseAccountIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected AccountRepositoryPort accountRepositoryPort;

    @Autowired
    protected TransactionRepositoryPort transactionRepositoryPort;

    @AfterEach
    protected void cleanupRepositories() {
        accountRepositoryPort.deleteAll();
        transactionRepositoryPort.deleteAll();
    }

}
