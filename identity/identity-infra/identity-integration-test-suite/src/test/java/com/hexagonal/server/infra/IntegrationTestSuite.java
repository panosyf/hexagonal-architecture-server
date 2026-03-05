package com.hexagonal.server.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = "com.hexagonal.server"
)
public class IntegrationTestSuite {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationTestSuite.class, args);
    }

}
