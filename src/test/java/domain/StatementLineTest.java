package domain;

import org.alaf.entities.StatementLine;
import org.alaf.entities.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;


import static org.alaf.builders.DateCreator.parseDate;
import static org.alaf.entities.Amount.amountOf;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementLineTest {

    @Mock
    PrintStream printer;

    @Test
    public void
    should_print_itself() {
        StatementLine statementLine = new StatementLine(
                new Transaction(amountOf(1000),  parseDate("17-07-2023")),
                amountOf(1000));

        statementLine.printTo(printer);

        verify(printer).println("2023-07-17 | 1000.00  |          | 1000.00");
    }

    @Test public void
    should_print_withdrawal() {
        StatementLine statementLine = new StatementLine(
                new Transaction(amountOf(-1000),  parseDate("17-07-2023")),
                amountOf(-1000));

        statementLine.printTo(printer);

        verify(printer).println("2023-07-17 |          | 1000.00  | -1000.00");
    }


}