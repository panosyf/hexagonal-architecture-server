package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.LedgerEntryRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.mapper.CreditWalletMapper;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreditOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;

public class CreditWalletUseCaseImpl implements CreditWalletUseCase {

    private final WalletDomainService walletDomainService;
    private final WalletRepositoryPort walletRepositoryPort;
    private final LedgerEntryRepositoryPort ledgerEntryRepositoryPort;

    public CreditWalletUseCaseImpl(
            @Qualifier("walletDomainService") WalletDomainService walletDomainService,
            WalletRepositoryPort walletRepositoryPort,
            LedgerEntryRepositoryPort ledgerEntryRepositoryPort) {
        this.walletDomainService = walletDomainService;
        this.walletRepositoryPort = walletRepositoryPort;
        this.ledgerEntryRepositoryPort = ledgerEntryRepositoryPort;
    }

    @Override
    @Transactional
    public CreditWalletResponse creditWallet(String id, CreditWalletRequest creditWalletRequest) {
        Wallet wallet = walletRepositoryPort.findById(Id.valueOf(id));
        CreditOperation operation = CreditWalletMapper.toCreditOperation(creditWalletRequest, wallet);
        LedgerEntry ledgerEntry = walletDomainService.credit(operation);
        walletRepositoryPort.save(wallet);
        ledgerEntryRepositoryPort.save(ledgerEntry);
        return CreditWalletMapper.toCreditWalletResponse(ledgerEntry);
    }

}
