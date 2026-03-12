package com.hexagonal.server.ledger.infra.persistence.wallet.adapter.out;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.infra.persistence.wallet.entity.WalletPersistenceEntity;
import com.hexagonal.server.ledger.infra.persistence.wallet.mapper.WalletPersistenceMapper;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public class WalletRepositoryAdapter implements WalletRepositoryPort {

    private final WalletJpaRepository walletJpaRepository;

    public WalletRepositoryAdapter(
            WalletJpaRepository walletJpaRepository) {
        this.walletJpaRepository = walletJpaRepository;
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletPersistenceEntity walletEntity = WalletPersistenceMapper.toPersistenceEntity(wallet);
        walletJpaRepository.save(walletEntity);
        return wallet;
    }

    @Override
    public Wallet findById(Id walletId) {
        WalletPersistenceEntity walletEntity = walletJpaRepository.findById(walletId.getValue())
                .orElseThrow(() ->
                        new RuntimeException("Wallet not found"));
        return WalletPersistenceMapper.toDomainEntity(walletEntity);
    }

    @Override
    public int findTotalEntries() {
        return walletJpaRepository.findTotalEntries();
    }

    @Override
    public void deleteAll() {
        walletJpaRepository.deleteAll();
    }

}
