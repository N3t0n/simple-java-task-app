package proyect.task.model;

import proyect.task.exceptions.TaskException;
import proyect.task.persistence.TaskPersistence;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    List<Task> tasks;

    public TaskRepository() {
        tasks = TaskPersistence.loadTasks();
    }

    public void save(Task task) throws TaskException {
        if (task==null){
            throw new TaskException("Task cannot be null");
        }
        tasks.add(task);
        TaskPersistence.saveTasks(tasks);
    }

    public Task findById(String id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void remove(String id) throws TaskException {
        Task task = findById(id);
        if (task==null){
            throw new TaskException("Task cannot be null");
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }
    public void remove(Task task) throws TaskException {
        if (task==null){
            throw new TaskException("Task cannot be null");
        }

        if(!tasks.contains(task)){
            throw new TaskException("Task not found");
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }

    public List<Task> findAll() throws TaskException {
        if (tasks.isEmpty()){
            throw new TaskException("No tasks found");
        }
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

    public void updateTask(Task updateTask) throws TaskException {
        if (updateTask==null){
            throw new TaskException("Task cannot be null");
        }
        int index = findIndexById(updateTask.getId());
        if (index==-1){
            throw new TaskException("Invalid Index");
        }
        tasks.set(index, updateTask);
        TaskPersistence.saveTasks(tasks);
    }

    public void updateTaskCompleted(String id, Boolean completed) throws TaskException {

        int index = findIndexById(id);
        if (index==-1){
            throw new TaskException("Invalid Index");
        }
        tasks.get(index).setCompleted(completed);
        TaskPersistence.saveTasks(tasks);
    }

    public List<Task> findCompletedTasks() throws TaskException {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCompleted()) {
                completedTasks.add(task);
            }
        }
        if (!completedTasks.isEmpty()) {
            throw new TaskException("There are not completed tasks");
        }
        return completedTasks;
    }

    public List<Task> findPendingTasks() throws TaskException {
        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.getCompleted()) {
                pendingTasks.add(task);
            }
        }
        if (!pendingTasks.isEmpty()) {
            throw new TaskException("There are not completed tasks");
        }
        return pendingTasks;
    }


}

