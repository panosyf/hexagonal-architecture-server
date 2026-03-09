package com.hexagonal.server.identity.application.account.usecase.getaccount;

import com.hexagonal.server.identity.application.account.shared.dto.AccountDto;

public interface GetAccountUsecase {

    AccountDto getAccount(String id);

}
