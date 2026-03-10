package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request;

import java.math.BigDecimal;

public record CreditWalletRequest(
        String walletId,
        BigDecimal amount,
        String reference,
        String idempotencyKey
) {}

