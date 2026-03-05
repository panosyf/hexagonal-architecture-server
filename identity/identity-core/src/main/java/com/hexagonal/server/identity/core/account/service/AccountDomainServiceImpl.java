package com.hexagonal.server.identity.core.account.service;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.logging.LogInfoMessage;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.model.operation.GetAccountOperation;
import com.hexagonal.server.identity.core.account.port.out.AccountRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountDomainServiceImpl implements AccountDomainService {

    private final AccountRepositoryPort accountRepositoryPort;

    private final Logger log = LoggerFactory.getLogger(AccountDomainServiceImpl.class);

    public AccountDomainServiceImpl(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    @Override
    public Account getAccount(final GetAccountOperation getAccountOperation) {
        log.info("AccountDomainServiceImpl");
        return accountRepositoryPort.findById(getAccountOperation.id());
    }

    @Override
    public Account createAccount(final CreateAccountOperation createAccountOperation) {
        Account account = new Account(
                createAccountOperation.email(),
                createAccountOperation.username(),
                createAccountOperation.password(),
                createAccountOperation.name()
        );
        Account persistedAccount = accountRepositoryPort.save(account);
        log.info(LogInfoMessage.LOG_ACCOUNT_CREATED_INFO, persistedAccount.getEmail(), persistedAccount.getUsername());
        return persistedAccount;
    }

}
