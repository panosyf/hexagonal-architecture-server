package com.hexagonal.server.shared.kernel.common.persistence.valueobjects.converters;

import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DescriptionAttributeConverter implements AttributeConverter<Description, String> {

    @Override
    public String convertToDatabaseColumn(Description description) {
        return description.getValue();
    }

    @Override
    public Description convertToEntityAttribute(String s) {
        return Description.valueOf(s);
    }

}
