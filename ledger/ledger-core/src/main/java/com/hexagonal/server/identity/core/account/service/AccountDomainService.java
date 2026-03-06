package com.hexagonal.server.identity.core.account.service;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.model.operation.CreateAccountOperation;

public interface AccountDomainService {

    Account createAccount(CreateAccountOperation createAccountOperation);

}
