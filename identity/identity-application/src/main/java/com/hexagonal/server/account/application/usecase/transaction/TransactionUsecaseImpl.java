package com.hexagonal.server.account.application.usecase.transaction;

import com.hexagonal.server.account.application.usecase.account.AccountUsecase;
import com.hexagonal.server.account.application.model.dto.transaction.TransactionDto;
import com.hexagonal.server.account.application.model.request.transaction.TransactionCreateRequest;
import com.hexagonal.server.account.application.model.request.transaction.TransactionUpdateRequest;
import com.hexagonal.server.account.application.model.response.transaction.TransactionCreationResponse;
import com.hexagonal.server.account.application.model.response.transaction.TransactionResponse;
import com.hexagonal.server.account.application.model.response.transaction.TransactionUpdateResponse;
import com.hexagonal.server.account.core.domain.transaction.Transaction;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.service.transaction.TransactionDomainService;
import com.hexagonal.server.account.core.model.operation.transaction.CreateTransactionOperation;
import com.hexagonal.server.account.core.model.operation.transaction.GetTransactionOperation;
import com.hexagonal.server.account.core.model.operation.transaction.UpdateTransactionOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.springframework.core.convert.ConversionService;

public class TransactionUsecaseImpl implements TransactionUsecase {

    private final TransactionDomainService transactionDomainService;
    private final AccountUsecase accountUsecase;
    private final ConversionService conversionService;

    public TransactionUsecaseImpl(
            TransactionDomainService transactionDomainService,
            AccountUsecase accountUsecase,
            ConversionService conversionService) {
        this.transactionDomainService = transactionDomainService;
        this.accountUsecase = accountUsecase;
        this.conversionService = conversionService;
    }

    @Override
    public TransactionResponse getTransaction(String id) {
        GetTransactionOperation getTransactionOperation = new GetTransactionOperation(Id.valueOf(id));
        Transaction transaction = transactionDomainService.getTransaction(getTransactionOperation);
        TransactionDto transactionDto = conversionService.convert(transaction, TransactionDto.class);
        return new TransactionResponse(transactionDto);
    }

    @Override
    public TransactionCreationResponse createTransaction(TransactionCreateRequest transactionCreateRequest) {
        CreateTransactionOperation createTransactionOperation = conversionService.convert(transactionCreateRequest, CreateTransactionOperation.class);
        Transaction transaction = transactionDomainService.createTransaction(createTransactionOperation);
        Id debtorAccountId = transaction.getDebtorAccountId();
        Money amount = transaction.getAmount();
        try {
            accountUsecase.decreaseBalance(debtorAccountId.getValue(), amount.getValue());
        } catch (Exception e) {
            UpdateTransactionOperation updateTransactionOperation = new UpdateTransactionOperation(transaction.getId(), TransactionStatusEnum.FAILED);
            transactionDomainService.updateTransaction(updateTransactionOperation);
            return new TransactionCreationResponse(null, TransactionStatusEnum.FAILED);
        }
        return new TransactionCreationResponse(transaction.getId().getValue(), TransactionStatusEnum.PENDING);
    }

    @Override
    public TransactionUpdateResponse updateTransaction(String id, TransactionUpdateRequest transactionUpdateRequest) {
        UpdateTransactionOperation updateTransactionOperation = new UpdateTransactionOperation(Id.valueOf(id), transactionUpdateRequest.transactionStatusEnum());
        Transaction updatedTransaction = transactionDomainService.updateTransaction(updateTransactionOperation);
        // TODO THIS IS TEMPORARY, WILL BE REFACTORED UTILIZING STATE PATTERN
        if (updateTransactionOperation.transactionStatusEnum().equals(TransactionStatusEnum.COMPLETED)) {
            accountUsecase.increaseBalance(updatedTransaction.getBeneficiaryAccountId().getValue(), updatedTransaction.getAmount().getValue());
        }
        return new TransactionUpdateResponse(updatedTransaction.getId().getValue(), updatedTransaction.getStatus());
    }

}
