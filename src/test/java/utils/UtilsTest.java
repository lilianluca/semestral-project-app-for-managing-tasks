package utils;

import cz.tul.Difficulty;
import cz.tul.utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    void oneEqualsEasy(){
        assertEquals(Difficulty.EASY, Utils.getDifficultyByInt(1));
    }
    @Test
    void twoEqualsMedium(){
        assertEquals(Difficulty.MEDIUM, Utils.getDifficultyByInt(2));
    }
    @Test
    void threeEqualHard(){
        assertEquals(Difficulty.HARD, Utils.getDifficultyByInt(3));
    }
}