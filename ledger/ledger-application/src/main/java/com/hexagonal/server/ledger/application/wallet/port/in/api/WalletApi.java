package com.hexagonal.server.ledger.application.wallet.port.in.api;


import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request.DebitWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response.DebitWalletResponse;

public interface WalletApi {

    CreateWalletResponse createWallet(CreateWalletRequest request);

    CreditWalletResponse creditWallet(CreditWalletRequest request);

    DebitWalletResponse debitWallet(DebitWalletRequest request);

}
