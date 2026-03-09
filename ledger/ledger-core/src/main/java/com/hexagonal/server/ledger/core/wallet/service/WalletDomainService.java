package com.hexagonal.server.ledger.core.wallet.service;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreateWalletOperation;
import com.hexagonal.server.ledger.core.wallet.model.CreditOperation;
import com.hexagonal.server.ledger.core.wallet.model.DebitOperation;

public interface WalletDomainService {

    Wallet createWallet(CreateWalletOperation createWalletOperation);

    LedgerEntry credit(CreditOperation creditOperation);

    LedgerEntry debit(DebitOperation debitOperation);

}
