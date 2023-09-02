package cz.tul.ui;

import cz.tul.Category;
import cz.tul.Task;
import cz.tul.utils.DateTimeTools;
import cz.tul.utils.FileTools;
import cz.tul.utils.IntegerTools;
import cz.tul.utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Utility class containing UI-related helper methods for the application.
 */
public class UITools {
    public static Scanner scanner = new Scanner(System.in);
    private static String menu = """
            ****************Menu****************
            * 1. Vytvořit úkol                 *
            * 2. Výpis úkolů                   *
            * 3. Výpis kategororií             *
            * 4. Upravit úkol                  *
            * 5. Odstranit úkol                *
            * 6. Odstranit kategorii           *
            ************************************
            """;
    private static String difficultyMenu = """
            1. Lehké
            2. Střední
            3. Těžké
            """;
    private static String sortingMenu = """
            1. Podle názvu
            2. Podle data přidání
            3. Podle data splnění
            4. Podle obtížnosti
            """;
    private static String editMenu = """
            1. Název
            2. Popis
            3. Datum splnění
            4. Obtížnost
            """;
    private static String filesMenu = """
            1. Textové
            2. Binární
            """;

    private UITools() {
    }

    public static String getMenu() {
        return menu;
    }

    public static String getDifficultyMenu() {
        return difficultyMenu;
    }

    public static String getSortingMenu() {
        return sortingMenu;
    }

    public static String getEditMenu() {
        return editMenu;
    }

    public static String getFilesMenu() {
        return filesMenu;
    }

    /**
     * Checks if the specified category file exists and prompts the user to enter an existing category if it doesn't.
     *
     * @param fileName - The name of the category file to check.
     * @return The name of an existing category file.
     */
    public static String checkIfCategoryExists(int filesChoice, String fileName) {
        boolean fileExists = FileTools.checkIfFileExists(Utils.getDirectoryByChoice(filesChoice), fileName);
        while (!fileExists) {
            System.out.print("Zadaná kategorie neexistuje. Zadejte existující kategorii: ");
            fileName = scanner.nextLine();
            fileExists = FileTools.checkIfFileExists(Utils.getDirectoryByChoice(filesChoice), fileName);
        }
        return fileName;
    }

    /**
     * Checks if the specified menu choice is within the valid interval and prompts the user to enter a valid choice if not.
     *
     * @param menuChoice    - The menu choice to check.
     * @param intervalStart - The starting value of the valid interval.
     * @param intervalEnd   - The ending value of the valid interval.
     * @return The valid menu choice within the specified interval.
     */
    public static int checkIfValidMenuChoice(int menuChoice, int intervalStart, int intervalEnd) {
        while (IntegerTools.checkIfNumberIsNotWithinInterval(menuChoice, intervalStart, intervalEnd)) {
            System.out.print("Špatně zadaná volba. Zadejte prosím " + intervalStart + " až " + intervalEnd + ": ");
            menuChoice = IntegerTools.getInt();
        }
        return menuChoice;
    }

    /**
     * Checks if the specified choice is a valid "yes" or "no" option and prompts the user to enter a valid choice if not.
     *
     * @param choice - The choice to check.
     * @return The valid "yes" or "no" choice.
     */
    public static String checkIfYesNoChoice(String choice) {
        while (!choice.equals("a") && !choice.equals("n")) {
            System.out.print("Špatně zadaná volba. Zadejte prosím a pokud ano n pokud ne: ");
            choice = scanner.nextLine();
        }
        return choice;
    }

