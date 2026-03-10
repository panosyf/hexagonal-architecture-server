package com.hexagonal.server.shared.kernel.common.unit.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.valueobjects.Name;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NameTest {

    private static final String firstName = "firstName";
    private static final String lastName = "lastName";
    private static final String newFirstName = "newFirstName";
    private static final String newLastName = "newLastName";

    @Test
    void valueOfTest() {
        Name name = Name.valueOf(firstName, lastName);
        assertThat(String.join(" ", firstName, lastName))
                .isEqualTo(name.getFullName());
        name.setFirstName(newFirstName);
        name.setLastName(newLastName);
        assertThat(String.join(" ", newFirstName, newLastName))
                .isEqualTo(name.getFullName());
        assertThat(newFirstName).isEqualTo(name.getFirstName());
        assertThat(newLastName).isEqualTo(name.getLastName());
    }

    @Test
    void valueOfFullNameTest() {
        String fullName = String.join(" ", firstName, lastName);
        Name name = Name.valueOf(fullName);
        assertThat(fullName)
                .isEqualTo(name.getFullName());
        name.setFirstName(newFirstName);
        name.setLastName(newLastName);
        assertThat(String.join(" ", newFirstName, newLastName))
                .isEqualTo(name.getFullName());
        assertThat(newFirstName).isEqualTo(name.getFirstName());
        assertThat(newLastName).isEqualTo(name.getLastName());
    }

    @ParameterizedTest
    @MethodSource("validateValueOfTestArguments")
    void validateValueOfTest(String firstName, String lastName) {
        assertThatThrownBy(() -> Name.valueOf(firstName, lastName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> validateValueOfTestArguments() {
        return Stream.of(
                Arguments.of("", lastName),
                Arguments.of("         ", lastName),
                Arguments.of(null, lastName),
                Arguments.of(firstName, ""),
                Arguments.of(firstName, "         "),
                Arguments.of(firstName, null)
        );
    }

    @ParameterizedTest
    @MethodSource("validateValueOfFullNameTest")
    void validateValueOfFullNameTest(String fullName) {
        assertThatThrownBy(() -> Name.valueOf(fullName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessageConstants.FULL_NAME_CANNOT_BE_NULL_OR_BLANK);
    }

    private static Stream<Arguments> validateValueOfFullNameTest() {
        return Stream.of(
                Arguments.of("         "),
                Arguments.of(""),
                Arguments.of((Object) null)
        );
    }

    @ParameterizedTest
    @MethodSource("validateSetFirstNameTestArguments")
    void validateSetFirstNameTest(String newFirstName) {
        Name name = Name.valueOf(firstName, lastName);
        assertThatThrownBy(() -> name.setFirstName(newFirstName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessageConstants.FIRST_NAME_CANNOT_BE_NULL_OR_BLANK);
    }

    private static Stream<Arguments> validateSetFirstNameTestArguments() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("         "),
                Arguments.of((Object) null)
        );
    }

    @ParameterizedTest
    @MethodSource("validateSetLastNameTestArguments")
    void validateSetLastNameTest(String newLastName) {
        Name name = Name.valueOf(firstName, lastName);
        assertThatThrownBy(() -> name.setLastName(newLastName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessageConstants.LAST_NAME_ID_CANNOT_BE_NULL_OR_BLANK);
    }

    private static Stream<Arguments> validateSetLastNameTestArguments() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("         "),
                Arguments.of((Object) null)
        );
    }

    @ParameterizedTest
    @MethodSource("validateValueOfFullNameArraySizeTest")
    void validateValueOfFullNameArraySizeTest(String fullName) {
        assertThatThrownBy(() -> Name.valueOf(fullName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessageConstants.FULL_NAME_ARRAY_MUST_HAVE_SIZE_OF_TWO);
    }

    private static Stream<Arguments> validateValueOfFullNameArraySizeTest() {
        return Stream.of(
                Arguments.of(firstName),
                Arguments.of(firstName + lastName)
        );
    }

}
