package com.hexagonal.server.ledger.config;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.shared.kernel.testing.config.AbstractIntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseLedgerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    protected WalletRepositoryPort walletRepositoryPort;

    @AfterEach
    protected void cleanupAfterEach() {
        walletRepositoryPort.deleteAll();
    }

}
