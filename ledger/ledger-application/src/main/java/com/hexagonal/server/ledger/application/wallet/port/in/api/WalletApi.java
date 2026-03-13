package com.hexagonal.server.ledger.application.wallet.port.in.api;


import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request.DebitWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response.DebitWalletResponse;
import com.hexagonal.server.shared.kernel.common.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface WalletApi {

    ResponseEntity<ApiResponse<CreateWalletResponse>> createWallet(CreateWalletRequest createWalletRequest);

    ResponseEntity<ApiResponse<CreditWalletResponse>> creditWallet(String id, CreditWalletRequest creditWalletRequest);

    ResponseEntity<ApiResponse<DebitWalletResponse>> debitWallet(String id, DebitWalletRequest debitWalletRequest);

}
