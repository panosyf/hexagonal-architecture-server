package com.hexagonal.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(
        scanBasePackages = "com.hexagonal.server",
        excludeName = {
                "com.hexagonal.server.ledger.LedgerApplication",
                "com.hexagonal.server.identity.IdentityApplication"
        }
)
public class HexagonalServerApplication {

    public static void main(String[] args) {
        System.out.println("monolith-boot running");
        SpringApplication.run(HexagonalServerApplication.class, args);
    }

}
