package com.hexagonal.server.identity.application.account.mapper;

import com.hexagonal.server.identity.application.account.common.dto.AccountDto;
import com.hexagonal.server.identity.core.account.domain.Account;
import org.springframework.core.convert.converter.Converter;

public class AccountToDto implements Converter<Account, AccountDto> {

    @Override
    public AccountDto convert(Account account) {
        return new AccountDto(
                account.getId().getValue(),
                account.getName().getFirstName(),
                account.getName().getLastName(),
                account.getCreatedAt().getTime(),
                account.getUpdatedAt().getTime()
        );
    }

}
