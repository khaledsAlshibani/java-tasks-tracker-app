package biz.technway.khaled.trackmytasks.View;

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

            Label titleLabel = new Label("Title: " + task.getTitle());
            Label descriptionLabel = new Label("Description: " + task.getDescription());
            Label labelLabel = new Label("Label: " + task.getLabel());
            Label deadlineLabel = new Label("Deadline: " + task.getDeadlineDate());

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
            taskBox.getChildren().addAll(titleLabel, descriptionLabel, labelLabel, deadlineLabel, buttons);

            activeTasksList.getChildren().add(taskBox);
        }

        if (activeTasks.isEmpty()) {
            Label noTasksLabel = new Label("No active tasks found.");
            activeTasksList.getChildren().add(noTasksLabel);
        }

        layout.setCenter(activeTasksList);
    }
}
