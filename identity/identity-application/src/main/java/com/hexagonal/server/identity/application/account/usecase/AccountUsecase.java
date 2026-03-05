package com.hexagonal.server.identity.application.account.usecase;

import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;
import com.hexagonal.server.identity.application.account.model.response.AccountResponse;

public interface AccountUsecase {

    AccountCreationResponse createAccount(AccountCreateRequest accountCreateRequest);

    AccountResponse getAccount(String id);

}
