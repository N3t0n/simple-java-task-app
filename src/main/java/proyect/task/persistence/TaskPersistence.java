package proyect.task.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import proyect.task.model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskPersistence {
    private final static String FILE_NAME = "tasks.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void saveTasks(List<Task> tasks) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage()) ;
        }
    }

    public static List<Task> loadTasks() {
        File  file = new File(FILE_NAME);
        if (!file.exists()){
            return new ArrayList<>();
        }

        try (Reader reader  = new FileReader(FILE_NAME)) {
            Type listeType = new TypeToken<List<Task>>() {
            }.getType();
            return gson.fromJson(reader, listeType);
        }catch (IOException e){
            System.out.println("Error loading tasks: " + e.getMessage());
            return new ArrayList<>();
        }

    }
}
