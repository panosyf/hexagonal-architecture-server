package com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request;

import java.math.BigDecimal;

public record DebitWalletRequest(
        String walletId,
        BigDecimal amount,
        String reference
) {
}
