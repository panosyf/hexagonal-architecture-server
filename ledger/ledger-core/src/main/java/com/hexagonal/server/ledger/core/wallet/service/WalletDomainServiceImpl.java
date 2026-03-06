package com.hexagonal.server.ledger.core.wallet.service;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.operation.CreateWalletOperation;
import com.hexagonal.server.ledger.core.wallet.model.operation.CreditOperation;
import com.hexagonal.server.ledger.core.wallet.model.operation.DebitOperation;

public class WalletDomainServiceImpl implements WalletDomainService {

    @Override
    public Wallet createWallet(CreateWalletOperation createWalletOperation) {
        return Wallet.create(createWalletOperation.accountId());
    }

    @Override
    public LedgerEntry credit(CreditOperation creditOperation) {
        Wallet wallet = creditOperation.wallet();
        return wallet.credit(creditOperation.amount(), creditOperation.reference());
    }

    @Override
    public LedgerEntry debit(DebitOperation debitOperation) {
        Wallet wallet = debitOperation.wallet();
        return wallet.debit(debitOperation.amount(), debitOperation.reference());
    }

}
