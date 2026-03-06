package com.hexagonal.server.ledger.core.wallet.model.operation;

import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public record CreditOperation(Wallet wallet, Money amount, String reference) {
}
