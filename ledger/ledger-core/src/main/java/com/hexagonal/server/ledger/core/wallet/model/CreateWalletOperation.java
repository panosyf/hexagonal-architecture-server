package com.hexagonal.server.ledger.core.wallet.model;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public record CreateWalletOperation(Id accountId) {
}
