package com.hexagonal.server.identity.application.account.usecase.getaccount;

import com.hexagonal.server.identity.application.account.model.dto.AccountDto;

public interface GetAccountUsecase {

    AccountDto getAccount(String id);

}
