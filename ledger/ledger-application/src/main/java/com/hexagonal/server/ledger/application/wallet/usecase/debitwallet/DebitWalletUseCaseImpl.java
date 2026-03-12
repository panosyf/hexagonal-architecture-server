package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet;

import com.hexagonal.server.ledger.application.wallet.port.out.repository.LedgerEntryRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.port.out.repository.WalletRepositoryPort;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.mapper.DebitWalletMapper;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request.DebitWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response.DebitWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.DebitOperation;
import com.hexagonal.server.ledger.core.wallet.service.WalletDomainService;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;

public class DebitWalletUseCaseImpl implements DebitWalletUseCase {

    private final WalletDomainService walletDomainService;
    private final WalletRepositoryPort walletRepositoryPort;
    private final LedgerEntryRepositoryPort ledgerEntryRepositoryPort;

    public DebitWalletUseCaseImpl(
            @Qualifier("walletDomainService") WalletDomainService walletDomainService,
            WalletRepositoryPort walletRepositoryPort,
            LedgerEntryRepositoryPort ledgerEntryRepositoryPort) {
        this.walletDomainService = walletDomainService;
        this.walletRepositoryPort = walletRepositoryPort;
        this.ledgerEntryRepositoryPort = ledgerEntryRepositoryPort;
    }

    @Override
    @Transactional
    public DebitWalletResponse debitWallet(String id, DebitWalletRequest debitWalletRequest) {
        Wallet wallet = walletRepositoryPort.findById(Id.valueOf(id));
        DebitOperation debitOperation = DebitWalletMapper.toDebitOperation(debitWalletRequest, wallet);
        LedgerEntry ledgerEntry = walletDomainService.debit(debitOperation);
        walletRepositoryPort.save(wallet);
        ledgerEntryRepositoryPort.save(ledgerEntry);
        return DebitWalletMapper.toDebitWalletResponse(ledgerEntry);
    }

}
