package org.alaf.entities;

import java.text.DecimalFormat;
import java.util.Objects;

public class Amount {
    private int value;
    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    /**
     * A private constructor wich take the value of the ammount.
     * It is private because it will be called from the static method amountOf
     * @param value
     */

    private Amount(int value) {
        this.value = value;
    }

    /**
     * static method to create an inctance of an amount
     * @param value
     * @return
     */
    public static Amount amountOf(int value) {
        return new Amount(value);
    }

    /**
     *
     * @param otherAmount
     * @return new amount after adding the otherAmount Value to the current ammount
     */
    public Amount plus(Amount otherAmount) {
        return amountOf(this.value + otherAmount.value);
    }

    /**
     * test if the value of the current account if greater than the value of the other amount
     * @param otherAmount
     * @return
     */
    public boolean isGreaterThan(Amount otherAmount) {
        return this.value > otherAmount.value;
    }

    /**
     *
     * @return the absolute value of the amount
     */
    public Amount absoluteValue() {
        return amountOf(Math.abs(value));
    }

    /**
     *
     * @return a string representation of the amount
     */
    public String moneyRepresentation() {
        return decimalFormat.format(value);
    }

    public Amount negative() {
        return amountOf(-value);
    }

    /**
     * A method to test if the values of two instances are equals
     * @param o
     * @return
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount)) return false;
        Amount amount = (Amount) o;
        return value == amount.value;
    }

    @Override
    public String toString() {
        return moneyRepresentation();
    }
}
