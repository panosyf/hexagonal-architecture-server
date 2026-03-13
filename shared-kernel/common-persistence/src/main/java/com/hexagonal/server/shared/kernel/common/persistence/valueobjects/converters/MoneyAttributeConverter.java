package com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters;

import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.math.BigDecimal;

@Converter
public class MoneyAttributeConverter implements AttributeConverter<Money, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(Money money) {
        return money.getValue();
    }

    @Override
    public Money convertToEntityAttribute(BigDecimal bigDecimal) {
        return Money.of(bigDecimal);
    }

}
