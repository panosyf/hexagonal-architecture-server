package com.hexagonal.server.account.core.service.account;

import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.account.core.model.operation.account.CreateAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.DecreaseBalanceOperation;
import com.hexagonal.server.account.core.model.operation.account.GetAccountOperation;
import com.hexagonal.server.account.core.model.operation.account.IncreaseBalanceOperation;

public interface AccountDomainService {

    Account getAccount(GetAccountOperation getAccountOperation);

    Account createAccount(CreateAccountOperation createAccountOperation);

    Account increaseBalance(IncreaseBalanceOperation increaseBalanceOperation);

    Account decreaseBalance(DecreaseBalanceOperation decreaseBalanceOperation);

}
