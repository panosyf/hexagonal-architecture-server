package com.hexagonal.server.account.application.common.constant.account;

import java.time.Instant;

public class Timestamp {

    private Timestamp() {
    }

    public static final com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp TIMESTAMP_1 = com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp.valueOf(Instant.parse("2024-09-08T16:30:000Z"));
    public static final com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp TIMESTAMP_2 = com.hexagonal.server.shared.kernel.common.valueobjects.Timestamp.valueOf(Instant.parse("2024-09-08T17:30:000Z"));

}
