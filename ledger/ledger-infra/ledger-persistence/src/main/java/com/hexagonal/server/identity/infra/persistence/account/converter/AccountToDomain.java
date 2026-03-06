package com.hexagonal.server.identity.infra.persistence.account.converter;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.infra.persistence.account.entity.AccountPersistenceEntity;
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
                accountPersistenceEntity.getCreatedAt(),
                accountPersistenceEntity.getUpdatedAt()
        );
    }

}
