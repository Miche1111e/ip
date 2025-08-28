import java.util.ArrayList;
import java.io.File;

public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
        // handle file creation if folder/file doesn't exist
    }

    public ArrayList<Task> load() {
        // read tasks from file
    }

    public void save(ArrayList<Task> tasks) {
        // write tasks to file
    }
}
