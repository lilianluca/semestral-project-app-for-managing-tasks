package cz.tul.utils;

import cz.tul.ui.UITools;

/**
 * A utility class for working with integers.
 */
public class IntegerTools {
    private IntegerTools() {
    }

    /**
     * Reads an integer input from the user.
     * @return The integer input provided by the user.
     */
    public static int getInt(){
        while (true){
            try{
                return Integer.parseInt(UITools.scanner.nextLine());
            }catch (NumberFormatException exception){
                System.out.print("Vámi zadaný vstup není číslo! Zadejte prosím číslo: ");
            }
        }
    }

    /**
     * Checks if a number is not within the specified interval.
     * @param number - The number to check.
     * @param intervalStart - The start of the interval (inclusive).
     * @param intervalEnd - The end of the interval (inclusive).
     * @return {@code true} if the number is not within the interval, {@code false} otherwise.
     */
    public static boolean checkIfNumberIsNotWithinInterval(int number, int intervalStart, int intervalEnd){
        return number < intervalStart || number > intervalEnd;
    }

}
