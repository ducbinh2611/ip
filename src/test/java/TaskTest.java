import org.junit.Test;
import main.java.Task;

import static org.junit.Assert.assertEquals;

public class TaskTest {
    @Test
    public void markAsDone_returnsTick() {
        Task task = new Task("test", false);
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "\u2713");
    }

    @Test
    public void getDescription_correctDescription() {
        Task task = new Task("return home");
        String description = task.getDescription();
        assertEquals(description, "return home");
    }
}