package com.hexagonal.server.ledger.infra.persistence.wallet.mapper;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.LedgerEntryPersistenceEntity;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.WalletPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

import java.util.List;

public class WalletPersistenceMapper {

    private WalletPersistenceMapper() {
    }

    public static Wallet toDomainEntity(WalletPersistenceEntity walletEntity) {
        return Wallet.create(
                Id.valueOf(walletEntity.getId()),
                Id.valueOf(walletEntity.getAccountId()),
                List.of());
    }

    public static Wallet toDomainEntity(WalletPersistenceEntity walletEntity, List<LedgerEntryPersistenceEntity> ledgerEntities) {
        List<LedgerEntry> entries = ledgerEntities.stream()
                .map(WalletPersistenceMapper::toLedgerEntryDomain)
                .toList();
        return Wallet.create(
                Id.valueOf(walletEntity.getId()),
                Id.valueOf(walletEntity.getAccountId()),
                entries);
    }

    public static WalletPersistenceEntity toWalletEntity(Wallet wallet) {
        return WalletPersistenceEntity.create(
                wallet.getId().getValue(),
                wallet.getAccountId().getValue());
    }

    public static List<LedgerEntryPersistenceEntity> toLedgerEntities(Wallet wallet) {
        return wallet.getLedgerEntryList()
                .stream()
                .map(entry ->
                        LedgerEntryPersistenceEntity.create(
                                entry.getId().getValue(),
                                wallet.getId().getValue(),
                                entry.getAmount().getValue(),
                                entry.getReference()
                        ))
                .toList();
    }

    private static LedgerEntry toLedgerEntryDomain(LedgerEntryPersistenceEntity ledgerEntryPersistenceEntity) {
        return LedgerEntry.create(
                Id.valueOf(ledgerEntryPersistenceEntity.getId()),
                Id.valueOf(ledgerEntryPersistenceEntity.getWalletId()),
                Money.of(ledgerEntryPersistenceEntity.getAmount()),
                ledgerEntryPersistenceEntity.getReference()
        );
    }

}
