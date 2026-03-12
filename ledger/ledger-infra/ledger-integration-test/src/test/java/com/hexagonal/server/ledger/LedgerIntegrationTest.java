package com.hexagonal.server.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = "com.hexagonal.server.ledger"
)
public class LedgerIntegrationTest {

    public static void main(String[] args) {
        SpringApplication.run(LedgerIntegrationTest.class, args);
    }

}
