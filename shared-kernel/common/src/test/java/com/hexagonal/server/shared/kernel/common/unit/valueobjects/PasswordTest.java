package com.hexagonal.server.shared.kernel.common.unit.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.valueobjects.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PasswordTest {

    @Test
    void valueOfTest() {
        String password = "password";
        assertThat(password)
                .isEqualTo(Password.valueOf(password).getValue());
    }

    @ParameterizedTest
    @MethodSource("validateValueTestArguments")
    void validateValueTest(String value) {
        assertThatThrownBy(() -> Password.valueOf(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessageConstants.PASSWORD_CANNOT_BE_NULL_OR_BLANK);
    }

    private static Stream<Arguments> validateValueTestArguments() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("         "),
                Arguments.of((Object) null)
        );
    }

}
