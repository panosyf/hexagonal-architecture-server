package com.hexagonal.server.account.infra.integration.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = "com.hexagonal.server"
)
public class AccountIntegrationTest {

    public static void main(String[] args) {
        SpringApplication.run(AccountIntegrationTest.class, args);
    }

}
