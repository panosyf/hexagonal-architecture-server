package com.hexagonal.server.ledger.core.wallet.model.operation;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public record CreateWalletOperation(Id accountId) {
}
