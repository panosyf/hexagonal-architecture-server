package com.hexagonal.server.account.core.service.account;

import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.account.core.logging.account.LogInfoMessage;
import com.hexagonal.server.account.core.model.operation.account.CreateAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.DecreaseBalanceOperation;
import com.hexagonal.server.account.core.model.operation.account.GetAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.IncreaseBalanceOperation;
import com.hexagonal.server.account.core.port.out.account.AccountRepositoryPort;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
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

    @Override
    public Account increaseBalance(final IncreaseBalanceOperation increaseBalanceOperation) {
        Id id = increaseBalanceOperation.id();
        Account account = accountRepositoryPort.findById(id);
        account.increaseBalance(increaseBalanceOperation.amount());
        Account updatedAccount = accountRepositoryPort.updateBalance(account);
        log.info(LogInfoMessage.LOG_BALANCE_INCREASED_FOR_ACCOUNT, id);
        return updatedAccount;
    }

    @Override
    public Account decreaseBalance(final DecreaseBalanceOperation decreaseBalanceOperation) {
        Id id = decreaseBalanceOperation.id();
        Account account = accountRepositoryPort.findById(id);
        account.decreaseBalance(decreaseBalanceOperation.amount());
        Account updatedAccount = accountRepositoryPort.updateBalance(account);
        log.info(LogInfoMessage.LOG_BALANCE_DECREASED_FOR_ACCOUNT, id);
        return updatedAccount;
    }

}
