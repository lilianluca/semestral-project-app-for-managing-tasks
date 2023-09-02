package cz.tul.utils;

import cz.tul.ui.UITools;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * A utility class for handling date and time operations.
 */
public class DateTimeTools {
    /**
     * Reads a LocalDate object from user input.
     * @return The LocalDate object entered by the user.
     */
    public static LocalDate getLocalDate() {
        while (true) {
            try {
                return LocalDate.parse(UITools.scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.print("Datum není ve formátu: (yyyy-MM-dd), zadejte prosím ještě jednou: ");
            }
        }
    }

    /**
     * Reads a LocalTime object from user input.
     * @return The LocalTime object entered by the user.
     */
    public static LocalTime getLocalTime() {
        while (true) {
            try {
                return LocalTime.parse(UITools.scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.print("Čas není ve formátu: (HH:mm), zadejte prosím ještě jednou: ");
            }
        }
    }
}
