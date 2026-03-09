package com.hexagonal.server.ledger.core.wallet.model;

import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public record DebitOperation(Wallet wallet, Money amount, String reference) {
}
