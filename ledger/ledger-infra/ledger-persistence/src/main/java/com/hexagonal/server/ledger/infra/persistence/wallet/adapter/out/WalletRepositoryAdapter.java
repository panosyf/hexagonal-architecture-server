package com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.LedgerEntryPersistenceEntity;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.WalletPersistenceEntity;
import com.hexagonal.server.ledger.infra.persistence.wallet.mapper.WalletPersistenceMapper;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

import java.util.List;

public class WalletRepositoryAdapter implements WalletRepositoryPort {

    private final WalletJpaRepository walletJpaRepository;
    private final LedgerEntryJpaRepository ledgerEntryJpaRepository;

    public WalletRepositoryAdapter(
            WalletJpaRepository walletJpaRepository,
            LedgerEntryJpaRepository ledgerEntryJpaRepository) {
        this.walletJpaRepository = walletJpaRepository;
        this.ledgerEntryJpaRepository = ledgerEntryJpaRepository;
    }

    @Override
    public Wallet findById(Id walletId) {
        WalletPersistenceEntity walletEntity = walletJpaRepository.findById(walletId.getValue())
                .orElseThrow(() ->
                        new RuntimeException("Wallet not found"));
        List<LedgerEntryPersistenceEntity> ledgerEntries = ledgerEntryJpaRepository.findByWalletId(walletId.getValue());
        return WalletPersistenceMapper.toDomainEntity(walletEntity, ledgerEntries);
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletPersistenceEntity walletEntity = WalletPersistenceMapper.toWalletEntity(wallet);
        walletJpaRepository.save(walletEntity);
        List<LedgerEntryPersistenceEntity> entries = WalletPersistenceMapper.toLedgerEntities(wallet);
        ledgerEntryJpaRepository.saveAll(entries);
        return wallet;
    }

}
