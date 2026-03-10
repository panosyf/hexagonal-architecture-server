package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response;

import java.math.BigDecimal;

public record CreditWalletResponse(
        String ledgerEntryId,
        String walletId,
        BigDecimal amount,
        String reference
) {}

