package com.hexagonal.server.ledger.application.wallet.usecase.createwallet;

import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;

public interface CreateWalletUseCase {

    CreateWalletResponse createWallet(CreateWalletRequest createWalletRequest);

}

