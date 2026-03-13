package com.hexagonal.server.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.hexagonal.server.ledger")
@EntityScan("com.hexagonal.server.ledger")
@EnableJpaRepositories("com.hexagonal.server.ledger")
public class HexagonalServerLedgerApplication {

    public static void main(String[] args) {
        System.out.println("ledger-boot running");
        SpringApplication.run(HexagonalServerLedgerApplication.class, args);
    }

}