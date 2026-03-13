package com.hexagonal.server.identity.application.account.usecase.createaccount;

import com.hexagonal.server.identity.application.account.usecase.createaccount.model.request.CreateAccountRequest;
import com.hexagonal.server.identity.application.account.usecase.createaccount.model.response.CreateAccountResponse;

public interface CreateAccountUsecase {

    CreateAccountResponse createAccount(CreateAccountRequest createAccountRequest);

}
