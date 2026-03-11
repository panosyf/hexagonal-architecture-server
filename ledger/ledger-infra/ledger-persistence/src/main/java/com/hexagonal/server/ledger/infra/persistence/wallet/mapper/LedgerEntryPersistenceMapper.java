package com.hexagonal.server.ledger.infra.persistence.wallet.mapper;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.LedgerEntryPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.util.List;

public class LedgerEntryPersistenceMapper {

    private LedgerEntryPersistenceMapper() {
    }

    public static List<LedgerEntryPersistenceEntity> toPersistenceEntityList(List<LedgerEntry> ledgerEntryList) {
        return ledgerEntryList
                .stream()
                .map(entry ->
                        LedgerEntryPersistenceEntity.create(
                                entry.getId().getValue(),
                                entry.getWalletId().getValue(),
                                entry.getAmount().getValue(),
                                entry.getReference()
                        ))
                .toList();
    }

    public static LedgerEntry toDomainEntity(LedgerEntryPersistenceEntity ledgerEntryPersistenceEntity) {
        return LedgerEntry.create(
                Id.valueOf(ledgerEntryPersistenceEntity.getId()),
                Id.valueOf(ledgerEntryPersistenceEntity.getWalletId()),
                Money.of(ledgerEntryPersistenceEntity.getAmount()),
                ledgerEntryPersistenceEntity.getReference()
        );
    }

}
