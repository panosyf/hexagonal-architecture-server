package com.hexagonal.server.account.application.converter.transaction.in;

import com.hexagonal.server.account.application.model.request.transaction.TransactionCreateRequest;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionType;
import com.hexagonal.server.account.core.model.operation.transaction.CreateTransactionOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.springframework.core.convert.converter.Converter;

public class TransactionCreateRequestToOperation implements Converter<TransactionCreateRequest, CreateTransactionOperation> {

    @Override
    public CreateTransactionOperation convert(TransactionCreateRequest transactionCreateRequest) {
        TransactionType type = transactionCreateRequest.type();
        Money amount = Money.of(transactionCreateRequest.amount());
        Description description = Description.valueOf(transactionCreateRequest.description());
        Id debtorAccountId = Id.valueOf(transactionCreateRequest.debtorAccountId());
        Id beneficiaryAccountId = Id.valueOf(transactionCreateRequest.beneficiaryAccountId());
        return new CreateTransactionOperation(
                type,
                amount,
                description,
                debtorAccountId,
                beneficiaryAccountId,
                TransactionStatusEnum.PENDING);
    }

}
