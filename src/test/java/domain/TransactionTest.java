package domain;

import org.alaf.entities.Amount;
import org.alaf.entities.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.time.LocalDate;

import static org.alaf.builders.DateCreator.parseDate;
import static org.alaf.entities.Amount.amountOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {

    @Mock
    PrintStream printer;

    @Test
    public void
    should_print_credit_trasanction() {
        Transaction transaction = new Transaction(amountOf(1000), parseDate("17-07-2023"));

        transaction.printTo(printer, amountOf(1000));

        verify(printer).println("2023-07-17 | 1000.00  |          | 1000.00");
    }

    @Test public void
    should_print_debit_trasanction() {
        Transaction transaction = new Transaction(amountOf(-1000),  parseDate("17-07-2023"));

        transaction.printTo(printer, amountOf(-1000));

        verify(printer).println("2023-07-17 |          | 1000.00  | -1000.00");
    }

    @Test public void
    should_calculate_current_balance_after_deposit() {
        Transaction transaction = new Transaction(amountOf(1000),  parseDate("17-07-2023"));

        Amount currentValue = transaction.balanceAfterTransaction(amountOf(100));
        assertEquals(currentValue,amountOf(1100));

    }

    @Test public void
    should_calculate_current_balance_after_withdrawal() {
        Transaction transaction = new Transaction(amountOf(-1000),  parseDate("17-07-2023"));

        Amount currentValue = transaction.balanceAfterTransaction(amountOf(100));
        assertEquals(currentValue,amountOf(-900));

    }

    @Test public void
    should_be_equal_to_other_transaction_with_same_value_and_date() {
        LocalDate depositDate = parseDate("17-07-2023");
        Transaction depositOfOneHundred = new Transaction(amountOf(1000), depositDate);
        Transaction anotherDepositOfOneHundred = new Transaction(amountOf(1000), depositDate);
        assertEquals(depositOfOneHundred,anotherDepositOfOneHundred);

    }

}