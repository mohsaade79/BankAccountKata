package org.alaf.builders;






import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

/**
 * This class is used to parse a date from a string.
 * To factor code and avoid code duplication. It is used in the AtartApp class and in many test classes
 */
public class DateCreator {
  private final static  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);

    public static LocalDate parseDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString, formatter);

        return date;
    }
}
