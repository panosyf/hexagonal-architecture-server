package com.hexagonal.server.shared.kernel.common.unit.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IdTest {

    @Test
    void generateTest() {
        UUID uuid = UUID.randomUUID();
        assertThat(uuid.toString())
                .isEqualTo(Id.valueOf(uuid.toString()).getValue())
                .isEqualTo(Id.valueOf(uuid).getValue());
        assertThat(Id.generate().getValue()).isInstanceOf(String.class);
    }

    @ParameterizedTest
    @MethodSource("validateGenerateTestArguments")
    void validateGenerateTest(String value) {
        assertThatThrownBy(() -> Id.valueOf(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessageConstants.ID_CANNOT_BE_NULL_OR_BLANK);
    }

    private static Stream<Arguments> validateGenerateTestArguments() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("         "),
                Arguments.of((Object) null)
        );
    }

}
