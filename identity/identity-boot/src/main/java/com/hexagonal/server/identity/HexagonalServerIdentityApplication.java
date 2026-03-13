package com.hexagonal.server.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.hexagonal.server.identity")
@EntityScan("com.hexagonal.server.identity")
@EnableJpaRepositories("com.hexagonal.server.identity")
public class HexagonalServerIdentityApplication {

    public static void main(String[] args) {
        System.out.println("identity-boot running");
        SpringApplication.run(HexagonalServerIdentityApplication.class, args);
    }

}