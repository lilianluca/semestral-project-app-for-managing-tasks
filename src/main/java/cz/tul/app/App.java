package cz.tul.app;

import cz.tul.ui.UITools;
import cz.tul.utils.IntegerTools;

/**
 * The main class that represents the application entry point.
 */
public class App {
    public static void main(String[] args) {
        System.out.print(UITools.getFilesMenu());
        System.out.print("Přejete si pracovat s textovými nebo binárními soubory?: ");
        int filesChoice = IntegerTools.getInt();
        filesChoice = UITools.checkIfValidMenuChoice(filesChoice, 1, 2);

        String continueChoice = "a";
        while (continueChoice.equals("a")) {
            System.out.print(UITools.getMenu());
            System.out.print("Vaše volba: ");
            int menuChoice = IntegerTools.getInt();
            menuChoice = UITools.checkIfValidMenuChoice(menuChoice, 1, 6);
            switch (menuChoice) {
                case 1 -> UITools.createTask(filesChoice);
                case 2 -> UITools.listTasks(filesChoice);
                case 3 -> UITools.listDirectories(filesChoice);
                case 4 -> UITools.editTask(filesChoice);
                case 5 -> UITools.removeTask(filesChoice);
                case 6 -> UITools.removeCategory(filesChoice);
                default -> System.out.println("Špatně zadaná volba.");
            }
            System.out.print("Chcete pokračovat? a/n: ");
            continueChoice = UITools.scanner.nextLine();
            continueChoice = UITools.checkIfYesNoChoice(continueChoice);
        }
    }
}