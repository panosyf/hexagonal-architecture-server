package com.hexagonal.server.ledger.application.wallet.usecase.createwallet.mapper;

import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreateWalletOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;

public class CreateWalletMapper {

    private CreateWalletMapper() {
    }

    public static CreateWalletOperation toCreateWalletOperation(CreateWalletRequest createWalletRequest) {
        Id accountId = Id.valueOf(createWalletRequest.accountId());
        return new CreateWalletOperation(accountId);
    }

    public static CreateWalletResponse toCreateWalletResponse(Wallet wallet) {
        return CreateWalletMapper.toCreateWalletResponse(wallet);
    }

}
