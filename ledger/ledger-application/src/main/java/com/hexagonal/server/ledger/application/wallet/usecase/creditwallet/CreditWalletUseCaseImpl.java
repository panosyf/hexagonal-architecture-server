package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.IdempotencyRepository;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreditOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public class CreditWalletUseCaseImpl {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletDomainService walletDomainService;
    private final IdempotencyRepository idempotencyRepository;

    public CreditWalletUseCaseImpl(
            WalletRepositoryPort walletRepositoryPort,
            WalletDomainService walletDomainService,
            IdempotencyRepository idempotencyRepository) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletDomainService = walletDomainService;
        this.idempotencyRepository = idempotencyRepository;
    }

    public LedgerEntry execute(
            Id walletId,
            Money amount,
            String reference,
            String idempotencyKey) {
        if (idempotencyRepository.exists(idempotencyKey)) {
            throw new IllegalStateException("Duplicate operation");
        }
        Wallet wallet = walletRepositoryPort.findById(walletId);
        LedgerEntry entry = walletDomainService.credit(new CreditOperation(wallet, amount, reference));
        walletRepositoryPort.save(wallet);
        idempotencyRepository.store(idempotencyKey);
        return entry;
    }
}

