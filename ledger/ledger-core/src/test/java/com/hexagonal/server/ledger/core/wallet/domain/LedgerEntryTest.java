package com.hexagonal.server.ledger.core.wallet.domain;

import com.hexagonal.server.shared.kernel.common.valueobjects.Description;
import com.hexagonal.server.shared.kernel.common.valueobjects.Id;
import com.hexagonal.server.shared.kernel.common.valueobjects.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LedgerEntryTest {

    @Test
    void should_create_credit_entry() {
        // given
        Id walletId = Id.generate();
        Money amount = Money.of(BigDecimal.valueOf(100));
        // when
        LedgerEntry entry = LedgerEntry.credit(walletId, amount, Description.valueOf("deposit"));
        // then
        assertThat(entry.getWalletId()).isEqualTo(walletId);
        assertThat(entry.getAmount()).isEqualTo(amount);
        assertThat(entry.getReference().getValue()).isEqualTo("deposit");
        assertThat(entry.getCreatedAt()).isNotNull();
        assertThat(entry.getId()).isNotNull();
    }

    @Test
    void should_create_debit_entry_with_negative_amount() {
        // given
        Id walletId = Id.generate();
        Money amount = Money.of(BigDecimal.valueOf(50));
        // when
        LedgerEntry entry = LedgerEntry.debit(walletId, amount, Description.valueOf("payment"));
        // then
        assertThat(entry.getAmount().getValue()).isEqualByComparingTo("-50");
        assertThat(entry.getReference().getValue()).isEqualTo("payment");
    }

    @Test
    void credit_should_fail_if_amount_negative() {
        // given
        Id walletId = Id.generate();
        Money amount = Money.of(BigDecimal.valueOf(-10));
        // then
        assertThatThrownBy(() ->
                LedgerEntry.credit(walletId, amount, Description.valueOf("invalid"))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Credit cannot be negative");
    }

    @Test
    void debit_should_fail_if_amount_negative() {
        // given
        Id walletId = Id.generate();
        Money amount = Money.of(BigDecimal.valueOf(-10));
        // then
        assertThatThrownBy(() ->
                LedgerEntry.debit(walletId, amount, Description.valueOf("invalid"))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Debit amount cannot be negative");
    }

}
