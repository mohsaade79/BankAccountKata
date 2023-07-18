package domain;

import org.alaf.entities.Account;
import org.alaf.entities.Amount;
import org.alaf.entities.Statement;
import org.alaf.entities.Transaction;
import org.alaf.exceptions.BalanceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.time.LocalDate;

import static org.alaf.builders.DateCreator.parseDate;
import static org.alaf.entities.Amount.amountOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class AccountTest {
    @Mock
    private Statement statement;
    private Account account;

    @Before
    public void initialise() {
        account = new Account(statement);
    }

    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
        Exception exception = assertThrows(BalanceException.class, () -> {
            LocalDate depositDate =  parseDate("10-07-2023");
            Amount depositAmount = amountOf(1000);
            account.deposit(depositAmount, depositDate);
            LocalDate withdrawalDate =  parseDate("17-07-2023");
            Amount withdrwalAmount = amountOf(1500);
            account.withdrawal(withdrwalAmount, withdrawalDate);
        });
        String msg="Cannot withdrow 1500.00 Because it is greater than 1000.00";
        assertEquals(msg, exception.getMessage());
    }

    @Test
    public void
    should_add_deposit_line_to_statement() {
        LocalDate depositDate =  parseDate("17-07-2023");
        Amount depositAmount = amountOf(1000);

        account.deposit(depositAmount, depositDate);

        verify(statement).addLineContaining(new Transaction(depositAmount, depositDate),
                currentBalanceEqualsTo(depositAmount));
    }



    @Test public void
    should_print_statement() {
        PrintStream printer = System.out;

        account.printStatement(printer);

        verify(statement).printTo(printer);
    }

    private Amount currentBalanceEqualsTo(Amount amount) {
        return amount;
    }

}
