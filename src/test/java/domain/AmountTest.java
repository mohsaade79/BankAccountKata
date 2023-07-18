package domain;

import org.alaf.entities.Amount;
import org.junit.Test;

import static org.alaf.entities.Amount.amountOf;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AmountTest {
    @Test public void
    should_be_equal_to_another_instance_containing_same_amount() {
        Amount oneHundred =amountOf(100);
        Amount anotherOneHundred = amountOf(100);
        assertEquals(oneHundred,anotherOneHundred);

    }

    @Test public void
    should_be_different_from_another_instance_containing_different_amount() {
        Amount ten = amountOf(10);
        Amount five = amountOf(5);
        assertNotEquals(ten,five);

    }


    @Test public void
    should_sum_up_amounts() {
        Amount ten = amountOf(10);
        Amount five = amountOf(5);
        Amount fifteen = amountOf(15);
            assertEquals(fifteen,ten.plus(five));

    }

    @Test public void
    should_indicate_when_it_is_greater_than_other_amount() {
        Amount ten = amountOf(10);
        Amount five = amountOf(5);
        assertEquals(ten.isGreaterThan(five),true);

    }

    @Test public void
    should_indicate_when_it_is_not_greater_than_other_amount() {
        Amount ten = amountOf(10);
        Amount five = amountOf(5);
        assertEquals(five.isGreaterThan(ten),false);

    }

    @Test public void
    should_return_the_absolute_value() {
        Amount minusFive = amountOf(-5);
        assertEquals(amountOf(5),minusFive.absoluteValue());

    }

    @Test public void
    should_return_the_negative_value() {
        Amount five = amountOf(5);
        assertEquals(amountOf(-5),five.negative());

    }

    @Test
    public void
    should_return_money_representation() {
        Amount oneThousand = amountOf(1000);
        assertEquals("1000.00",oneThousand.moneyRepresentation());

    }

}
