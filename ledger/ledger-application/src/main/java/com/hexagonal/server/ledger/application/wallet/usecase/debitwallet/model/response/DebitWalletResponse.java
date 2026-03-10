package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response;

import java.math.BigDecimal;
import java.time.Instant;

public record DebitWalletResponse(
        String ledgerEntryId,
        String walletId,
        BigDecimal amount,
        Instant createdAt,
        String reference
) {
}

