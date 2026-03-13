package com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters;

import com.hexagonal.server.shared.kernel.common.valueobjects.Email;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class EmailAttributeConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email email) {
        return email.getValue();
    }

    @Override
    public Email convertToEntityAttribute(String s) {
        return Email.valueOf(s);
    }

}
