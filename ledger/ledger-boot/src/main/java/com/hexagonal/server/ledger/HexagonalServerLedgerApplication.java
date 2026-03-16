package com.hexagonal.server.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hexagonal.server.ledger")
public class HexagonalServerLedgerApplication {

    public static void main(String[] args) {
        System.out.println("ledger-boot running");
        SpringApplication.run(HexagonalServerLedgerApplication.class, args);
    }

}