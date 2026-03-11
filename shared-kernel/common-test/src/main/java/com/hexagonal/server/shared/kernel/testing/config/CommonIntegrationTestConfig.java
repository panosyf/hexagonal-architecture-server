package com.hexagonal.server.shared.kernel.testing.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.hexagonal.server.shared.kernel.testing.config.requesttestclient.RequestTestClient;

@TestConfiguration
public class CommonIntegrationTestConfig {

    private final WebTestClient webTestClient;

    public CommonIntegrationTestConfig(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @Lazy
    @Bean
    public RequestTestClient crudTestClient() {
        return new RequestTestClient(webTestClient);
    }

}
