package com.hexagonal.server.identity.application.application.model.request.transaction;

import com.hexagonal.server.identity.core.core.model.enums.transaction.TransactionType;

import java.math.BigDecimal;

public record TransactionCreateRequest(
        TransactionType type,
        BigDecimal amount,
        String description,
        String debtorAccountId,
        String beneficiaryAccountId) {
}
