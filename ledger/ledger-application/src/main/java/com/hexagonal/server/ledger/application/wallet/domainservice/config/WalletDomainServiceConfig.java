package com.hexagonal.server.ledger.application.wallet.domainservice.config;

import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletDomainServiceConfig {

    public WalletDomainServiceConfig() {
    }

    @Bean("walletDomainService")
    public WalletDomainService walletDomainService() {
        return new WalletDomainServiceImpl();
    }

}
