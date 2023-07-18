package org.alaf;

import org.alaf.entities.Account;
import org.alaf.entities.Statement;
import org.alaf.exceptions.BalanceException;

import static org.alaf.builders.DateCreator.parseDate;
import static org.alaf.entities.Amount.amountOf;

public class StartApp {
    public static void main(String[] args) {

        Account account = new Account(new Statement());
        try {
        account.deposit(amountOf(1000), parseDate("10-01-2012"));
        account.deposit(amountOf(2000), parseDate("13-01-2012"));

            account.withdrawal(amountOf(500), parseDate("14-01-2012"));
        } catch (BalanceException e) {
            System.out.println(e.getMessage());
            System.out.println("Try with another value");
        }

        account.printStatement(System.out);
    }
}