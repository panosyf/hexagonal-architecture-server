package com.hexagonal.server.ledger.core.wallet.domain;

import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.hexagonal.server.ledger.core.wallet.exception.WalletErrorMessageConstants.INSUFFICIENT_FUNDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WalletTest {

    @Test
    void should_create_wallet() {
        // given
        Id accountId = Id.generate();
        // when
        Wallet wallet = Wallet.create(accountId);
        // then
        assertThat(wallet).isNotNull();
        assertThat(wallet.getBalance()).isEqualTo(Money.zero());
    }

    @Test
    void credit_should_add_ledger_entry() {
        // given
        Wallet wallet = Wallet.create(Id.generate());
        // when
        wallet.credit(Money.of(BigDecimal.valueOf(100)), Description.valueOf("deposit"));
        // then
        assertThat(wallet.getBalance()).isEqualTo(Money.of(BigDecimal.valueOf(100)));
    }

    @Test
    void debit_should_reduce_balance() {
        // given
        Wallet wallet = Wallet.create(Id.generate());
        // when
        wallet.credit(Money.of(BigDecimal.valueOf(100)), Description.valueOf("deposit"));
        wallet.debit(Money.of(BigDecimal.valueOf(40)), Description.valueOf("payment"));
        // then
        assertThat(wallet.getBalance()).isEqualTo(Money.of(BigDecimal.valueOf(60)));
    }

    @Test
    void debit_should_fail_if_insufficient_funds() {
        // given
        Wallet wallet = Wallet.create(Id.generate());
        // when
        wallet.credit(Money.of(BigDecimal.valueOf(50)), Description.valueOf("deposit"));
        // then
        assertThatThrownBy(() ->
                wallet.debit(Money.of(BigDecimal.valueOf(100)), Description.valueOf("payment")))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(INSUFFICIENT_FUNDS);
    }

    @Test
    void balance_should_be_sum_of_entries() {
        // given
        Wallet wallet = Wallet.create(Id.generate());
        // when
        wallet.credit(Money.of(BigDecimal.valueOf(100)), Description.valueOf("deposit"));
        wallet.debit(Money.of(BigDecimal.valueOf(30)), Description.valueOf("payment"));
        // then
        assertThat(wallet.getBalance()).isEqualTo(Money.of(BigDecimal.valueOf(70)));
    }


}
