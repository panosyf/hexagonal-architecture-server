package com.hexagonal.server.identity.core.account.service;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.model.operation.DecreaseBalanceOperation;
import com.hexagonal.server.identity.core.account.model.operation.GetAccountOperation;
import com.hexagonal.server.identity.core.account.model.operation.IncreaseBalanceOperation;

public interface AccountDomainService {

    Account getAccount(GetAccountOperation getAccountOperation);

    Account createAccount(CreateAccountOperation createAccountOperation);

    Account increaseBalance(IncreaseBalanceOperation increaseBalanceOperation);

    Account decreaseBalance(DecreaseBalanceOperation decreaseBalanceOperation);

}
