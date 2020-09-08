package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate eventTime;
    private LocalDateTime time;

    public Event(
            String description, String time, boolean hasTime, boolean isDone, String priority) {
        super(description, isDone, priority);
        if (!hasTime) {
            this.eventTime = LocalDate.parse(time);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.time = LocalDateTime.parse(time, formatter);
        }
    }

    @Override
    public String getStorageString() {
        return "E | " + this.getStatusIcon() + " | " + this.description
            + " | " + this.time + " | " + this.priority.toString() + "\n";
    }

    @Override
    public String toString() {
        String timeFormat;
        if (eventTime != null) {
            timeFormat = eventTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            timeFormat = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            int hour = time.getHour();
            int min = time.getMinute();
            timeFormat += " " + hour + ":" + (min < 10 ? "0" : "") + min;

        }
        return "[E][" + this.getStatusIcon() + "] "
                + this.description + " (at: " + timeFormat + ")"
                + " (Priority: " + this.priority.toString() + ")";
    }
}
