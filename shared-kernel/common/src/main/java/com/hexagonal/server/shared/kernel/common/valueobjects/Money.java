package com.hexagonal.server.shared.kernel.common.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.constants.ErrorMessageConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public class Money extends ValueObject {

    private final BigDecimal value;
    private final Currency currency;
    private static final Money ZERO = new Money(BigDecimal.ZERO);

    private Money(final BigDecimal value) {
        this.value = Money.setScale(value);
        this.currency = Currency.getInstance("EUR");
    }

    private Money(final BigDecimal value, final Currency currency) {
        this.value = Money.setScale(value);
        this.currency = currency;
    }

    public static Money of(final BigDecimal amount) {
        BigDecimal finalAmount = amount == null ? BigDecimal.ZERO : amount;
        return new Money(finalAmount);
    }

    public static Money of(final BigDecimal amount, final Currency currency) {
        BigDecimal finalAmount = amount == null ? BigDecimal.ZERO : amount;
        return new Money(finalAmount, currency);
    }

    public static Money zero() {
        return ZERO;
    }

    private static BigDecimal setScale(final BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public Money add(final Money money) {
        return new Money(this.value.add(money.value));
    }

    public Money subtract(final Money money) {
        if (this.value.compareTo(money.value) < 0)
            throw new IllegalArgumentException(ErrorMessageConstants.SUBTRACTION_OPERATION_BETWEEN_GIVEN_VALUES_RETURNS_NEGATIVE_RESULTS);
        return new Money(this.value.subtract(money.value));
    }

    public boolean isSubtractedResultNegative(final Money money) {
        return this.value.compareTo(money.value) < 0;
    }

    public Money multiply(final BigDecimal multiplier) {
        if (multiplier == null) throw new IllegalArgumentException(ErrorMessageConstants.MULTIPLIER_CANNOT_BE_NULL);
        if (multiplier.compareTo(BigDecimal.ZERO) == 0)
            throw new IllegalArgumentException(ErrorMessageConstants.MULTIPLIER_CANNOT_BE_ZERO);
        if (multiplier.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(ErrorMessageConstants.MULTIPLIER_CANNOT_BE_NEGATIVE);
        return new Money(this.value.multiply(multiplier));
    }

    public Money divide(final BigDecimal divisor) {
        if (divisor == null) throw new IllegalArgumentException(ErrorMessageConstants.DIVISOR_CANNOT_BE_NULL);
        if (divisor.compareTo(BigDecimal.ZERO) == 0)
            throw new IllegalArgumentException(ErrorMessageConstants.DIVISOR_CANNOT_BE_ZERO);
        if (divisor.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(ErrorMessageConstants.DIVISOR_CANNOT_BE_NEGATIVE);
        return new Money(this.value.divide(divisor, 2, RoundingMode.HALF_EVEN));
    }

    public boolean isZero() {
        return this.value.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isGreaterThanZero() {
        return this.value.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isNegative() {
        return this.value.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isGreaterThan(final Money money) {
        return this.value.compareTo(money.value) > 0;
    }

    public boolean isGreaterThanOrEqual(final Money money) {
        return this.value.compareTo(money.value) >= 0;
    }

    public boolean isLessThan(final Money money) {
        return this.value.compareTo(money.value) < 0;
    }

    public boolean isLessThanOrEqual(final Money money) {
        return this.value.compareTo(money.value) <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

}
