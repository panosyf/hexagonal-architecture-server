package com.hexagonal.server.shared.kernel.common.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;

import java.util.Objects;

public class Password extends ValueObject {

    private final String value;

    private Password(final String value) {
        this.value = value;
    }

    public static Password valueOf(final String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException(ErrorMessageConstants.PASSWORD_CANNOT_BE_NULL_OR_BLANK);
        return new Password(value);
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "Password{" +
                "value='" + value + '\'' +
                '}';
    }

}
