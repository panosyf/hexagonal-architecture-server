package com.hexagonal.server.account.core.unit.domain.account;

import com.hexagonal.server.account.core.domain.account.Account;
import com.hexagonal.server.account.core.exception.illegalargument.transaction.InsufficientBalanceException;
import com.hexagonal.server.shared.kernel.common.exception.constants.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.exception.utils.ErrorUtils;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.hexagonal.server.account.core.common.mock.account.AccountMock.generateAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    private static final Money AMOUNT_0 = Money.zero();
    private static final Money AMOUNT_1 = Money.of(BigDecimal.ONE);
    private static final Money AMOUNT_10 = Money.of(BigDecimal.TEN);
    private static final Money BALANCE_0 = Money.zero();
    private static final Money BALANCE_5 = Money.of(BigDecimal.valueOf(5));
    private static final Money BALANCE_10 = Money.of(BigDecimal.TEN);
    private static final Money BALANCE_15 = Money.of(BigDecimal.valueOf(15));
    private static final Money BALANCE_20 = Money.of(BigDecimal.valueOf(20));

    @Test
    void hasBalanceTest() {
        Account account = generateAccount(BALANCE_10);
        assertTrue(account.hasBalance());
    }

    @ParameterizedTest(name = "Account with balance {0} eligibility for transaction with amount {1} is {2}")
    @MethodSource("isBalanceEligibleForTransactionTestArguments")
    void isBalanceEligibleForTransactionTest(Money balance, Money amount, boolean result) {
        Account account = generateAccount(balance);
        assertThat(account.isBalanceEligibleForTransaction(amount)).isEqualTo(result);
    }

    private static Stream<Arguments> isBalanceEligibleForTransactionTestArguments() {
        return Stream.of(
                Arguments.of(BALANCE_5, AMOUNT_10, false),
                Arguments.of(BALANCE_0, AMOUNT_0, false),
                Arguments.of(BALANCE_5, AMOUNT_1, true)
        );
    }

    @ParameterizedTest
    @MethodSource("notEligibleBalanceForTransactionTestArguments")
    void notEligibleBalanceForTransactionTest(Money balance, Money amount, boolean result) {
        Account account = generateAccount(balance);
        assertThat(account.notEligibleBalanceForTransaction(amount)).isEqualTo(result);
    }

    private static Stream<Arguments> notEligibleBalanceForTransactionTestArguments() {
        return Stream.of(
                Arguments.of(BALANCE_5, AMOUNT_10, true),
                Arguments.of(BALANCE_0, AMOUNT_0, true),
                Arguments.of(BALANCE_5, AMOUNT_1, false)
        );
    }

    @Test
    void validateBalanceEligibleForTransactionTest() {
        Account account = generateAccount();
        assertThatThrownBy(() -> account.validateBalanceEligibleForTransaction(BALANCE_10))
                .isInstanceOf(InsufficientBalanceException.class)
                .hasMessage(ErrorUtils.generateErrorMessage(ErrorMessageConstants.INSUFFICIENT_BALANCE_EXCEPTION, account.getId().getValue()));
    }

    @Test
    void increaseBalanceTest() {
        Account account = generateAccount(BALANCE_5);
        account.increaseBalance(BALANCE_15);
        assertThat(account.getBalance().equals(BALANCE_20)).isTrue();
    }

    @Test
    void decreaseBalanceTest() {
        Account account = generateAccount(BALANCE_15);
        account.decreaseBalance(BALANCE_10);
        assertThat(account.getBalance().equals(BALANCE_5)).isTrue();
    }

    @Test
    void decreaseBalanceThrowsInsufficientBalanceExceptionTest() {
        Account account = generateAccount(BALANCE_5);
        assertThatThrownBy(() -> account.decreaseBalance(BALANCE_10))
                .isInstanceOf(InsufficientBalanceException.class);
    }

}
