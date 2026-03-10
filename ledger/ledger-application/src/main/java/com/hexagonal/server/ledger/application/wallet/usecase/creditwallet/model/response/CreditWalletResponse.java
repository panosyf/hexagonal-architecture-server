package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response;

import java.math.BigDecimal;
import java.time.Instant;

public record CreditWalletResponse(
        String ledgerEntryId,
        String walletId,
        BigDecimal amount,
        Instant createdAt,
        String reference
) {}

