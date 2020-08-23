package main.java;

public class Event extends Task {
    private String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String getStorageString() {
        return "E | " + this.getStatusIcon() + " | " + this.description
            + " | " + this.time + "\n";
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.description + " (at: " + this.time + ")";
    }
}
