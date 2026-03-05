package com.hexagonal.server.identity.infra.persistence.converter.account;

import com.hexagonal.server.identity.core.domain.account.Account;
import com.hexagonal.server.identity.infra.persistence.entity.account.AccountPersistenceEntity;
import org.springframework.core.convert.converter.Converter;

public class AccountToEntity implements Converter<Account, AccountPersistenceEntity> {

    @Override
    public AccountPersistenceEntity convert(Account account) {
        return new AccountPersistenceEntity(
                account.getId().getValue(),
                account.getEmail(),
                account.getUsername(),
                account.getPassword(),
                account.getName(),
                account.getBalance(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

}
