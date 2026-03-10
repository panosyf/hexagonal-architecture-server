package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.IdempotencyRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.mapper.CreditWalletMapper;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreditOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.springframework.beans.factory.annotation.Qualifier;

public class CreditWalletUseCaseImpl implements CreditWalletUseCase {

    private final WalletDomainService walletDomainService;
    private final WalletRepositoryPort walletRepositoryPort;
    private final IdempotencyRepositoryPort idempotencyRepositoryPort;

    public CreditWalletUseCaseImpl(
            @Qualifier("walletDomainService") WalletDomainService walletDomainService,
            WalletRepositoryPort walletRepositoryPort,
            IdempotencyRepositoryPort idempotencyRepositoryPort) {
        this.walletDomainService = walletDomainService;
        this.walletRepositoryPort = walletRepositoryPort;
        this.idempotencyRepositoryPort = idempotencyRepositoryPort;
    }

    @Override
    public CreditWalletResponse creditWallet(CreditWalletRequest creditWalletRequest) {
        if (idempotencyRepositoryPort.exists(Id.valueOf(creditWalletRequest.idempotencyKey()))) {
            throw new IllegalStateException("Duplicate credit creditWalletRequest");
        }
        Wallet wallet = walletRepositoryPort.findById(Id.valueOf(creditWalletRequest.walletId()));
        CreditOperation operation = CreditWalletMapper.toCreditOperation(creditWalletRequest, wallet);
        LedgerEntry ledgerEntry = walletDomainService.credit(operation);
        walletRepositoryPort.save(wallet);
        idempotencyRepositoryPort.store(Id.valueOf(creditWalletRequest.idempotencyKey()));
        return CreditWalletMapper.toCreditWalletResponse(ledgerEntry);
    }

}


