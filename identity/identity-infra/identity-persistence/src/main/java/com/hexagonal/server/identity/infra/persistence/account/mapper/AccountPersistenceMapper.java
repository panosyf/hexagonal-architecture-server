package com.hexagonal.server.identity.infra.persistence.account.mapper;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.infra.persistence.account.entity.AccountPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public class AccountPersistenceMapper {

    private AccountPersistenceMapper() {
    }

    public static Account toDomainEntity(AccountPersistenceEntity accountPersistenceEntity) {
        return Account.create(
                Id.valueOf(accountPersistenceEntity.getId()),
                accountPersistenceEntity.getEmail(),
                accountPersistenceEntity.getUsername(),
                accountPersistenceEntity.getPassword(),
                accountPersistenceEntity.getName(),
                accountPersistenceEntity.getCreatedAt(),
                accountPersistenceEntity.getUpdatedAt()
        );
    }

    public static AccountPersistenceEntity toPersistenceEntity(Account account) {
        return AccountPersistenceEntity.create(
                account.getId().getValue(),
                account.getEmail(),
                account.getUsername(),
                account.getPassword(),
                account.getName(),
                account.getCreatedAt(),
                account.getUpdatedAt()
        );
    }

}
