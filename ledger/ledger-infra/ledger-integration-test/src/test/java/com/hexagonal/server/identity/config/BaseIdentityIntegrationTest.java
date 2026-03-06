package com.hexagonal.server.identity.config;

import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.shared.kernel.testing.config.AbstractIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseIdentityIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected AccountRepositoryPort accountRepositoryPort;

    @AfterEach
    protected void cleanupRepositories() {
        accountRepositoryPort.deleteAll();
    }

}
