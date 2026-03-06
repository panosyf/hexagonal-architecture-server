package com.hexagonal.server.identity.application.account.usecase;

import com.hexagonal.server.identity.application.account.model.dto.AccountDto;
import com.hexagonal.server.identity.application.account.model.request.AccountCreateRequest;
import com.hexagonal.server.identity.application.account.model.response.AccountCreationResponse;

public interface AccountUsecase {

    AccountCreationResponse createAccount(AccountCreateRequest accountCreateRequest);

    AccountDto getAccount(String id);

}
