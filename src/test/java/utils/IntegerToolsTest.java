package utils;

import cz.tul.utils.IntegerTools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerToolsTest {
    @Test
    void SevenIsNotWithinIntervalFromOneToSix(){
        assertTrue(IntegerTools.checkIfNumberIsNotWithinInterval(7, 1, 6));
    }
    @Test
    void TwoIsWithinIntervalFromOneToSix(){
        assertFalse(IntegerTools.checkIfNumberIsNotWithinInterval(2, 1, 6));
    }
    @Test
    void ZeroIsNotWithinIntervalFromOneToTen(){
        assertTrue(IntegerTools.checkIfNumberIsNotWithinInterval(0, 1, 10));
    }
}