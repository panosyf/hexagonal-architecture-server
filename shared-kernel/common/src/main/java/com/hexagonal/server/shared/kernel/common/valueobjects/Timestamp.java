package com.hexagonal.server.shared.kernel.common.valueobjects;

import com.hexagonal.server.shared.kernel.common.exception.ErrorMessageConstants;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static com.hexagonal.server.shared.kernel.common.constants.ZoneId.UTC;

public class Timestamp extends ValueObject {

    private final Instant time;

    private Timestamp() {
        this.time = Instant.now();
    }

    private Timestamp(Instant time) {
        if (time == null)
            throw new IllegalArgumentException(ErrorMessageConstants.TIMESTAMP_CANNOT_BE_NULL);
        this.time = time;
    }

    public static Timestamp now() {
        return new Timestamp();
    }

    public static Timestamp valueOf(final Instant time) {
        return new Timestamp(time);
    }

    public Instant getTime() {
        return this.time;
    }

    public boolean isBefore(final Timestamp timestamp) {
        return this.time.isBefore(timestamp.getTime());
    }

    public boolean isAfter(final Timestamp timestamp) {
        return this.time.isAfter(timestamp.getTime());
    }

    public Timestamp minusNanos(final int nanos) {
        validateValue(nanos);
        Instant instant = this.time.atZone(ZoneId.of(UTC)).truncatedTo(ChronoUnit.NANOS).minusNanos(nanos).toInstant();
        return new Timestamp(instant);
    }

    public Timestamp plusNanos(final int nanos) {
        validateValue(nanos);
        Instant instant = this.time.atZone(ZoneId.of(UTC)).truncatedTo(ChronoUnit.NANOS).plusNanos(100).toInstant();
        return new Timestamp(instant);
    }

    public Timestamp minusDays(final int days) {
        validateValue(days);
        Instant instant = this.time.atZone(ZoneId.of(UTC)).minusDays(days).toInstant();
        return new Timestamp(instant);
    }

    public Timestamp plusDays(final int days) {
        validateValue(days);
        Instant instant = this.time.atZone(ZoneId.of(UTC)).plusDays(days).toInstant();
        return new Timestamp(instant);
    }

    public Timestamp minusMonths(final int months) {
        validateValue(months);
        Instant instant = this.time.atZone(ZoneId.of(UTC)).minusMonths(months).toInstant();
        return new Timestamp(instant);
    }

    public Timestamp plusMonths(final int months) {
        validateValue(months);
        Instant instant = this.time.atZone(ZoneId.of(UTC)).plusMonths(months).toInstant();
        return new Timestamp(instant);
    }

    private void validateValue(final int value) {
        if (value < 1) {
            throw new IllegalArgumentException(ErrorMessageConstants.VALUE_MUST_BE_AT_LEAST_ONE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timestamp timestamp = (Timestamp) o;
        return Objects.equals(time, timestamp.time);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(time);
    }

    @Override
    public String toString() {
        return "Timestamp{" +
                "time=" + time +
                '}';
    }

}
