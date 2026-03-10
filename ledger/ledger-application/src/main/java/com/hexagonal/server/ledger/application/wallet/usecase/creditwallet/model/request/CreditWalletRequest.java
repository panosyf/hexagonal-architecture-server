package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request;

import java.math.BigDecimal;

public record CreditWalletRequest(
        BigDecimal amount,
        String reference
) {
}

