package com.targa.labs.dev.cqrses;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.eric.banking.cqrses.aggregate.BankAccountAggregate;
import com.eric.banking.cqrses.command.CreateAccountCommand;
import com.eric.banking.cqrses.command.CreditMoneyCommand;
import com.eric.banking.cqrses.command.DebitMoneyCommand;
import com.eric.banking.cqrses.event.AccountCreatedEvent;
import com.eric.banking.cqrses.event.MoneyCreditedEvent;
import com.eric.banking.cqrses.event.MoneyDebitedEvent;

import java.math.BigDecimal;
import java.util.UUID;

public class BankAccountTest {
    private static final String customerName = "Nebrass";

    private FixtureConfiguration<BankAccountAggregate> fixture;
    private UUID id;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(BankAccountAggregate.class);
        id = UUID.randomUUID();
    }

    @Test
    public void should_dispatch_accountcreated_event_when_createaccount_command() {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand(
                        id,
                        BigDecimal.valueOf(1000),
                        customerName)
                )
                .expectEvents(new AccountCreatedEvent(
                        id,
                        BigDecimal.valueOf(1000),
                        customerName)
                );
    }

    @Test
    public void should_dispatch_moneycredited_event_when_balance_is_lower_than_debit_amount() {
        fixture.given(new AccountCreatedEvent(
                        id,
                        BigDecimal.valueOf(1000),
                        customerName))
                .when(new CreditMoneyCommand(
                        id,
                        BigDecimal.valueOf(100))
                )
                .expectEvents(new MoneyCreditedEvent(
                        id,
                        BigDecimal.valueOf(100))
                );
    }

    @Test
    public void should_dispatch_moneydebited_event_when_balance_is_upper_than_debit_amount() {
        fixture.given(new AccountCreatedEvent(
                        id,
                        BigDecimal.valueOf(1000),
                        customerName))
                .when(new DebitMoneyCommand(
                        id,
                        BigDecimal.valueOf(100)))
                .expectEvents(new MoneyDebitedEvent(
                        id,
                        BigDecimal.valueOf(100)));
    }

    @Test
    public void should_not_dispatch_event_when_balance_is_lower_than_debit_amount() {
        fixture.given(new AccountCreatedEvent(
                        id,
                        BigDecimal.valueOf(1000),
                        customerName))
                .when(new DebitMoneyCommand(
                        id,
                        BigDecimal.valueOf(5000)))
                .expectNoEvents();
    }
}
