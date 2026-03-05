package com.hexagonal.server.identity.core.core.model.operation.account;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;

public record IncreaseBalanceOperation(Id id, Money amount) {
}
