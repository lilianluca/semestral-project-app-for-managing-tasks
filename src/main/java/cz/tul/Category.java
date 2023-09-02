package cz.tul;

import cz.tul.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class representing category, which contains tasks.
 */
public class Category {
    private String name;

    /**
     * Constructor
     * @param name - Category name.
     */
    public Category(String name) {
        this.name = name;
    }

    private ArrayList<Task> tasks = new ArrayList<>();

    public String getName() {
        return name;
    }

    /**
     * Adds a task to the task list
     * @param task - The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at the specified index from the task list.
     * @param index - The index of the task to be removed.
     */
    public void removeTask(int index){
        tasks.remove(index);
    }

    /**
     * Returns a string representation of the Category.
     * The string representation includes relevant information about the Category.
     * @return A string representation of the Category.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Pořadí   Název                          Popis                          Datum přidání                  Datum splnění                  Obtížnost\n");
        for (Task task : tasks) {
            res.append(String.format("%-8s %-30s %-30s %-30s %-30s %-30s\n", task.getOrderNumber(), task.getName(), task.getDescription(), task.getAddedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), task.getDeadlineDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), task.getStringDifficulty()));
        }
        return res.toString();
    }

    /**
     * Sorts the tasks by name in ascending order.
     * @return A string representation of the sorted tasks.
     */
    public String sortByName() {
        Collections.sort(tasks);
        return toString();
    }

    /**
     * Sorts the tasks in the task list by the date they were added in ascending order.
     * @return A string representation of the sorted task list.
     */
    public String sortByAddedDate() {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getAddedDate().compareTo(t2.getAddedDate());
            }
        });
        return toString();
    }

    /**
     * Sorts the tasks in the task list by the deadline date in ascending order.
     * @return A string representation of the sorted task list.
     */
    public String sortByDeadlineDate() {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDeadlineDate().compareTo(t2.getDeadlineDate());
            }
        });
        return toString();
    }

    /**
     * Sorts the tasks in the task list by difficulty level in ascending order.
     * @return A string representation of the sorted task list.
     */
    public String sortByDifficulty() {
        tasks.sort(new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return Integer.compare(Utils.getIntByDifficulty(t1.getDifficulty()), Utils.getIntByDifficulty(t2.getDifficulty()));
            }
        });
        return toString();
    }

    /**
     * Edits the name of a task identified by the given order number.
     * @param orderNumber - The order number of the task to be edited.
     * @param newTaskName - The new name to be set for the task.
     * @return {@code true} if the task was found and its name was successfully updated, {@code false} otherwise.
     */
    public boolean editTaskName(int orderNumber, String newTaskName) {
        Task task = getTaskByOrderNumber(orderNumber);
        if (task != null) {
            task.setName(newTaskName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edits the description of a task identified by the given order number.
     * @param orderNumber - The order number of the task to be edited.
     * @param newTaskDescription - The new description to be set for the task.
     * @return {@code true} if the task was found and its description was successfully updated, {@code false} otherwise.
     */
    public boolean editTaskDescription(int orderNumber, String newTaskDescription) {
        Task task = getTaskByOrderNumber(orderNumber);
        if (task != null) {
            task.setDescription(newTaskDescription);
            //update text files...
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edits the deadline date of a task identified by the given order number.
     * @param orderNumber - The order number of the task to be edited.
     * @param newDate - The new date to be set for the task's deadline.
     * @param newTime - The new time to be set for the task's deadline.
     * @return {@code true} if the task was found and its deadline date was successfully updated, {@code false} otherwise.
     */
    public boolean editTaskDeadlineDate(int orderNumber, LocalDate newDate, LocalTime newTime) {
        Task task = getTaskByOrderNumber(orderNumber);
        if (task != null) {
            LocalDateTime newDeadlineDate = LocalDateTime.of(newDate, newTime);
            task.setDeadlineDate(newDeadlineDate);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Edits the difficulty of a task identified by the given order number.
     * @param orderNumber - The order number of the task to be edited.
     * @param newDifficulty - The new difficulty level to be set for the task.
     * @return {@code true} if the task was found and its difficulty was successfully updated, {@code false} otherwise.
     */
    public boolean editTaskDifficulty(int orderNumber, int newDifficulty) {
        Task task = getTaskByOrderNumber(orderNumber);
        if (task != null) {
            task.setDifficulty(Utils.getDifficultyByInt(newDifficulty));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves a task from the task list based on the given order number.
     * @param orderNumber - The order number of the task to be retrieved.
     * @return The task object with the specified order number, or {@code null} if no task is found.
     */
    public Task getTaskByOrderNumber(int orderNumber) {
        for (Task task : tasks) {
            if (orderNumber == task.getOrderNumber()) {
                return task;
            }
        }
        return null;
    }

    /**
     * Checks if a task with the specified order number exists in the task list.
     * @param orderNumber - The order number to be checked.
     * @return {@code true} if a task with the specified order number exists, {@code false} otherwise.
     */
    public boolean checkIfTaskExists(int orderNumber) {
        for (Task task : tasks) {
            if (orderNumber == task.getOrderNumber()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a copy of the list of tasks.
     * @return A new ArrayList containing a copy of the tasks.
     */
    public ArrayList<Task> getTasks(){
        return new ArrayList<>(tasks);
    }
}
