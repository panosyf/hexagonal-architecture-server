package com.hexagonal.server.identity.application.application.converter.account.out;

import com.hexagonal.server.identity.application.application.model.dto.account.AccountDto;
import com.hexagonal.server.identity.core.core.domain.account.Account;
import org.springframework.core.convert.converter.Converter;

public class AccountToDto implements Converter<Account, AccountDto> {

    @Override
    public AccountDto convert(Account account) {
        return new AccountDto(
                account.getId().getValue(),
                account.getName().getFirstName(),
                account.getName().getLastName(),
                account.getBalance().getValue(),
                account.getCreatedAt().getTime(),
                account.getUpdatedAt().getTime()
        );
    }

}
