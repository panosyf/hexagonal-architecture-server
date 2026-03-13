package com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters;

import com.hexagonal.server.shared.kernel.common.valueobjects.Name;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class NameAttributeConverter implements AttributeConverter<Name, String> {

    @Override
    public String convertToDatabaseColumn(Name name) {
        return name.getFullName();
    }

    @Override
    public Name convertToEntityAttribute(String fullName) {
        return Name.valueOf(fullName);
    }

}
