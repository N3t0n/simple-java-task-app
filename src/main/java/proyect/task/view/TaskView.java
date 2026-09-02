package proyect.task.view;

import proyect.task.controller.TaskController;
import proyect.task.exceptions.TaskException;
import proyect.task.exceptions.TaskValidationException;

import java.util.Scanner;

public class TaskView {
    private final TaskController taskController;
    private final Scanner scanner;

    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true){
            System.out.println("Task Manager");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Update Task");
            System.out.println("4. Show Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    addTaskView();
                    break;
                case 2:
                    removeTaskView();
                    break;
                case 3:
                    updateTaskView();
                    break;
                case 4:
                    showTasksView();
                    break;
                case 5:
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void addTaskView(){
        try {
            System.out.println("Add Task Id");
            String id = scanner.nextLine();

            System.out.println("Add Task Title");
            String title = scanner.nextLine();

            System.out.println("Add Task Description");
            String description = scanner.nextLine();

            System.out.println("Is the task completed? (true/false)");
            Boolean completed = scanner.nextBoolean();
            taskController.addTask(id, title, description, completed);
            System.out.println("Task added successfully!!!");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred");
            e.printStackTrace();
        }
    }

    public void removeTaskView(){
        try {
            System.out.println("Enter the id of the task to remove: ");
            String id = scanner.nextLine();
            this.taskController.removeTask(id);
            System.out.println("Task removed successfully!!!");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred");
            e.printStackTrace();
        }
    }

    public void updateTaskView(){
        try {
            System.out.println("Update Task Id");
            String id = scanner.nextLine();

            System.out.println("Update Task Title");
            String title = scanner.nextLine();

            System.out.println("Update Task Description");
            String description = scanner.nextLine();

            System.out.println("Is the task completed? (true/false)");
            Boolean completed = scanner.nextBoolean();
            taskController.updateTask(id, title, description, completed);
            System.out.println("Task updated successfully!!!");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred");
            e.printStackTrace();
        }
    }

    public void showTasksView(){
        try {
            System.out.println("Task List: ");
            this.taskController.showTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred");
            e.printStackTrace();
        }
    }

}
