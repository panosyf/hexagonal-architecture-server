package com.hexagonal.server.account.application.converter.transaction.out;

import com.hexagonal.server.account.application.model.dto.transaction.TransactionDto;
import com.hexagonal.server.account.core.domain.transaction.Transaction;
import org.springframework.core.convert.converter.Converter;

public class TransactionToDto implements Converter<Transaction, TransactionDto> {

    @Override
    public TransactionDto convert(Transaction transaction) {
        return new TransactionDto(
                transaction.getId().getValue(),
                transaction.getType(),
                transaction.getAmount().getValue(),
                transaction.getDescription().getValue(),
                transaction.getDebtorAccountId().getValue(),
                transaction.getBeneficiaryAccountId().getValue(),
                transaction.getStatus(),
                transaction.getCreatedAt().getTime(),
                transaction.getUpdatedAt().getTime()
        );
    }

}
