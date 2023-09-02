package cz.tul;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void easyEqualsLehke(){
        assertEquals("Lehké", new Task(1,"test", "test", LocalDateTime.now(), LocalDateTime.now(), Difficulty.EASY).getStringDifficulty());
    }
    @Test
    void mediumEqualsStredni(){
        assertEquals("Střední", new Task(1,"test", "test", LocalDateTime.now(), LocalDateTime.now(), Difficulty.MEDIUM).getStringDifficulty());
    }
    @Test
    void hardEqualsTezke(){
        assertEquals("Těžké", new Task(1,"test", "test", LocalDateTime.now(), LocalDateTime.now(), Difficulty.HARD).getStringDifficulty());
    }
}