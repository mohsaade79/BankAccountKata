package org.alaf.entities;

import java.io.PrintStream;
import java.time.LocalDate;

import static org.alaf.entities.Amount.amountOf;
import static org.apache.commons.lang3.StringUtils.rightPad;

public class Transaction {

        private static final String EMPTY_VALUE = "          ";

        private Amount value;
        private LocalDate date;

        public Transaction(Amount value, LocalDate date) {
            this.value = value;
            this.date = date;
        }

    /**
     * A method to return the value of the account balance after the transaction
     * @param currentBalance
     * @return
     */
        public Amount balanceAfterTransaction(Amount currentBalance) {
            return currentBalance.plus(value);
        }

    /**
     * A method to give a string representation of the transaction details.
     * It uses a stringbuilder to put inside it the transaction detail, then it writes it to the output stream
     * @param printer
     * @param currentBalance
     */
        public void printTo(PrintStream printer, Amount currentBalance) {
            StringBuilder builder = new StringBuilder();
            addDateTo(builder);
            addValueTo(builder);
            addCurrentBalanceTo(builder, currentBalance);
            printer.println(builder.toString());
        }

    /**
     * A method to add the current balance to the stringbuilder
     * @param builder
     * @param currentBalance
     */
        private void addCurrentBalanceTo(StringBuilder builder, Amount currentBalance) {
            builder.append("| ")
                    .append(currentBalance.moneyRepresentation());
        }

    /**
     * A method to add the transaction value to the stringbuilder.
     * Upon to the transaction type it calls either addCreditTo or  addDebitTo method
     * @param builder
     */
        private void addValueTo(StringBuilder builder) {
            if (value.isGreaterThan(amountOf(0))) {
                addCreditTo(builder);
            } else {
                addDebitTo(builder);
            }
        }

    /**
     * A method to add a debit value transaction to the string builder
     * @param builder
     */
        private void addDebitTo(StringBuilder builder) {
            builder.append(EMPTY_VALUE)
                    .append("|")
                    .append(valueToString());
        }

    /**
     * A method to add a credit value transaction to the string builder
     * @param builder
     */
        private void addCreditTo(StringBuilder builder) {
            builder.append(valueToString())
                    .append("|")
                    .append(EMPTY_VALUE);
        }

    /**
     * A method that return a formatted string representation of the transaction value.
     * @return
     */
        private String valueToString() {
            String stringValue = " " + value.absoluteValue().moneyRepresentation();
            return rightPad(stringValue, 10);
        }

    /**
     * A method that add the date of the transaction to the string builder
     * @param builder
     */
        private void addDateTo(StringBuilder builder) {
            builder.append(date.toString());
            builder.append(" |");
        }

    /**
     * A method to test the equality of 2 transactions
     * @param obj
     * @return
     */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            Transaction other = (Transaction) obj;
            if (date == null) {
                if (other.date != null)
                    return false;
            } else if (!date.equals(other.date))
                return false;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

    }