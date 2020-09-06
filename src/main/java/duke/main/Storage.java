package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Saves the tasks in the hard disk automatically whenever the task list changes and
 * loads the data from the hard disk when Duke starts up.
 */
public class Storage {
    private static final String folderPath = "data/";
    private final String storagePath;

    /**
     * Creates a Storage object.
     * @param fileName Name of the saved file.
     */
    public Storage(String fileName) {
        this.storagePath = folderPath + fileName;
    }

    /**
     * Writes text to the saved file.
     * @param textToAdd Text to be added into the file.
     */
    private void writeToFile(String textToAdd) {
        try {
            FileWriter fileWriter = new FileWriter(storagePath);
            fileWriter.write(textToAdd);
            fileWriter.close();
        } catch (IOException err) {
            System.out.println(err);
        }
    }

    /**
     * Writes the entire task list to the saved file.
     * @param taskList The task list such that its details are copied in the saved file.
     */
    public void writeTasks(TaskList taskList) {
        List<Task> tasks = taskList.getTaskList();
        StringBuilder data = new StringBuilder();
        for (Task task : tasks) {
            data.append(task.getStorageString());
        }
        writeToFile(data.toString());
    }

    /**
     * Reads the file and forms a list of individual string representations of tasks in the file.
     * @return A list of string representation of tasks in the saved file.
     * @throws IOException when the directory to the saved file is not found.
     */
    public List<String> readStorageFile() throws IOException {
        File folder = new File(this.folderPath);
        File file = new File(this.storagePath);
        if (!folder.exists()) {
            folder.mkdirs();
            throw new IOException("Folder data does not exist");
        } else if (folder.exists() && !file.isFile()) {
            file.createNewFile();
            throw new IOException("duke.txt does not exist");
        }
        Scanner sc = new Scanner(file);
        List<String> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            tasks.add(task);
        }
        return tasks;
    }

    private Task translateStringToTask(String savedTask) throws IOException {
        String[] arr = savedTask.split(" \\| ");
        String command = arr[0];
        boolean isDone = arr[1].equals("\u2713");
        String description = arr[2];
        String timeNotProcessed = arr.length == 3 ? "" : arr[3];
        boolean hasTime = timeNotProcessed.contains("T");
        String timeProcessed = timeNotProcessed.replace("T", " ");
        if (command.equals("T")) {
            return new ToDo(description, isDone);
        } else if (command.equals("E")) {
            return new Event(description, timeProcessed, hasTime, isDone);
        } else if (command.equals("D")) {
            return new DeadLine(description, timeProcessed, hasTime, isDone);
        } else {
            throw new IOException("Saved task is invalid");
        }
    }

    /**
     * Reads from the saved file and creates the task list.
     * @return Task list that contains all the tasks in the saved file.
     * @throws IOException when the directory to the saved file is not found.
     */
    public TaskList formTaskList() throws IOException {
        List<String> taskListInString = this.readStorageFile();
        TaskList taskList = new TaskList();
        for (String taskInString : taskListInString) {
            Task task = this.translateStringToTask(taskInString);
            taskList.addTask(task);
        }
        return taskList;
    }
}
