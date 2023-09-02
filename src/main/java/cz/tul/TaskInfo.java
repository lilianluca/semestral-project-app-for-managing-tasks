package cz.tul;

import java.time.LocalDateTime;

/**
 * Interface representing the information of a task.
 */
public interface TaskInfo {
    String getName();
    String getDescription();
    LocalDateTime getAddedDate();
    LocalDateTime getDeadlineDate();
    Difficulty getDifficulty();
}
