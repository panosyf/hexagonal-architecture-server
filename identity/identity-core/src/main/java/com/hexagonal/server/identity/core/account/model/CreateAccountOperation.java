package com.hexagonal.server.identity.core.account.model;

import com.hexagonal.server.shared.kernel.common.valueobjects.Email;
import com.hexagonal.server.shared.kernel.common.valueobjects.Name;
import com.hexagonal.server.shared.kernel.common.valueobjects.Password;
import com.hexagonal.server.shared.kernel.common.valueobjects.Username;

public record CreateAccountOperation(Email email, Username username, Password password, Name name) {
}
