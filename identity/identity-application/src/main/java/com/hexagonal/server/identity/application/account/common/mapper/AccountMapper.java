package com.hexagonal.server.identity.application.account.common.mapper;

import com.hexagonal.server.identity.application.account.common.dto.AccountDto;
import com.hexagonal.server.identity.core.account.domain.Account;

public class AccountMapper {

    private AccountMapper() {
    }

    public static AccountDto toAccountDto(Account account) {
        return new AccountDto(
                account.getId().getValue(),
                account.getName().getFirstName(),
                account.getName().getLastName(),
                account.getCreatedAt().getTime(),
                account.getUpdatedAt().getTime()
        );
    }

}
