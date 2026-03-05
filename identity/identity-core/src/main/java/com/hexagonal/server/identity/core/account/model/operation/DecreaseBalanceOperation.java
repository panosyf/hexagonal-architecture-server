package com.hexagonal.server.identity.core.account.model.operation;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public record DecreaseBalanceOperation(Id id, Money amount) {
}
