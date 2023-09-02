package cz.tul.utils;

import cz.tul.Category;
import cz.tul.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FileTools {
    private FileTools() {
    }

    /**
     * Writes a task to the text file of a specified category.
     * The method takes the category name and a Task object as parameters.
     * It constructs the file path based on the category name.
     * The task information is formatted into a string and written to the file.
     *
     * @param fileName - The name of the category file.
     * @param task     - Task object to write to category file.
     */
    public static void writeToTextFiles(String fileName, Task task) {
        String filePath = "data" + File.separator + "text_files" + File.separator + fileName + ".csv";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            bufferedWriter.write(task.getOrderNumber() + 1 + "," + task.getName() + "," + task.getDescription() + "," + task.getAddedDate() + "," + task.getDeadlineDate() + "," + Utils.getIntByDifficulty(task.getDifficulty()) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the text file of a specified category with the tasks from the provided Category object.
     *
     * @param fileName - The name of the category file.
     * @param category - Category object with saved/edited tasks.
     */
    public static void updateTextFile(String fileName, Category category) {
        String filePath = "data" + File.separator + "text_files" + File.separator + fileName + ".csv";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < category.getTasks().size(); i++) {
                Task task = category.getTasks().get(i);
                bufferedWriter.write(i + 1 + "," + task.getName() + "," + task.getDescription() + "," + task.getAddedDate() + "," + task.getDeadlineDate() + "," + Utils.getIntByDifficulty(task.getDifficulty()) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the content of a text file and saves the tasks in the provided Category object.
     *
     * @param fileName - The name of the category file.
     * @param category - Category object where the tasks are stored.
     */
    public static void readFromTextFiles(String fileName, Category category) {
        String filePath = "data" + File.separator + "text_files" + File.separator + fileName + ".csv";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                String[] dataStr = s.split(",");
                int orderNumber = Integer.parseInt(dataStr[0]);
                String name = dataStr[1];
                String description = dataStr[2];
                String[] addedDateTimeData = dataStr[3].split("T");
                LocalDate addedDate = LocalDate.parse(addedDateTimeData[0]);
                LocalTime addedTime = LocalTime.parse(addedDateTimeData[1]);
                LocalDateTime addedDateTime = LocalDateTime.of(addedDate, addedTime);
                String[] deadlineDateTimeData = dataStr[4].split("T");
                LocalDate deadlineDate = LocalDate.parse(deadlineDateTimeData[0]);
                LocalTime deadlineTime = LocalTime.parse(deadlineDateTimeData[1]);
                LocalDateTime deadlineDateTime = LocalDateTime.of(deadlineDate, deadlineTime);
                int difficulty = Integer.parseInt(dataStr[5]);
                Task task = new Task(orderNumber, name, description, addedDateTime, deadlineDateTime, Utils.getDifficultyByInt(difficulty));
                category.addTask(task);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Retrieves the order number for a new task in the specified category file.
     *
     * @param fileName - The name of the category file.
     * @return The order number for the new task.
     */
    public static int getOrderTextFiles(String fileName) {
        String filePath = "data" + File.separator + "text_files" + File.separator + fileName + ".csv";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            int order = 0;
            while ((bufferedReader.readLine()) != null) {
                order++;
            }
            return order;
        } catch (IOException exception) {
            return 0;
        }
    }

    public static int getOrderBinaryFiles(String fileName) {
        if(checkIfFileExists("binary_files", fileName)){
            String filePath = "data" + File.separator + "binary_files" + File.separator + fileName + ".dat";
            int orderNumber = 0;
            try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath))) {
                while (dataInputStream.available() > 0) {
                    orderNumber = dataInputStream.readInt();
                    String name = dataInputStream.readUTF();
                    String description = dataInputStream.readUTF();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
                    String addedDateTimeStr = dataInputStream.readUTF();
                    LocalDateTime addedDateTime = LocalDateTime.parse(addedDateTimeStr, formatter);
                    String deadlineDateTimeStr = dataInputStream.readUTF();
                    LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDateTimeStr, formatter);
                    int difficulty = dataInputStream.readInt();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return orderNumber;
        }else{
            return 0;
        }

    }

    /**
     * Checks if the specified file exists.
     *
     * @param fileName The name of the category file.
     * @return {@code true} if the file exists, {@code false} otherwise.
     */
    public static boolean checkIfFileExists(String directoryChoice, String fileName) {
        String filePath;
        if (directoryChoice.equals("text_files")) {
            filePath = "data" + File.separator + "text_files" + File.separator + fileName + ".csv";
        } else {
            filePath = "data" + File.separator + "binary_files" + File.separator + fileName + ".dat";
        }
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * Deletes the specified file.
     *
     * @param fileName The name of the category file.
     * @return {@code true} if the file was successfully deleted, {@code false} otherwise.
     */
    public static boolean deleteFile(String directory, String fileName) {
        String eof = "";
        if(directory.equals("text_files")){
            eof = ".csv";
        }else if(directory.equals("binary_files")){
            eof = ".dat";
        }
        String filePath = "data" + File.separator + directory + File.separator + fileName + eof;
        File file = new File(filePath);
        return file.delete();
    }

    /**
     * @return A formatted string listing the directories and the number of tasks.
     */
    public static String listDirectories(String filesChoice) {
        String path = "data" + File.separator + filesChoice;
        StringBuilder filesStr = new StringBuilder();
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            filesStr.append("Pořadí   Název                Počet úkolů").append("\n");
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    String fileName = files[i].getName();
                    int dotIndex = fileName.lastIndexOf('.');
                    String nameWithoutExtension = fileName.substring(0, dotIndex);
                    filesStr.append(String.format("%-8d %-20s %-20s\n", i + 1, nameWithoutExtension, getNumberOfLinesOfFile(files[i])));
                }
            }
        }
        if (files != null && files.length == 0) {
            filesStr = new StringBuilder("Žádná kategorie neexistuje.\n");
        }
        return filesStr.toString();
    }

    /**
     * Returns the number of lines in a given file.
     *
     * @param file The file for which to count the lines.
     * @return The number of lines in the file.
     */
    private static int getNumberOfLinesOfFile(File file) {
        int countLines = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()))) {
            while (bufferedReader.readLine() != null) {
                countLines++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countLines;
    }

    /**
     * Checks if the directory for storing text files is empty.
     *
     * @return {@code true} if the directory is empty, {@code false} otherwise.
     */
    public static boolean checkIfDirectoryIsEmpty(String directoryChoice) {
        File directory = new File("data" + File.separator + directoryChoice);
        return directory.isDirectory() && Objects.requireNonNull(directory.listFiles()).length == 0;
    }

    //binary files
    public static void writeToBinaryFiles(String fileName, Task task) {
        String filePath = "data" + File.separator + "binary_files" + File.separator + fileName + ".dat";
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filePath, true))) {
            dataOutputStream.writeInt(task.getOrderNumber() + 1);
            dataOutputStream.writeUTF(task.getName());
            dataOutputStream.writeUTF(task.getDescription());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
            String formattedAddedDateTime = task.getAddedDate().format(formatter);
            dataOutputStream.writeUTF(formattedAddedDateTime);
            String formattedDeadlineDateTime = task.getDeadlineDate().format(formatter);
            dataOutputStream.writeUTF(formattedDeadlineDateTime);
            dataOutputStream.writeInt(Utils.getIntByDifficulty(task.getDifficulty()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFromBinaryFile(String fileName, Category category) {
        String filePath = "data" + File.separator + "binary_files" + File.separator + fileName + ".dat";
        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(filePath))) {
            while (dataInputStream.available() > 0) {
                int orderNumber = dataInputStream.readInt();
                String name = dataInputStream.readUTF();
                String description = dataInputStream.readUTF();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
                String addedDateTimeStr = dataInputStream.readUTF();
                LocalDateTime addedDateTime = LocalDateTime.parse(addedDateTimeStr, formatter);
                String deadlineDateTimeStr = dataInputStream.readUTF();
                LocalDateTime deadlineDateTime = LocalDateTime.parse(deadlineDateTimeStr, formatter);
                int difficulty = dataInputStream.readInt();
                Task task = new Task(orderNumber, name, description, addedDateTime, deadlineDateTime, Utils.getDifficultyByInt(difficulty));
                category.addTask(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateBinaryFile(String fileName, Category category) {
        String filePath = "data" + File.separator + "binary_files" + File.separator + fileName + ".dat";
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(filePath))) {
            for (int i = 0; i < category.getTasks().size(); i++) {
                Task task = category.getTasks().get(i);
                dataOutputStream.writeInt(task.getOrderNumber() + 1);
                dataOutputStream.writeUTF(task.getName());
                dataOutputStream.writeUTF(task.getDescription());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");
                String formattedAddedDateTime = task.getAddedDate().format(formatter);
                dataOutputStream.writeUTF(formattedAddedDateTime);
                String formattedDeadlineDateTime = task.getDeadlineDate().format(formatter);
                dataOutputStream.writeUTF(formattedDeadlineDateTime);
                dataOutputStream.writeInt(Utils.getIntByDifficulty(task.getDifficulty()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
