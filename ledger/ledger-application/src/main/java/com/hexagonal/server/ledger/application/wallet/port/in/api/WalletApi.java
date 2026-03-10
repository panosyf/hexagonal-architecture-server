package com.hexagonal.server.ledger.application.wallet.port.in.api;


import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public interface WalletApi {

    Id createWallet(Id accountId);

    LedgerEntry credit(Id walletId, Money amount, String reference, String idempotencyKey);

    LedgerEntry debit(Id walletId, Money amount, String reference, String idempotencyKey);
}

