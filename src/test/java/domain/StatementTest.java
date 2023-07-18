package domain;

import org.alaf.entities.Statement;
import org.alaf.entities.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;

import static org.alaf.builders.DateCreator.parseDate;
import static org.alaf.entities.Amount.amountOf;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementTest {

    @Mock
    PrintStream printer;
    @Mock
    Transaction transaction;
    private Statement statement;

    @Before
    public void initialise() {
        statement = new Statement();
    }

    @Test
    public void
    should_print_statement_header() {
        statement.printTo(printer);

        verify(printer).println(Statement.STATEMENT_HEADER);
    }

    @Test public void
    should_print_deposit() {
        statement.addLineContaining(
                new Transaction(amountOf(1000),  parseDate("17-07-2023")),
                amountOf(1000));

        statement.printTo(printer);

        verify(printer).println("2023-07-17 | 1000.00  |          | 1000.00");
    }

    @Test public void
    should_print_withdrawal() {
        statement.addLineContaining(
                new Transaction(amountOf(-1000),  parseDate("17-07-2023")),
                amountOf(-1000));

        statement.printTo(printer);

        verify(printer).println("2023-07-17 |          | 1000.00  | -1000.00");
    }

    @Test public void
    should_print_two_deposits_in_reverse_order() {
        statement.addLineContaining( new Transaction(amountOf(1000),  parseDate("10-07-2023")),
                amountOf(1000)
        );
        statement.addLineContaining( new Transaction(amountOf(2000),  parseDate("13-07-2023")),
                amountOf(3000)
        );
        statement.addLineContaining(
                new Transaction(amountOf(-500),  parseDate("17-07-2023")) ,
                amountOf(2500));

        statement.printTo(printer);

        InOrder inOrder = Mockito.inOrder(printer);
        inOrder.verify(printer).println("2023-07-17 |          | 500.00   | 2500.00");
        inOrder.verify(printer).println("2023-07-13 | 2000.00  |          | 3000.00");
        inOrder.verify(printer).println("2023-07-10 | 1000.00  |          | 1000.00");

    }

}