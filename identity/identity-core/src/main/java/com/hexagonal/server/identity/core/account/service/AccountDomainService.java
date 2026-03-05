package com.hexagonal.server.identity.core.account.service;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;
import com.hexagonal.server.identity.core.account.model.operation.GetAccountOperation;

public interface AccountDomainService {

    Account getAccount(GetAccountOperation getAccountOperation);

    Account createAccount(CreateAccountOperation createAccountOperation);

}
