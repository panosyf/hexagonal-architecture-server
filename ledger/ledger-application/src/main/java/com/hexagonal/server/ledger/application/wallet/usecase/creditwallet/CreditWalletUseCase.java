package com.hexagonal.server.ledger.application.wallet.usecase.creditwallet;

import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;

public interface CreditWalletUseCase {

    CreditWalletResponse creditWallet(String id, CreditWalletRequest creditWalletRequest);

}
