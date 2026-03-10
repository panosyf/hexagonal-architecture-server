package com.hexagonal.server.shared.kernel.common.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;

import java.util.Objects;

public class Name extends ValueObject {

    private String firstName;
    private String lastName;

    private Name(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Name valueOf(final String firstName, final String lastName) {
        validateInputs(firstName, lastName);
        return new Name(firstName, lastName);
    }

    public static Name valueOf(final String fullName) {
        validateValueNotNull(fullName, ErrorMessageConstants.FULL_NAME_CANNOT_BE_NULL_OR_BLANK);
        String[] fullNameArray = generateFullNameArray(fullName);
        return new Name(fullNameArray[0], fullNameArray[1]);
    }

    private static String[] generateFullNameArray(String fullName) {
        String[] fullNameArray = fullName.split(" ", 2);
        if (fullNameArray.length < 2)
            throw new IllegalArgumentException(ErrorMessageConstants.FULL_NAME_ARRAY_MUST_HAVE_SIZE_OF_TWO);
        return fullNameArray;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        validateValueNotNull(firstName, ErrorMessageConstants.FIRST_NAME_CANNOT_BE_NULL_OR_BLANK);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        validateValueNotNull(lastName, ErrorMessageConstants.LAST_NAME_ID_CANNOT_BE_NULL_OR_BLANK);
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    private static void validateInputs(final String firstName, final String lastName) {
        validateValueNotNull(firstName, ErrorMessageConstants.FIRST_NAME_CANNOT_BE_NULL_OR_BLANK);
        validateValueNotNull(lastName, ErrorMessageConstants.LAST_NAME_ID_CANNOT_BE_NULL_OR_BLANK);
    }

    private static void validateValueNotNull(final String value, final String errorMessage) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
