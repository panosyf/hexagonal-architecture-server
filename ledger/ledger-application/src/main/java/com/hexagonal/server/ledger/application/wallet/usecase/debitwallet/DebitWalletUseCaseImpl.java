package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.IdempotencyRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.DebitOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public class DebitWalletUseCaseImpl {

    private final WalletRepositoryPort walletRepositoryPort;
    private final WalletDomainService walletDomainService;
    private final IdempotencyRepositoryPort idempotencyRepositoryPort;

    public DebitWalletUseCaseImpl(
            WalletRepositoryPort walletRepositoryPort,
            WalletDomainService walletDomainService,
            IdempotencyRepositoryPort idempotencyRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.walletDomainService = walletDomainService;
        this.idempotencyRepositoryPort = idempotencyRepositoryPort;
    }

    public LedgerEntry execute(
            Id walletId,
            Money amount,
            String reference,
            String idempotencyKey) {
        if (idempotencyRepositoryPort.exists(idempotencyKey)) {
            throw new IllegalStateException("Duplicate operation");
        }
        Wallet wallet = walletRepositoryPort.findById(walletId);
        LedgerEntry entry = walletDomainService.debit(new DebitOperation(wallet, amount, reference));
        walletRepositoryPort.save(wallet);
        idempotencyRepositoryPort.store(idempotencyKey);
        return entry;
    }
}

