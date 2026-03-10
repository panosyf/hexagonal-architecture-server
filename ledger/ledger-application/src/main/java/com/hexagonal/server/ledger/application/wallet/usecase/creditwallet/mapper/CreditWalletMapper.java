package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.mapper;

import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreditOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public final class CreditWalletMapper {

    private CreditWalletMapper() {
    }

    public static CreditOperation toCreditOperation(CreditWalletRequest creditWalletRequest, Wallet wallet) {
        return new CreditOperation(
                wallet,
                Money.of(creditWalletRequest.amount()),
                creditWalletRequest.reference());
    }

    public static CreditWalletResponse toCreditWalletResponse(LedgerEntry ledgerEntry) {
        return new CreditWalletResponse(
                ledgerEntry.getId().getValue(),
                ledgerEntry.getWalletId().getValue(),
                ledgerEntry.getAmount().getValue(),
                ledgerEntry.getReference());
    }
}

