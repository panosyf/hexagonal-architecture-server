package com.hexagonal.server.infra.persistence.converter.account;

import com.hexagonal.server.identity.core.domain.account.Account;
import com.hexagonal.server.infra.persistence.entity.account.AccountPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.springframework.core.convert.converter.Converter;

public class AccountToDomain implements Converter<AccountPersistenceEntity, Account> {

    @Override
    public Account convert(AccountPersistenceEntity accountPersistenceEntity) {
        return new Account(
                Id.valueOf(accountPersistenceEntity.getId()),
                accountPersistenceEntity.getEmail(),
                accountPersistenceEntity.getUsername(),
                accountPersistenceEntity.getPassword(),
                accountPersistenceEntity.getName(),
                accountPersistenceEntity.getBalance(),
                accountPersistenceEntity.getCreatedAt(),
                accountPersistenceEntity.getUpdatedAt()
        );
    }

}
