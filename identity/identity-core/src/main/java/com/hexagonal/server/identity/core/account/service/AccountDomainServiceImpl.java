package com.hexagonal.server.identity.core.account.service;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;

public class AccountDomainServiceImpl implements AccountDomainService {

    public AccountDomainServiceImpl() {
    }

    @Override
    public Account createAccount(final CreateAccountOperation createAccountOperation) {
        return new Account(
                createAccountOperation.email(),
                createAccountOperation.username(),
                createAccountOperation.password(),
                createAccountOperation.name()
        );
    }

}