    /**
     * Creates a task with the given data. The created task is then added to the appropriate category file.
     */
    public static void createTask(int filesChoice) {
        System.out.print("Zadejte název kategorie, do které budete chtít přidat úkol: ");
        String categoryName = scanner.nextLine();
        System.out.print("Zadejte název úkolu: ");
        String taskName = scanner.nextLine();
        System.out.print("Zadejte popis úkolu: ");
        String taskDescription = scanner.nextLine();
        System.out.print("Zadejte datum do kdy má být úkol splněný (ve formátu: yyyy-MM-dd): ");
        LocalDate taskDeadlineDate = DateTimeTools.getLocalDate();
        System.out.print("Zadejte čas do kdy má být úkol splněný (ve formátu: HH:mm): ");
        LocalTime taskDeadlineTime = DateTimeTools.getLocalTime();
        System.out.print(UITools.getDifficultyMenu());
        System.out.print("Zadejte obtížnost úkolu: ");
        int taskDifficulty = IntegerTools.getInt();
        taskDifficulty = checkIfValidMenuChoice(taskDifficulty, 1, 3);
        LocalDateTime taskDeadlineDateTime = LocalDateTime.of(taskDeadlineDate, taskDeadlineTime);
        Task task = new Task(FileTools.getOrderBinaryFiles(categoryName), taskName, taskDescription, LocalDateTime.now().withNano(0).withSecond(0), taskDeadlineDateTime, Utils.getDifficultyByInt(taskDifficulty));
        if(filesChoice == 1) {
            FileTools.writeToTextFiles(categoryName, task);
        }else if(filesChoice == 2){
            FileTools.writeToBinaryFiles(categoryName, task);
        }
    }

    /**
     * Lists the tasks from a specified category, allowing optional sorting based on user preferences.
     * The sorted or unsorted tasks are then displayed. If no categories exist, a corresponding message is printed.
     */
    public static void listTasks(int filesChoice) {
        if (!FileTools.checkIfDirectoryIsEmpty(Utils.getDirectoryByChoice(filesChoice))) {
            System.out.print("Z které kategorie chcete vypsat úkoly? (př: prace, skola,...): ");
            String fileName = scanner.nextLine();
            fileName = UITools.checkIfCategoryExists(filesChoice, fileName);
            Category category = new Category(fileName);
            System.out.print("Přejete si seřadit úkoly? a/n: ");
            String sortingChoice = scanner.nextLine();
            sortingChoice = checkIfYesNoChoice(sortingChoice);
            if (filesChoice == 1) {
                FileTools.readFromTextFiles(fileName, category);
            } else if (filesChoice == 2) {
                FileTools.readFromBinaryFile(fileName, category);
            }
            if (sortingChoice.equals("a")) {
                System.out.print(UITools.getSortingMenu());
                System.out.print("Podle jakého kritéria si přejete seřadit úkoly?: ");
                int sortingMenuChoice = IntegerTools.getInt();
                sortingMenuChoice = checkIfValidMenuChoice(sortingMenuChoice, 1, 4);
                switch (sortingMenuChoice) {
                    case 1 -> System.out.print(category.sortByName());
                    case 2 -> System.out.print(category.sortByAddedDate());
                    case 3 -> System.out.print(category.sortByDeadlineDate());
                    case 4 -> System.out.print(category.sortByDifficulty());
                }
            } else {
                System.out.print(category);
            }
        } else {
            System.out.println("Žádná kategorie neexistuje.");
        }
    }

    /**
     * Lists the directories in the text_files directory.
     */
    public static void listDirectories(int filesChoice) {
        String directory = "";
        if (filesChoice == 1) {
            directory = "text_files";
        } else if (filesChoice == 2) {
            directory = "binary_files";
        }
        System.out.print(FileTools.listDirectories(directory));
    }

