package biz.technway.khaled.trackmytasks.view;

import biz.technway.khaled.trackmytasks.controller.TaskController;
import biz.technway.khaled.trackmytasks.model.TaskModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ActiveTasksView {
    private final TaskController taskController;

    public ActiveTasksView(TaskController taskController) {
        this.taskController = taskController;
    }

    /**
     * Shows the active tasks view in the given layout
     *
     * The active tasks view displays all active tasks in a vertical list. Each
     * task is displayed with its title, description, label, and deadline. In
     * addition, each task has three buttons: complete, delete, and archive.
     * Clicking the complete button marks the task as completed and removes it
     * from the active tasks view. Clicking the delete button removes the task
     * from the active tasks view and from the database. Clicking the archive
     * button moves the task to the archived tasks view.
     *
     * @param layout the layout to show the active tasks view in
     */
    public void showActiveTasksView(BorderPane layout) {
        VBox activeTasksList = new VBox(20);
        activeTasksList.getStyleClass().add("container");

        Label pageTitle = new Label("Active Tasks");
        pageTitle.getStyleClass().add("title");
        activeTasksList.getChildren().add(pageTitle);

        List<TaskModel> activeTasks = taskController.getActiveTasks();

        for (TaskModel task : activeTasks) {
            VBox taskBox = new VBox(10);
            taskBox.getStyleClass().add("task-item");

            Label title = new Label("Title: " + task.getTitle());
            Label description = new Label("Description: " + task.getDescription());
            Label label = new Label("Label: " + task.getLabel());
            Label deadline = new Label("Deadline: " + task.getDeadlineDate());

            HBox buttons = new HBox(7);
            
            Button completeButton = new Button("✅ Complete Task");
            completeButton.getStyleClass().add("button--add");
            completeButton.setOnAction(e -> {
                taskController.completeTask(task); 
                showActiveTasksView(layout);
            });

            Button deleteButton = new Button("❌ Delete Task");
            deleteButton.getStyleClass().add("button--delete");
            deleteButton.setOnAction(e -> {
                taskController.deleteTask(task); 
                showActiveTasksView(layout); 
            });

            Button archiveButton = new Button("Archive Task");
            archiveButton.setOnAction(e -> {
                taskController.archiveTask(task); 
                showActiveTasksView(layout); 
            });

            buttons.getChildren().addAll(completeButton, deleteButton, archiveButton);
            taskBox.getChildren().addAll(title, description, label, deadline, buttons);

            activeTasksList.getChildren().add(taskBox);
        }

        if (activeTasks.isEmpty()) {
            Label noTasksLabel = new Label("No active tasks found.");
            activeTasksList.getChildren().add(noTasksLabel);
        }

        layout.setCenter(activeTasksList);
    }
}
