package com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.LedgerEntryRepositoryPort;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.LedgerEntryPersistenceEntity;
import com.hexagonal.server.ledger.infra.persistence.wallet.mapper.LedgerEntryPersistenceMapper;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

import java.util.List;

public class LedgerEntryRepositoryAdapter implements LedgerEntryRepositoryPort {

    private final LedgerEntryJpaRepository ledgerEntryJpaRepository;

    public LedgerEntryRepositoryAdapter(LedgerEntryJpaRepository ledgerEntryJpaRepository) {
        this.ledgerEntryJpaRepository = ledgerEntryJpaRepository;
    }

    @Override
    public void save(LedgerEntry ledgerEntry) {
        LedgerEntryPersistenceEntity ledgerEntryPersistenceEntity = LedgerEntryPersistenceMapper.toPersistenceEntity(ledgerEntry);
        ledgerEntryJpaRepository.save(ledgerEntryPersistenceEntity);
    }

    @Override
    public List<LedgerEntry> findByWalletId(Id walletId) {
        return ledgerEntryJpaRepository.findByWalletId(walletId.getValue())
                .stream()
                .map(LedgerEntryPersistenceMapper::toDomainEntity)
                .toList();
    }

    @Override
    public int findTotalEntries() {
        return ledgerEntryJpaRepository.findTotalEntries();
    }

    @Override
    public void deleteAll() {
        ledgerEntryJpaRepository.deleteAll();
    }

}

