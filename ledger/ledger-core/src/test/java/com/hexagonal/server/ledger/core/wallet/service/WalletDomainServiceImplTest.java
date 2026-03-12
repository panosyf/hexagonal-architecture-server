package com.hexagonal.server.ledger.core.wallet.service;

import com.hexagonal.server.ledger.core.wallet.domain.LedgerEntry;
import com.hexagonal.server.ledger.core.wallet.domain.Wallet;
import com.hexagonal.server.ledger.core.wallet.model.CreateWalletOperation;
import com.hexagonal.server.ledger.core.wallet.model.CreditOperation;
import com.hexagonal.server.ledger.core.wallet.model.DebitOperation;
import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class WalletDomainServiceImplTest {

    private WalletDomainService walletDomainService;

    @BeforeEach
    void setUp() {
        walletDomainService = new WalletDomainServiceImpl();
    }

    @Test
    void should_create_wallet() {
        //given
        Id accountId = Id.generate();
        CreateWalletOperation createWalletOperation = new CreateWalletOperation(accountId);
        // then
        Wallet wallet = walletDomainService.createWallet(createWalletOperation);
        // then
        assertThat(wallet).isNotNull();
        assertThat(wallet.getBalance()).isEqualTo(Money.zero());
    }

    @Test
    void should_credit_wallet() {
        // given
        Wallet wallet = Wallet.create(Id.generate());
        CreditOperation creditOperation = new CreditOperation(wallet, Money.of(BigDecimal.valueOf(100)), Description.valueOf("deposit"));
        // when
        LedgerEntry entry = walletDomainService.credit(creditOperation);
        // then
        assertThat(entry).isNotNull();
        assertThat(wallet.getBalance()).isEqualTo(Money.of(BigDecimal.valueOf(100)));
    }

    @Test
    void should_debit_wallet() {
        // given
        Wallet wallet = Wallet.create(Id.generate());
        wallet.credit(Money.of(BigDecimal.valueOf(100)), Description.valueOf("deposit"));
        DebitOperation debitOperation = new DebitOperation(wallet, Money.of(BigDecimal.valueOf(40)), Description.valueOf("payment"));
        // when
        LedgerEntry entry = walletDomainService.debit(debitOperation);
        // then
        assertThat(entry).isNotNull();
        assertThat(wallet.getBalance()).isEqualTo(Money.of(BigDecimal.valueOf(60)));
    }

}
