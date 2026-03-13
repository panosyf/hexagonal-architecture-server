package com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IdAttributeConverter implements AttributeConverter<Id, String> {

    @Override
    public String convertToDatabaseColumn(Id id) {
        return id.getValue();
    }

    @Override
    public Id convertToEntityAttribute(String s) {
        return Id.valueOf(s);
    }

}
