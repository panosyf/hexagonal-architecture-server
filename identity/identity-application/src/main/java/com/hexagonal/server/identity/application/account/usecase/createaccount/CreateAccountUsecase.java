package com.hexagonal.server.identity.application.account.usecase.createaccount;

import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;

public interface CreateAccountUsecase {

    AccountCreationResponse createAccount(AccountCreateRequest accountCreateRequest);

}
