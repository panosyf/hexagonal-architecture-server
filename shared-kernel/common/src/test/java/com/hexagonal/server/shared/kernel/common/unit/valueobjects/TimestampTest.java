package com.hexagonal.server.shared.kernel.common.unit.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;
import com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TimestampTest {

    private final Instant instant = Instant.parse("2024-05-05T16:00:00.600000000Z");
    private final Instant instant2DaysAfter = Instant.parse("2024-05-07T16:00:00.600000000Z");
    private final Instant instant2DaysBefore = Instant.parse("2024-05-03T16:00:00.600000000Z");
    private final Instant instant2MonthsAfter = Instant.parse("2024-07-05T16:00:00.600000000Z");
    private final Instant instant2MonthsBefore = Instant.parse("2024-03-05T16:00:00.600000000Z");

    @Test
    void generateTest() {
        assertThat(instant)
                .isEqualTo(Timestamp.valueOf(instant).getTime());
        assertThat(Timestamp.now().getTime()).isInstanceOf(Instant.class);
    }

    @Test
    void validateValueOfTest() {
        assertThatThrownBy(() -> Timestamp.valueOf(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessageConstants.TIMESTAMP_CANNOT_BE_NULL);
    }

    @Test
    void isBeforeTest() {
        assertThat(Timestamp.valueOf(instant)
                .isBefore(Timestamp.valueOf(instant2MonthsAfter)))
                .isTrue();
    }

    @Test
    void isAfterTest() {
        assertThat(Timestamp.valueOf(instant)
                .isAfter(Timestamp.valueOf(instant2MonthsBefore)))
                .isTrue();
    }

    @Test
    void minusDays() {
        assertThat(Timestamp.valueOf(instant2DaysBefore))
                .isEqualTo(Timestamp.valueOf(instant).minusDays(2));
    }

    @Test
    void plusDays() {
        assertThat(Timestamp.valueOf(instant2DaysAfter))
                .isEqualTo(Timestamp.valueOf(instant).plusDays(2));
    }

    @Test
    void minusMonths() {
        assertThat(Timestamp.valueOf(instant2MonthsBefore))
                .isEqualTo(Timestamp.valueOf(instant).minusMonths(2));
    }

    @Test
    void plusMonths() {
        assertThat(Timestamp.valueOf(instant2MonthsAfter))
                .isEqualTo(Timestamp.valueOf(instant).plusMonths(2));
    }

}
