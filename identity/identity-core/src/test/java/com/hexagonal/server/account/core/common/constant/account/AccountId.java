package com.hexagonal.server.account.core.common.constant.account;

import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public class AccountId {

    private AccountId() {
    }

    public static final Id ACCOUNT_ID_1 = com.hexagonal.server.shared.kernel.common.valueobjects.Id.valueOf("accountId1");
    public static final Id ACCOUNT_ID_2 = com.hexagonal.server.shared.kernel.common.valueobjects.Id.valueOf("accountId2");

}
