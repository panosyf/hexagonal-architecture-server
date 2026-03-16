package com.hexagonal.server.identity.infra.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.hexagonal.server.identity.infra.persistence"
)
@EntityScan(
        basePackages = "com.hexagonal.server.identity.infra.persistence"
)
public class IdentityJpaConfig {
}
