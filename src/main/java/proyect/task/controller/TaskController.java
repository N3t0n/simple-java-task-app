package proyect.task.controller;

import proyect.task.exceptions.TaskException;
import proyect.task.exceptions.TaskValidationException;
import proyect.task.model.Task;
import proyect.task.model.TaskRepository;

import java.util.List;

public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);
        Task task = new Task(id, title, completed, description);
        this.taskRepository.save(task);
        System.out.println("Task added");
    }

    public void removeTask(String id) throws TaskValidationException, TaskException {
        if (id==null || id.trim().isEmpty()){
            throw new TaskValidationException("Id cannot be null or empty");
        }
            this.taskRepository.remove(id);
    }

    public void showTasks() throws TaskValidationException, TaskException {
        List<Task> tasks = this.taskRepository.findAll();
        if (tasks.isEmpty()){
            throw new TaskValidationException("List cannot be empty");
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void updateTask(String id, String title, String description, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, description, completed);
        Task updateTask = new Task(id, title, completed, description);
        this.taskRepository.updateTask(updateTask);
    }

    private void validateTaskData(String id, String title, String description, Boolean completed) throws TaskValidationException {
        if (id==null || id.trim().isEmpty()){
            throw new TaskValidationException("Id cannot be null or empty");
        }
        if (title==null || title.trim().isEmpty()){
            throw new TaskValidationException("Title cannot be null or empty");
        }
        if (description==null || description.trim().isEmpty()){
            throw new TaskValidationException("Description cannot be null or empty");
        }
        if (completed==null){
            throw new TaskValidationException("Status cannot be null");
        }
    }
}
