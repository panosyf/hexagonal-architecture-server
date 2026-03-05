package com.hexagonal.server.account.core.unit.service.transaction;


import com.hexagonal.server.account.core.common.constant.transaction.TransactionId;
import com.hexagonal.server.account.core.domain.transaction.Transaction;
import com.hexagonal.server.account.core.exception.elementnotfound.transaction.TransactionNotFoundException;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionStatusEnum;
import com.hexagonal.server.account.core.model.enums.transaction.TransactionType;
import com.hexagonal.server.account.core.model.operation.transaction.CreateTransactionOperation;
import com.hexagonal.server.account.core.model.operation.transaction.GetTransactionOperation;
import com.hexagonal.server.account.core.model.operation.transaction.UpdateTransactionOperation;
import com.hexagonal.server.account.core.port.out.transaction.TransactionRepositoryPort;
import com.hexagonal.server.account.core.service.transaction.TransactionDomainService;
import com.hexagonal.server.account.core.service.transaction.TransactionDomainServiceImpl;
import com.hexagonal.server.shared.kernel.common.exception.constants.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.exception.utils.ErrorUtils;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;

import static com.hexagonal.server.account.core.common.mock.transaction.CreateTransactionOperationMock.generateCreateTransactionOperation;
import static com.hexagonal.server.account.core.common.mock.transaction.GetTransactionOperationMock.generateGetTransactionOperation;
import static com.hexagonal.server.account.core.common.mock.transaction.TransactionMock.generatePendingTransaction;
import static com.hexagonal.server.account.core.common.mock.transaction.TransactionMock.generateTransaction;
import static com.hexagonal.server.account.core.common.mock.transaction.UpdateTransactionOperationMock.generateUpdateTransactionOperation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class TransactionDomainServiceTest {

    private final TransactionRepositoryPort transactionRepositoryPort = mock(TransactionRepositoryPort.class);
    private TransactionDomainService transactionDomainService;
    private final ArgumentCaptor<Transaction> transactionCaptor = ArgumentCaptor.forClass(Transaction.class);
    private static final Money AMOUNT_5 = Money.of(BigDecimal.valueOf(5));

    @BeforeEach
    void init() {
        transactionDomainService = new TransactionDomainServiceImpl(transactionRepositoryPort);
    }

    @Test
    void getTransactionTest() {
        // given
        Timestamp now = Timestamp.now().minusNanos(100);
        Transaction transaction = generateTransaction();
        GetTransactionOperation getTransactionOperation = generateGetTransactionOperation();
        given(transactionRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willReturn(transaction);
        // when
        Transaction transactionResult = transactionDomainService.getTransaction(getTransactionOperation);
        // then
        verify(transactionRepositoryPort, times(1))
                .findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class));
        assertAll(
                () -> assertEquals(TransactionType.TRANSFER, transactionResult.getType()),
                () -> assertEquals(AMOUNT_5, transactionResult.getAmount()),
                () -> assertEquals(Description.emptyDescription(), transactionResult.getDescription()),
                () -> assertEquals(TransactionId.ACCOUNT_ID_1, transactionResult.getDebtorAccountId()),
                () -> assertEquals(TransactionId.ACCOUNT_ID_2, transactionResult.getBeneficiaryAccountId()),
                () -> assertEquals(TransactionStatusEnum.PENDING, transactionResult.getStatus()),
                () -> assertThat(now.isBefore(transactionResult.getCreatedAt())).isTrue(),
                () -> assertThat(now.isBefore(transactionResult.getUpdatedAt())).isTrue(),
                () -> assertThat(transactionResult.getCreatedAt().equals(transactionResult.getUpdatedAt())).isTrue()
        );
    }

    @Test
    void getTransactionThrowsTransactionNotFoundExceptionTest() {
        // given
        GetTransactionOperation getTransactionOperation = generateGetTransactionOperation();
        given(transactionRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willThrow(new TransactionNotFoundException(TransactionId.TRANSACTION_ID_1.getValue()));
        // then
        assertThatThrownBy(() -> transactionDomainService.getTransaction(getTransactionOperation))
                .isInstanceOf(TransactionNotFoundException.class)
                .hasMessage(ErrorUtils.generateErrorMessage(ErrorMessageConstants.TRANSACTION_NOT_FOUND_EXCEPTION, TransactionId.TRANSACTION_ID_1.getValue()));
    }

    @Test
    void createTransactionTest() {
        // given
        CreateTransactionOperation createTransactionOperation = generateCreateTransactionOperation();
        // when
        transactionDomainService.createTransaction(createTransactionOperation);
        // then
        verify(transactionRepositoryPort, times(1))
                .save(transactionCaptor.capture());
        Transaction createdTransaction = transactionCaptor.getValue();
        assertAll(
                () -> assertEquals(TransactionType.TRANSFER, createdTransaction.getType()),
                () -> assertEquals(AMOUNT_5, createdTransaction.getAmount()),
                () -> assertEquals(Description.emptyDescription(), createdTransaction.getDescription()),
                () -> assertEquals(TransactionId.ACCOUNT_ID_1, createdTransaction.getDebtorAccountId()),
                () -> assertEquals(TransactionId.ACCOUNT_ID_2, createdTransaction.getBeneficiaryAccountId())
        );

    }

    @Test
    void updateTransactionTest() {
        // given
        UpdateTransactionOperation updateTransactionOperation = generateUpdateTransactionOperation();
        Transaction transaction = generatePendingTransaction(TransactionId.TRANSACTION_ID_1);
        given(transactionRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willReturn(transaction);
        // when
        transactionDomainService.updateTransaction(updateTransactionOperation);
        // then
        verify(transactionRepositoryPort, times(1))
                .updateStatus(transactionCaptor.capture());
        assertThat(transactionCaptor.getValue().getStatus()).isEqualTo(TransactionStatusEnum.COMPLETED);
    }

    @Test
    void updateTransactionThrowsTransactionNotFoundExceptionTest() {
        // given
        UpdateTransactionOperation updateTransactionOperation = generateUpdateTransactionOperation();
        given(transactionRepositoryPort.findById(any(com.hexagonal.server.shared.kernel.common.valueobjects.Id.class)))
                .willThrow(new TransactionNotFoundException(TransactionId.TRANSACTION_ID_1.getValue()));
        // then
        assertThatThrownBy(() -> transactionDomainService.updateTransaction(updateTransactionOperation))
                .isInstanceOf(TransactionNotFoundException.class)
                .hasMessage(ErrorUtils.generateErrorMessage(ErrorMessageConstants.TRANSACTION_NOT_FOUND_EXCEPTION, TransactionId.TRANSACTION_ID_1.getValue()));
    }

}
