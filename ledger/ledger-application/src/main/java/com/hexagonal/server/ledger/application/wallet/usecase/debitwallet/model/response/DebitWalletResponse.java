package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response;

import java.math.BigDecimal;

public record DebitWalletResponse(
        String ledgerEntryId,
        String walletId,
        BigDecimal amount,
        String reference
) {
}

