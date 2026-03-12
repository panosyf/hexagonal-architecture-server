package com.hexagonal.server.ledger.infra.persistence.wallet.mapper;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.LedgerEntryPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public class LedgerEntryPersistenceMapper {

    private LedgerEntryPersistenceMapper() {
    }

    public static LedgerEntryPersistenceEntity toPersistenceEntity(LedgerEntry ledgerEntry) {
        return LedgerEntryPersistenceEntity.create(
                ledgerEntry.getId().getValue(),
                ledgerEntry.getWalletId().getValue(),
                ledgerEntry.getAmount(),
                ledgerEntry.getType(),
                ledgerEntry.getReference(),
                ledgerEntry.getCreatedAt(),
                ledgerEntry.getUpdatedAt());
    }

    public static LedgerEntry toDomainEntity(LedgerEntryPersistenceEntity ledgerEntryPersistenceEntity) {
        return LedgerEntry.create(
                Id.valueOf(ledgerEntryPersistenceEntity.getId()),
                Id.valueOf(ledgerEntryPersistenceEntity.getWalletId()),
                ledgerEntryPersistenceEntity.getAmount(),
                ledgerEntryPersistenceEntity.getType(),
                ledgerEntryPersistenceEntity.getReference(),
                ledgerEntryPersistenceEntity.getCreatedAt(),
                ledgerEntryPersistenceEntity.getUpdatedAt());
    }

}
