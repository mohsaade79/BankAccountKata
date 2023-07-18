package org.alaf.entities;

import org.alaf.exceptions.BalanceException;

import java.io.PrintStream;
import java.time.LocalDate;

import static org.alaf.entities.Amount.amountOf;

public class Account {
    private Statement statement;

    private Amount balance = amountOf(0);

    /**
     * Constructor which takes an instance of the class statement as parameter.
     * The value of the initial balance is initialized to 0 at the declaration
     * @param statement
     */
    public Account(Statement statement) {
        this.statement = statement;
    }

    /**
     * A deposit operation
     * @param value
     * @param date
     */
    public void deposit(Amount value, LocalDate date) {
        recordTransaction(value, date);
    }

    /**
     * A withdrow operation
     * @param value
     * @param date
     */
    public void withdrawal(Amount value, LocalDate date) throws BalanceException {
        if(value.isGreaterThan(balance))
            throw new BalanceException("Cannot withdrow "+value+" Because it is greater than "+balance);
        recordTransaction(value.negative(), date);
    }

    /**
     * A method to print the detail of the account statement to the output stream
     * @param printer
     */
    public void printStatement(PrintStream printer) {
        statement.printTo(printer);
    }

    /**
     * A method to record the information of a transaction (deposit or withdrew) to the account statement.
     * The account balance is updated to the new value after the transaction is done.
     * @param value
     * @param date
     */
    private void recordTransaction(Amount value, LocalDate date) {
        Transaction transaction = new Transaction(value, date);
        Amount balanceAfterTransaction = transaction.balanceAfterTransaction(balance);
        balance = balanceAfterTransaction;
        statement.addLineContaining(transaction, balanceAfterTransaction);
    }
}
