package com.hexagonal.server.identity.infra.integration.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = "com.hexagonal.server"
)
public class IdentityIntegrationTest {

    public static void main(String[] args) {
        SpringApplication.run(IdentityIntegrationTest.class, args);
    }

}
