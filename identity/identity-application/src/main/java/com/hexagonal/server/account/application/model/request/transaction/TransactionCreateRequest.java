package com.hexagonal.server.account.application.model.request.transaction;

import com.hexagonal.server.account.core.model.enums.transaction.TransactionType;

import java.math.BigDecimal;

public record TransactionCreateRequest(
        TransactionType type,
        BigDecimal amount,
        String description,
        String debtorAccountId,
        String beneficiaryAccountId) {
}
