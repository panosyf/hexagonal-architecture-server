package com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters;

import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;

@Converter
public class TimestampAttributeConverter implements AttributeConverter<Timestamp, Instant> {

    @Override
    public Instant convertToDatabaseColumn(Timestamp timestamp) {
        return timestamp.getTime();
    }

    @Override
    public Timestamp convertToEntityAttribute(Instant instant) {
        return Timestamp.valueOf(instant);
    }

}
