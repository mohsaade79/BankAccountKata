package org.alaf.entities;

import java.io.PrintStream;

public class StatementLine {
    private Transaction transaction;
    private Amount currentBalance;

    /**
     *
     * @param transaction
     * @param currentBalance
     */
    public StatementLine(Transaction transaction, Amount currentBalance) {
        this.transaction = transaction;
        this.currentBalance = currentBalance;
    }

    public void printTo(PrintStream printer) {
        this.transaction.printTo(printer, currentBalance);
    }

}
