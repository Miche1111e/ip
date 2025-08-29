package cheryl.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import cheryl.task.Todo;

class TaskListTest {

    @Test
    void testAddTask() {
        TaskList list = new TaskList();
        Todo task = new Todo("Finish homework");

        // Normal add
        list.addTask(task);
        assertEquals(1, list.getTasks().size());
        assertEquals("Finish homework", list.getTasks().get(0).getTitle());

        // Adding another task
        Todo task2 = new Todo("Read book");
        list.addTask(task2);
        assertEquals(2, list.getTasks().size());
        assertEquals("Read book", list.getTasks().get(1).getTitle());
    }

    @Test
    void testMarkTask() {
        TaskList list = new TaskList();
        list.addTask(new Todo("Do exercise"));

        // Normal marking
        list.markTask(0);
        assertTrue(list.getTasks().get(0).isDone());

        // Edge case: invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> list.markTask(1));
    }

    @Test
    void testRemoveTask() {
        TaskList list = new TaskList();
        Todo task = new Todo("Clean room");
        list.addTask(task);

        // Remove task
        list.deleteTask(0);
        assertEquals(0, list.getTasks().size());

        // Removing from empty list should throw exception
        assertThrows(IndexOutOfBoundsException.class, () -> list.deleteTask(0));
    }
}
