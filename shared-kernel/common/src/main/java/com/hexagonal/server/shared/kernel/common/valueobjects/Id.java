package com.hexagonal.server.shared.kernel.common.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;

import java.util.Objects;
import java.util.UUID;

public class Id extends ValueObject {

    private final String value;

    private Id(final String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(ErrorMessageConstants.ID_CANNOT_BE_NULL_OR_BLANK);
        this.value = value;
    }

    public static Id generate() {
        return new Id(UUID.randomUUID().toString());
    }

    public static Id valueOf(final UUID uuid) {
        return new Id(uuid.toString());
    }

    public static Id valueOf(final String value) {
        return new Id(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Id{" +
                "value='" + value + '\'' +
                '}';
    }

}
