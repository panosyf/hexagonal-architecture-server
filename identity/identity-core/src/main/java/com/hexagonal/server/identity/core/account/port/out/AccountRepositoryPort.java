package com.hexagonal.server.identity.core.account.port.out;

import com.hexagonal.server.identity.core.account.domain.Account;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public interface AccountRepositoryPort {

    Account save(Account account);

    Account findById(Id id);

    int findTotalEntries();

    void deleteAll();

}
