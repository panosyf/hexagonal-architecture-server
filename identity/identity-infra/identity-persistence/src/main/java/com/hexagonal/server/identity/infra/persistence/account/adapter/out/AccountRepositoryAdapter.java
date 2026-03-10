package com.hexagonal.server.identity.infra.persistence.account.adapter.out;

import com.hexagonal.server.identity.application.account.common.exception.AccountNotFoundException;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.infra.persistence.account.entity.AccountPersistenceEntity;
import com.hexagonal.server.identity.infra.persistence.account.mapper.AccountPersistenceMapper;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final AccountJpaRepository accountJpaRepository;

    public AccountRepositoryAdapter(AccountJpaRepository accountRepositoryPort) {
        this.accountJpaRepository = accountRepositoryPort;
    }

    @Override
    public Account save(Account account) {
        AccountPersistenceEntity accountPersistenceEntity = AccountPersistenceMapper.toPersistenceEntity(account);
        AccountPersistenceEntity persistedAccountPersistenceEntity = accountJpaRepository.save(accountPersistenceEntity);
        return accountToDomain(persistedAccountPersistenceEntity);
    }

    @Override
    public Account findById(Id id) {
        AccountPersistenceEntity accountPersistenceEntity = accountJpaRepository.findById(id.getValue())
                .orElseThrow(() -> new AccountNotFoundException(id.getValue()));
        return accountToDomain(accountPersistenceEntity);
    }

    @Override
    public int findTotalEntries() {
        return accountJpaRepository.findTotalEntries();
    }

    @Override
    public void deleteAll() {
        accountJpaRepository.deleteAll();
    }

    private Account accountToDomain(AccountPersistenceEntity accountPersistenceEntity) {
        return AccountPersistenceMapper.toDomainEntity(accountPersistenceEntity);
    }

}
