package com.hexagonal.server.ledger.infra.api.wallet.controller;

import com.hexagonal.server.ledger.application.wallet.port.in.api.WalletApi;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.CreateWalletUseCase;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.request.CreateWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.createwallet.model.response.CreateWalletResponse;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.CreditWalletUseCase;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.request.CreditWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.creditwallet.model.response.CreditWalletResponse;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.DebitWalletUseCase;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.request.DebitWalletRequest;
import com.hexagonal.server.ledger.application.wallet.usecase.debitwallet.model.response.DebitWalletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController implements WalletApi {

    private final CreateWalletUseCase createWalletUseCase;
    private final CreditWalletUseCase creditWalletUseCase;
    private final DebitWalletUseCase debitWalletUseCase;

    public WalletController(
            @Qualifier("createWalletUseCase") CreateWalletUseCase createWalletUseCase,
            @Qualifier("creditWalletUseCase") CreditWalletUseCase creditWalletUseCase,
            @Qualifier("debitWalletUseCase") DebitWalletUseCase debitWalletUseCase) {
        this.createWalletUseCase = createWalletUseCase;
        this.creditWalletUseCase = creditWalletUseCase;
        this.debitWalletUseCase = debitWalletUseCase;
    }

    @Override
    @PostMapping(path = "/api/v1/wallets")
    public ResponseEntity<CreateWalletResponse> createWallet(@RequestBody CreateWalletRequest createWalletRequest) {
        return new ResponseEntity<>(createWalletUseCase.createWallet(createWalletRequest), HttpStatus.CREATED);
    }

    @Override
    @PostMapping(path = "/api/v1/wallets/{id}/actions/credit")
    public ResponseEntity<CreditWalletResponse> creditWallet(@PathVariable(name = "id") String id, @RequestBody CreditWalletRequest creditWalletRequest) {
        return new ResponseEntity<>(creditWalletUseCase.creditWallet(id, creditWalletRequest), HttpStatus.OK);
    }

    @Override
    @PostMapping(path = "/api/v1/wallets/{id}/actions/debit")
    public ResponseEntity<DebitWalletResponse> debitWallet(@PathVariable(name = "id") String id, @RequestBody DebitWalletRequest debitWalletRequest) {
        return new ResponseEntity<>(debitWalletUseCase.debitWallet(id, debitWalletRequest), HttpStatus.OK);
    }

}
