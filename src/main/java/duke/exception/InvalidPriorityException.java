package duke.exception;

public class InvalidPriorityException extends DukeException {
    private static String message = "Priority can be one of high, medium or low";

    @Override
    public String toString() {
        return message;
    }
}