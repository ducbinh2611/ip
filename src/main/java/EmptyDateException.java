package main.java;

public class EmptyDateException extends DukeException {
    private static String message = "OOPS!!! Please specify the date";

    @Override
    public String toString() {
        return message;
    }
}
