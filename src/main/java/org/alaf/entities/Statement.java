package org.alaf.entities;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class Statement {
     public static final String STATEMENT_HEADER = "Date       | Credit   | Debit    | Balance";
    /**
     * A linked list that contains the detail of all the transactions of an account.
     */
    private List<StatementLine> statementLines = new LinkedList<StatementLine>();

    public void addLineContaining(Transaction transaction, Amount currentBalance) {
        ((LinkedList)statementLines).addFirst(new StatementLine(transaction, currentBalance) );
    }

    /**
     * A method to print the statement details to the output stream.
     * @param printer
     */
    public void printTo(PrintStream printer) {
        printer.println(STATEMENT_HEADER);
        for (StatementLine statementLine : statementLines) {
            statementLine.printTo(printer);
        }
    }


}
