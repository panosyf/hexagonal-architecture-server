package com.hexagonal.server.account.application.unit.usecase.transaction;

import com.hexagonal.server.account.application.usecase.account.AccountUsecase;
import com.hexagonal.server.account.application.usecase.transaction.TransactionUsecase;
import com.hexagonal.server.account.application.usecase.transaction.TransactionUsecaseImpl;
import com.hexagonal.server.account.application.common.constant.transaction.TransactionId;
import com.hexagonal.server.account.application.common.mock.transaction.TransactionCreateRequestMock;
import com.hexagonal.server.account.application.common.mock.transaction.TransactionMock;
import com.hexagonal.server.account.application.common.mock.transaction.TransactionUpdateRequestMock;
import com.hexagonal.server.account.application.converter.transaction.in.TransactionCreateRequestToOperation;
import com.hexagonal.server.account.application.converter.transaction.out.TransactionToDto;
import com.hexagonal.server.account.application.model.request.transaction.TransactionCreateRequest;
import com.hexagonal.server.account.application.model.request.transaction.TransactionUpdateRequest;
import com.hexagonal.server.account.application.model.response.transaction.TransactionCreationResponse;
import com.hexagonal.server.account.core.domain.transaction.Transaction;
import com.hexagonal.server.account.core.exception.illegalargument.transaction.InsufficientBalanceException;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.service.transaction.TransactionDomainService;
import com.hexagonal.server.account.core.model.operation.transaction.CreateTransactionOperation;
import com.hexagonal.server.account.core.model.operation.transaction.UpdateTransactionOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.core.convert.support.GenericConversionService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class TransactionUsecaseTest {

    private final TransactionDomainService transactionDomainService = mock(TransactionDomainService.class);
    private final AccountUsecase accountUsecase = mock(AccountUsecase.class);
    private final GenericConversionService genericConversionService = new GenericConversionService();
    private TransactionUsecase transactionUsecase;
    private final ArgumentCaptor<UpdateTransactionOperation> updateTransactionOperationCaptor = ArgumentCaptor.forClass(UpdateTransactionOperation.class);

    @BeforeEach
    void init() {
        genericConversionService.addConverter(new TransactionToDto());
        genericConversionService.addConverter(new TransactionCreateRequestToOperation());
        transactionUsecase = new TransactionUsecaseImpl(transactionDomainService, accountUsecase, genericConversionService);
    }

    @Test
    void createTransactionTest() {
        // given
        TransactionCreateRequest transactionCreateRequest = TransactionCreateRequestMock.generateTransactionCreateRequest();
        Transaction transaction = TransactionMock.generateTransaction();
        given(transactionDomainService.createTransaction(any(CreateTransactionOperation.class)))
                .willReturn(transaction);
        // when
        TransactionCreationResponse transactionCreationResponse = transactionUsecase.createTransaction(transactionCreateRequest);
        // Then
        assertThat(transactionCreationResponse.status()).isEqualTo(TransactionStatusEnum.PENDING);
    }

    @Test
    void createTransactionFailedTest() {
        // given
        TransactionCreateRequest transactionCreateRequest = TransactionCreateRequestMock.generateTransactionCreateRequest();
        Transaction transaction = TransactionMock.generateTransaction();
        given(transactionDomainService.createTransaction(any(CreateTransactionOperation.class)))
                .willReturn(transaction);
        doThrow(new InsufficientBalanceException(transaction.getDebtorAccountId().getValue()))
                .when(accountUsecase)
                .decreaseBalance(anyString(), any(BigDecimal.class));
        // when
        TransactionCreationResponse transactionCreationResponse = transactionUsecase.createTransaction(transactionCreateRequest);
        // Then
        assertThat(transactionCreationResponse.status()).isEqualTo(TransactionStatusEnum.FAILED);
    }

    @Test
    void updateTransactionTest() {
        // given
        TransactionUpdateRequest transactionUpdateRequest = TransactionUpdateRequestMock.generateTransactionUpdateRequest();
        Transaction transaction = TransactionMock.generateTransaction();
        given(transactionDomainService.updateTransaction(any(UpdateTransactionOperation.class)))
                .willReturn(transaction);
        // when
        transactionUsecase.updateTransaction(TransactionId.TRANSACTION_ID_1.getValue(), transactionUpdateRequest);
        // Then
        verify(transactionDomainService, times(1))
                .updateTransaction(updateTransactionOperationCaptor.capture());
        assertThat(updateTransactionOperationCaptor.getValue().transactionStatusEnum()).isEqualTo(TransactionStatusEnum.COMPLETED);
    }

}
