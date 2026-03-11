package com.hexagonal.server.ledger.infra.persistence.wallet.mapper;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.LedgerEntryPersistenceEntity;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.WalletPersistenceEntity;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

import java.util.List;

public class WalletPersistenceMapper {

    private WalletPersistenceMapper() {
    }

    public static Wallet toDomainEntity(WalletPersistenceEntity walletEntity) {
        return Wallet.create(
                Id.valueOf(walletEntity.getId()),
                Id.valueOf(walletEntity.getAccountId()),
                walletEntity.getCreatedAt(),
                walletEntity.getUpdatedAt());
    }

    public static Wallet toDomainEntity(WalletPersistenceEntity walletEntity, List<LedgerEntryPersistenceEntity> ledgerEntities) {
        List<LedgerEntry> ledgerEntryList = ledgerEntities.stream()
                .map(LedgerEntryPersistenceMapper::toDomainEntity)
                .toList();
        return Wallet.create(
                Id.valueOf(walletEntity.getId()),
                Id.valueOf(walletEntity.getAccountId()),
                ledgerEntryList,
                walletEntity.getCreatedAt(),
                walletEntity.getUpdatedAt());
    }

    public static WalletPersistenceEntity toPersistenceEntity(Wallet wallet) {
        return WalletPersistenceEntity.create(
                wallet.getId().getValue(),
                wallet.getAccountId().getValue(),
                wallet.getCreatedAt(),
                wallet.getUpdatedAt());
    }

}