    /**
     * Allows the user to edit a task within a specified category.
     */
    public static void editTask(int filesChoice) {
        System.out.print("V jaké kategorii chcete upravit úkol?: ");
        String fileName = UITools.scanner.nextLine();
        fileName = UITools.checkIfCategoryExists(filesChoice, fileName);
        Category category = new Category(fileName);
        if(filesChoice == 1){
            FileTools.readFromTextFiles(fileName, category);
        }else if(filesChoice == 2){
            FileTools.readFromBinaryFile(fileName, category);
        }
        System.out.print(category);
        System.out.print("Zadejte číslo úkolu, kterého chcete upravit: ");
        int orderNumber = IntegerTools.getInt();
        while (!category.checkIfTaskExists(orderNumber)) {
            System.out.print("Úkol se zadaným číslem neexistuje. Zadejte číslo existujícího úkolu: ");
            orderNumber = IntegerTools.getInt();
        }
        System.out.print(UITools.getEditMenu());
        System.out.print("Co si přejete upravit?: ");
        int editMenuChoice = IntegerTools.getInt();
        editMenuChoice = checkIfValidMenuChoice(editMenuChoice, 1, 4);
        switch (editMenuChoice) {
            case 1 -> {
                System.out.print("Zadejte nový název úkolu: ");
                String newTaskName = UITools.scanner.nextLine();
                if (category.editTaskName(orderNumber, newTaskName)) {
                    System.out.println("Název byl úspěšně změněn.");
                }
            }
            case 2 -> {
                System.out.print("Zadejte nový popis úkolu: ");
                String newTaskDescription = UITools.scanner.nextLine();
                if (category.editTaskDescription(orderNumber, newTaskDescription)) {
                    System.out.println("Popis byl úspěšně změněn.");
                }
            }
            case 3 -> {
                System.out.print("Zadejte nový datum úkolu (ve formátu: yyyy-MM-dd): ");
                LocalDate newDeadlineDate = DateTimeTools.getLocalDate();
                System.out.print("Zadejte nový čas úkolu (ve formátu: HH:mm): ");
                LocalTime newDeadlineTime = DateTimeTools.getLocalTime();
                if (category.editTaskDeadlineDate(orderNumber, newDeadlineDate, newDeadlineTime)) {
                    System.out.println("Datum bylo úspěšně změněno.");
                }
            }
            case 4 -> {
                System.out.print(UITools.getDifficultyMenu());
                System.out.print("Zadejte novou obtížnost úkolu: ");
                int taskDifficulty = IntegerTools.getInt();
                taskDifficulty = checkIfValidMenuChoice(taskDifficulty, 1, 3);
                if (category.editTaskDifficulty(orderNumber, taskDifficulty)) {
                    System.out.println("Obtížnost byla úspěšně změněna.");
                }
            }
        }
        if(filesChoice == 1) {
            FileTools.updateTextFile(fileName, category);
        }else if(filesChoice == 2){
            FileTools.updateBinaryFile(fileName, category);
        }
    }

    /**
     * Allows the user to remove a task from a specified category.
     */
    public static void removeTask(int filesChoice) {
        System.out.print("Z jaké kategorie chcete odstranit úkol?: ");
        String fileName = UITools.scanner.nextLine();
        fileName = UITools.checkIfCategoryExists(filesChoice, fileName);
        Category category = new Category(fileName);
        if(filesChoice == 1) {
            FileTools.readFromTextFiles(fileName, category);
        }else if(filesChoice == 2){
            FileTools.readFromBinaryFile(fileName, category);
        }
        System.out.print(category);
        System.out.print("Zadejte číslo úkolu, kterého chcete odstranit: ");
        int orderNumber = IntegerTools.getInt();
        while (!category.checkIfTaskExists(orderNumber)) {
            System.out.print("Úkol se zadaným číslem neexistuje. Zadejte číslo existujícího úkolu: ");
            orderNumber = IntegerTools.getInt();
        }
        category.removeTask(orderNumber - 1);
        if(filesChoice == 1){
            FileTools.updateTextFile(fileName, category);
        }else if(filesChoice == 2){
            FileTools.updateBinaryFile(fileName, category);
        }

        System.out.println("Úkol byl odstraněn.");
    }

    /**
     * Allows the user to remove a category.
     */
    public static void removeCategory(int filesChoice) {
        System.out.print("Zadejte název kategorie, kterou chcete odstranit: ");
        String fileName = UITools.scanner.nextLine();
        if (FileTools.checkIfFileExists(Utils.getDirectoryByChoice(filesChoice), fileName)) {
            if (FileTools.deleteFile(Utils.getDirectoryByChoice(filesChoice), fileName)) {
                System.out.println("Kategorie byla odstraněna.");
            } else {
                System.out.println("Kategorii se nepodařilo odstranit. Pravděpodobně neexistuje.");
            }
        } else {
            System.out.println("Zadaná kategorie neexistuje.");
        }
    }
}
