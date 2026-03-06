package com.hexagonal.server.identity.core.account.service;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.logging.LogInfoMessage;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountDomainServiceImpl implements AccountDomainService {

    private final Logger log = LoggerFactory.getLogger(AccountDomainServiceImpl.class);

    public AccountDomainServiceImpl() {
    }

    @Override
    public Account createAccount(final CreateAccountOperation createAccountOperation) {
        Account account = new Account(
                createAccountOperation.email(),
                createAccountOperation.username(),
                createAccountOperation.password(),
                createAccountOperation.name()
        );
        log.info(LogInfoMessage.LOG_ACCOUNT_CREATED_INFO, account.getEmail(), account.getUsername());
        return account;
    }

}
