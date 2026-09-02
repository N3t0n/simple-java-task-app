package proyect.task;

import proyect.task.controller.TaskController;
import proyect.task.model.TaskRepository;
import proyect.task.view.TaskView;

public class Main {
    public static void main(String[] args) {

        TaskRepository  repository = new TaskRepository();
        TaskController controller = new TaskController(repository);
        TaskView view = new TaskView(controller);
        view.showMenu();
    }
}
