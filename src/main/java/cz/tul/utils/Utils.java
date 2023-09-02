package cz.tul.utils;

import cz.tul.Difficulty;

/**
 * Utility class containing various helper methods for task management.
 */
public class Utils {

    private Utils() {

    }

    /**
     * Converts the given difficulty level integer into a Difficulty enum value.
     * @param difficulty - The difficulty level integer.
     * @return The corresponding Difficulty enum value.
     */
    public static Difficulty getDifficultyByInt(int difficulty){
        if(difficulty == 1){
            return Difficulty.EASY;
        }else if(difficulty == 2){
            return Difficulty.MEDIUM;
        }else{
            return Difficulty.HARD;
        }
    }

    /**
     * Converts the given Difficulty enum value into its corresponding integer representation.
     * @param difficulty - The Difficulty enum value.
     * @return The integer representation of the difficulty level.
     */
    public static int getIntByDifficulty(Difficulty difficulty){
        if(difficulty == Difficulty.EASY){
            return 1;
        }else if(difficulty == Difficulty.MEDIUM){
            return 2;
        }else {
            return 3;
        }
    }

    public static String getDirectoryByChoice(int filesChoice){
        if(filesChoice == 1){
            return "text_files";
        }else{
            return "binary_files";
        }
    }
}
