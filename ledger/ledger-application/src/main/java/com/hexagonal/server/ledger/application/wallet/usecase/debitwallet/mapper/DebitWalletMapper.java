package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.mapper;

import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request.DebitWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response.DebitWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.DebitOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public final class DebitWalletMapper {

    private DebitWalletMapper() {
    }

    public static DebitOperation toDebitOperation(DebitWalletRequest debitWalletRequest, Wallet wallet) {
        return new DebitOperation(
                wallet,
                Money.of(debitWalletRequest.amount()),
                Description.valueOf(debitWalletRequest.reference()));
    }

    public static DebitWalletResponse toDebitWalletResponse(LedgerEntry ledgerEntry) {
        return new DebitWalletResponse(
                ledgerEntry.getId().getValue(),
                ledgerEntry.getWalletId().getValue(),
                ledgerEntry.getAmount().getValue(),
                ledgerEntry.getCreatedAt().getTime(),
                ledgerEntry.getReference().getValue());
    }

}

