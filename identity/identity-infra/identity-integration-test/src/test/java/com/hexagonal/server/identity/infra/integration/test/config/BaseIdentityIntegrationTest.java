package com.hexagonal.server.identity.infra.integration.test.config;

import com.hexagonal.server.identity.core.account.port.out.AccountRepositoryPort;
import com.hexagonal.server.identity.core.account.port.out.transaction.TransactionRepositoryPort;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.hexagonal.server.shared.kernel.testing.config.AbstractIntegrationTest;

public class BaseIdentityIntegrationTest extends AbstractIntegrationTest {

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
