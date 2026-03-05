package com.hexagonal.server.identity.core.core.model.operation.account;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public record DecreaseBalanceOperation(Id id, Money amount) {
}
