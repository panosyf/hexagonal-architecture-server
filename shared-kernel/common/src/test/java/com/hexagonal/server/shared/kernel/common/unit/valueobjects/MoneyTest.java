package com.hexagonal.server.shared.kernel.common.unit.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {

    private final BigDecimal BIG_DECIMAL_8 = BigDecimal.valueOf(8);
    private final BigDecimal BIG_DECIMAL_12 = BigDecimal.valueOf(12);
    private final Money MONEY_2 = Money.of(BigDecimal.TWO);
    private final Money MONEY_8 = Money.of(BIG_DECIMAL_8);
    private final Money MONEY_10 = Money.of(BigDecimal.TEN);
    private final Money MONEY_12 = Money.of(BIG_DECIMAL_12);
    private final Money MONEY_MINUS_2 = Money.of(BigDecimal.TWO.negate());
    private final Money MONEY_USD_10 = Money.of(BigDecimal.TEN, Currency.getInstance("USD"));

    @ParameterizedTest
    @MethodSource("moneyOfTestArguments")
    void moneyOfTest(BigDecimal value, Money expectedMoney) {
        Money money = Money.of(value);
        assertThat(money.equals(expectedMoney)).isTrue();
        assertThat(money.getValue()).isEqualTo(value.setScale(2, RoundingMode.HALF_EVEN));
    }

    private static Stream<Arguments> moneyOfTestArguments() {
        return Stream.of(
                Arguments.of(BigDecimal.TEN, Money.of(BigDecimal.TEN)),
                Arguments.of(BigDecimal.ZERO, Money.of(BigDecimal.ZERO))
        );
    }

    @Test
    void moneyOfCurrencyTest() {
        BigDecimal value = BigDecimal.TEN;
        Currency usdCurrency = Currency.getInstance("USD");
        Money money = Money.of(value, usdCurrency);
        assertThat(money.equals(MONEY_USD_10)).isTrue();
        assertThat(money.getCurrency().equals(usdCurrency)).isTrue();
        assertThat(money.getValue()).isEqualTo(value.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void moneyOfNullTest() {
        Money money = Money.of(null);
        assertThat(money.equals(Money.zero())).isTrue();
        assertThat(money.getValue())
                .isEqualTo(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void addTest() {
        Money added = MONEY_2.add(MONEY_10);
        assertThat(added.equals(MONEY_12)).isTrue();
        assertThat(added.getValue())
                .isEqualTo(BIG_DECIMAL_12.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void subtractTest() {
        Money subtracted = MONEY_10.subtract(MONEY_2);
        assertThat(subtracted.equals(MONEY_8)).isTrue();
        assertThat(subtracted.getValue())
                .isEqualTo(BIG_DECIMAL_8.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void isSubtractedResultNegative() {
        assertThat(MONEY_10.isSubtractedResultNegative(MONEY_2)).isFalse();
        assertThat(MONEY_2.isSubtractedResultNegative(MONEY_10)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("multiplyTestArguments")
    void multiplyTest(BigDecimal multiplier, Money result) {
        Money multiplied = MONEY_10.multiply(multiplier);
        assertThat(multiplied.equals(result)).isTrue();
        assertThat(multiplied.getValue())
                .isEqualTo(result.getValue().setScale(2, RoundingMode.HALF_EVEN));
    }

    private static Stream<Arguments> multiplyTestArguments() {
        return Stream.of(
                Arguments.of(BigDecimal.TWO, Money.of(BigDecimal.valueOf(20))),
                Arguments.of(BigDecimal.valueOf(2.5), Money.of(BigDecimal.valueOf(25)))
        );
    }

    @ParameterizedTest
    @MethodSource("multiplierExceptionsTestArguments")
    void multiplierExceptionsTest(BigDecimal multiplier, String errorMessage) {
        assertThatThrownBy(() -> MONEY_10.multiply(multiplier))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    private static Stream<Arguments> multiplierExceptionsTestArguments() {
        return Stream.of(
                Arguments.of(null, ErrorMessageConstants.MULTIPLIER_CANNOT_BE_NULL),
                Arguments.of(BigDecimal.ZERO, ErrorMessageConstants.MULTIPLIER_CANNOT_BE_ZERO),
                Arguments.of(BigDecimal.ONE.negate(), ErrorMessageConstants.MULTIPLIER_CANNOT_BE_NEGATIVE)
        );
    }

    @ParameterizedTest
    @MethodSource("divideTestArguments")
    void divideTest(BigDecimal divisor, Money result) {
        Money divided = MONEY_10.divide(divisor);
        assertThat(divided.equals(result)).isTrue();
        assertThat(divided.getValue())
                .isEqualTo(result.getValue().setScale(2, RoundingMode.HALF_EVEN));
    }

    private static Stream<Arguments> divideTestArguments() {
        return Stream.of(
                Arguments.of(BigDecimal.TWO, Money.of(BigDecimal.valueOf(5))),
                Arguments.of(BigDecimal.valueOf(4), Money.of(BigDecimal.valueOf(2.5))),
                Arguments.of(BigDecimal.valueOf(100), Money.of(BigDecimal.valueOf(0.1))),
                Arguments.of(BigDecimal.valueOf(40), Money.of(BigDecimal.valueOf(0.25)))
        );
    }

    @ParameterizedTest
    @MethodSource("divisorExceptionsTestArguments")
    void divisorExceptionsTest(BigDecimal divisor, String errorMessage) {
        assertThatThrownBy(() -> MONEY_10.divide(divisor))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(errorMessage);
    }

    private static Stream<Arguments> divisorExceptionsTestArguments() {
        return Stream.of(
                Arguments.of(null, ErrorMessageConstants.DIVISOR_CANNOT_BE_NULL),
                Arguments.of(BigDecimal.ZERO, ErrorMessageConstants.DIVISOR_CANNOT_BE_ZERO),
                Arguments.of(BigDecimal.ONE.negate(), ErrorMessageConstants.DIVISOR_CANNOT_BE_NEGATIVE)
        );
    }

    @Test
    void isZeroTest() {
        Money money = Money.zero();
        assertThat(money.isZero()).isTrue();
        assertThat(money.getValue())
                .isEqualTo(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    void isGreaterThanZeroTest() {
        assertThat(MONEY_2.isGreaterThanZero()).isTrue();
        assertThat(Money.zero().isGreaterThan(Money.zero())).isFalse();
    }

    @Test
    void isNegativeTest() {
        assertThat(MONEY_MINUS_2.isNegative()).isTrue();
        assertThat(MONEY_2.isNegative()).isFalse();
    }

    @Test
    void isGreaterThanTest() {
        assertThat(MONEY_10.isGreaterThan(MONEY_2)).isTrue();
        assertThat(MONEY_2.isGreaterThan(MONEY_10)).isFalse();
    }

    @Test
    void isGreaterThanOrEqualTest() {
        assertThat(MONEY_10.isGreaterThanOrEqual(MONEY_2)).isTrue();
        assertThat(MONEY_10.isGreaterThanOrEqual(MONEY_10)).isTrue();
        assertThat(MONEY_2.isGreaterThanOrEqual(MONEY_10)).isFalse();
    }

    @Test
    void isLessThanTest() {
        assertThat(MONEY_2.isLessThan(MONEY_10)).isTrue();
        assertThat(MONEY_10.isLessThan(MONEY_2)).isFalse();
    }

    @Test
    void isLessThanOrEqualTest() {
        assertThat(MONEY_2.isLessThanOrEqual(MONEY_10)).isTrue();
        assertThat(MONEY_2.isLessThanOrEqual(MONEY_2)).isTrue();
        assertThat(MONEY_10.isLessThanOrEqual(MONEY_2)).isFalse();

    }

}
