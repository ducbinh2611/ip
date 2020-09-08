package duke.main;

import java.util.Scanner;

import duke.exception.*;

/**
 * Reads and parses the user input.
 */
public class Parser {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Reads next line of input from the user.
     * @return The next line that user inputs.
     */
    public static String readNextLine() {
        return sc.nextLine();
    }

    /**
     * Checks whether there is still input coming in.
     * @return True if there is still next line and false otherwise.
     */
    public static boolean hasNextLine() {
        return sc.hasNext();
    }

    /**
     * Returns the command keyword in the user input.
     * @param userInput The string represents user's input.
     * @return The keyword command.
     */
    public static String getCommand(String userInput) {
        String[] components = userInput.split(" ", 2);
        return components[0];
    }

    /**
     * Returns the arguments of the user's input.
     * @param userInput The string represents the user's input.
     * @return The arguments.
     * @throws EmptyTaskException
     */
    public static String getArgs(String userInput) throws EmptyTaskException {
        String[] components = userInput.split(" ", 2);
        if (components.length == 1) {
            throw new EmptyTaskException();
        } else {
            return components[1];
        }
    }

    /**
     * Gets the task index for done and delete command.
     * @param userInput The string represents the user's input.
     * @return The task index.
     * @throws InvalidIndexException
     */
    public static int getIndexTask(String userInput) throws InvalidIndexException {
        String[] components = userInput.split(" ", 2);
        if (components.length < 2) {
            throw new InvalidIndexException();
        } else {
            return Integer.parseInt(components[1]);
        }
    }

    /**
     * Returns the task's description from the user input.
     * @param userInput The string represents the user's input.
     * @return The task's description.
     * @throws EmptyTaskException
     */
    public static String findDescription(String userInput) throws EmptyTaskException {
        String[] components = userInput.split("/");
        if (components[0].length() == 0) {
            throw new EmptyTaskException();
        } else {
            return components[0];
        }
    }

    /**
     * Returns the date and/or time for deadline and event tasks.
     * @param userInput The string represents user's input.
     * @param keyword Keyword to distinguish deadline and event tasks.
     * @return The date and/or time for the task.
     * @throws EmptyDateException
     */
    public static String findTime(String userInput, String keyword) throws EmptyDateException {
        String[] components = userInput.split("/" + keyword + " ");
        if (components.length < 2) {
            throw new EmptyDateException();
        } else {
            String timeAndPriority = components[1];
            String time = timeAndPriority.split(" /priority")[0];
            return time;
        }
    }

    /**
     * Checks whether the given date in the correct format of YYYY-MM-DD HH:mm (time is optional)
     * @param time The input date time.
     * @return True if the input date time is of correct format.
     * @throws InvalidDateFormatException
     */
    public static boolean isValidDate(String time) throws InvalidDateFormatException {
        String[] times = time.split("-");
        if (times.length != 3) {
            throw new InvalidDateFormatException(false);
        }
        return true;
    }

    /**
     * Checks whether the user's input, other than containing date, contains time or not.
     * @param time The input date time.
     * @return True if there is time included, false otherwise.
     */
    public static boolean hasTime(String time) {
        String[] components = time.split(" ");
        return components.length == 2;
    }

    public static boolean hasPriority(String userInput) {
        String[] components = userInput.split("/priority ");
        return components.length > 1;
    }

    private static boolean isValidPriority(String priority) {
        return priority.equals("high") || priority.equals("medium") || priority.equals("low");
    }

    public static String getPriority(String userInput) throws InvalidPriority {
        String[] components = userInput.split("/priority ");
        String priority = components[1];
        if (isValidPriority(priority)) {
            return priority;
        } else {
            throw new InvalidPriority();
        }
    }
}
