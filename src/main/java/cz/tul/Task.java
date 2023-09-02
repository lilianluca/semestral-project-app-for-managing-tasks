package cz.tul;

import java.time.LocalDateTime;

/**
 * Class representing Task.
 */
public class Task implements Comparable<Task>, TaskInfo {
    private String name;
    private String description;
    private LocalDateTime addedDate;
    private LocalDateTime deadlineDate;
    private Difficulty difficulty;
    private int orderNumber;

    /**
     * Constructor
     * @param orderNumber - Task order.
     * @param name - Name of task
     * @param description - Description of Task.
     * @param addedDate - Date when Task was added.
     * @param deadlineDate - Date when Task must be completed.
     * @param difficulty - Task difficulty (EASY, MEDIUM, HARD).
     */
    public Task(int orderNumber, String name, String description, LocalDateTime addedDate, LocalDateTime deadlineDate, Difficulty difficulty) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.description = description;
        this.addedDate = addedDate;
        this.deadlineDate = deadlineDate;
        this.difficulty = difficulty;
    }

    /**
     * Converts Difficulty to String.
     * @return String representation (in czech language) of task difficulty, which is represented by Enum.
     */
    public String getStringDifficulty() {
        if (difficulty == Difficulty.EASY) {
            return "Lehké";
        } else if (difficulty == Difficulty.MEDIUM) {
            return "Střední";
        } else {
            return "Těžké";
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadlineDate(LocalDateTime deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Compares two tasks lexicographically by name.
     * @param task2 the object to be compared.
     * @return An integer value representing the lexicographic comparison result.
     *            Returns a negative value if this.getName() is less than task2.getName(),
     *            zero if both strings are equal, and a positive value if this.getName() is greater than task2.getName().
     */
    @Override
    public int compareTo(Task task2) {
        return this.getName().compareTo(task2.getName());
    }
}
