package com.hexagonal.server.ledger.core.wallet.service;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreateWalletOperation;
import com.hexagonal.server.ledger.core.wallet.model.CreditOperation;
import com.hexagonal.server.ledger.core.wallet.model.DebitOperation;

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
