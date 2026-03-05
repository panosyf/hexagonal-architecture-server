package com.hexagonal.server.identity.application.usecase.account;

import com.hexagonal.server.identity.application.model.request.account.AccountCreateRequest;
import com.hexagonal.server.identity.application.model.response.account.AccountCreationResponse;
import com.hexagonal.server.identity.application.model.response.account.AccountResponse;

import java.math.BigDecimal;

public interface AccountUsecase {

    AccountCreationResponse createAccount(AccountCreateRequest accountCreateRequest);

    AccountResponse getAccount(String id);

    void increaseBalance(String id, BigDecimal amount);

    void decreaseBalance(String id, BigDecimal amount);

}
