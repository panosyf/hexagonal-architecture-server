package com.hexagonal.server.infra.persistence.adapter.out.account;

import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.account.core.exception.elementnotfound.account.AccountNotFoundException;
import com.hexagonal.server.account.core.port.out.account.AccountRepositoryPort;
import com.hexagonal.server.infra.persistence.entity.account.AccountPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import jakarta.transaction.Transactional;
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
    public Money findBalance(Id id) {
        return Money.of(accountJpaRepository.findBalance(id.getValue()));
    }

    @Override
    @Transactional
    public Account updateBalance(Account account) {
        accountJpaRepository.updateBalance(account.getId().getValue(), account.getBalance().getValue());
        return findById(account.getId());
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
