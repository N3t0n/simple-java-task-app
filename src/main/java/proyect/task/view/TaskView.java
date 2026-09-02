package proyect.task.view;

import proyect.task.controller.TaskController;

import java.util.Scanner;

public class TaskView {
    private final TaskController taskController;
    private final Scanner scanner;

    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("Task Manager");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Update Task");
            System.out.println("4. Show Tasks");
            System.out.println("5. Exit");

            int option = askMenuOption();

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

    private void addTaskView() {
        try {
            TaskInput input = getTaskInput();
            taskController.addTask(input.id, input.title, input.description, input.completed);
            System.out.println("Task added successfully!!!");
        } catch (Exception e) {
            handleError(e);
        }
    }

    private void removeTaskView() {
        try {
            String id = askRequiredText("Enter the id of the task to remove: ");
            this.taskController.removeTask(id);
            System.out.println("Task removed successfully!!!");
        } catch (Exception e) {
            handleError(e);
        }
    }

    private void updateTaskView() {
        try {
            TaskInput input = getTaskInput();
            taskController.updateTask(input.id, input.title, input.description, input.completed);
            System.out.println("Task updated successfully!!!");
        } catch (Exception e) {
            handleError(e);
        }
    }

    private void showTasksView() {
        try {
            System.out.println("Task List: ");
            this.taskController.showTasks();
        } catch (Exception e) {
            handleError(e);
        }
    }

    private TaskInput getTaskInput() {
        String id = askRequiredText("Task id: ");
        String title = askRequiredText("Task title: ");
        String description = askRequiredText("Task description: ");
        boolean completed = askCompletedStatus();

        return new TaskInput(id, title, description, completed);
    }

    private String askRequiredText(String message) {
        String value;

        do {
            System.out.print(message);
            value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.out.println("This field is required.");
            }
        } while (value.isEmpty());

        return value;
    }

    private int askMenuOption() {
        while (true) {
            System.out.print("Enter your choice: ");
            String value = scanner.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private boolean askCompletedStatus() {
        while (true) {
            System.out.print("Is the task completed? (y/n): ");
            String value = scanner.nextLine().trim().toLowerCase();

            if (value.equals("y")) {
                return true;
            }

            if (value.equals("n")) {
                return false;
            }

            System.out.println("Please enter y or n.");
        }
    }

    private void handleError(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    private static class TaskInput {
        private final String id;
        private final String title;
        private final String description;
        private final boolean completed;

        private TaskInput(String id, String title, String description, boolean completed) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.completed = completed;
        }
    }
}
