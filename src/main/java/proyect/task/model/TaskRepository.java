package proyect.task.model;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    List<Task> tasks = new ArrayList<>();

    public void save(Task task) {
        tasks.add(task);
    }

    public Task findById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void remove(String id) {
        Task task = findById(id);
        tasks.remove(task);
    }

    public List<Task> findAll() {
        return tasks;
    }

    public int findIndexById(String id) {
        for (int i = 0; i < tasks.size(); i++) {
        if (tasks.get(i).getId().equals(id)) {
            return i;
        }
        }
        return -1;
    }

    public void updateTask(Task updateTask){
        int index = findIndexById(updateTask.getId());
        tasks.set(index, updateTask);
    }



}

