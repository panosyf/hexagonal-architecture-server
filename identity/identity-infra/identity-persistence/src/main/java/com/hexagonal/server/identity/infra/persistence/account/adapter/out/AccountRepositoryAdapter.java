package com.hexagonal.server.identity.infra.persistence.account.adapter.out;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.identity.core.account.exception.elementnotfound.AccountNotFoundException;
import com.hexagonal.server.identity.application.account.port.out.repository.AccountRepositoryPort;
import com.hexagonal.server.identity.infra.persistence.account.entity.AccountPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.springframework.core.convert.ConversionService;

public class AccountRepositoryAdapter implements AccountRepositoryPort {

    private final AccountJpaRepository accountJpaRepository;
    private final ConversionService conversionService;

    public AccountRepositoryAdapter(AccountJpaRepository accountRepositoryPort, ConversionService conversionService) {
        this.accountJpaRepository = accountRepositoryPort;
        this.conversionService = conversionService;
    }

    @Override
    public Account save(Account account) {
        AccountPersistenceEntity accountPersistenceEntity = conversionService.convert(account, AccountPersistenceEntity.class);
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
        return conversionService.convert(accountPersistenceEntity, Account.class);
    }

}
