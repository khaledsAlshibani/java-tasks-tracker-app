package biz.technway.khaled.trackmytasks.View;

import biz.technway.khaled.trackmytasks.controller.TaskController;
import biz.technway.khaled.trackmytasks.model.TaskModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ArchivedTasksView {
    private final TaskController taskController;

    public ArchivedTasksView(TaskController taskController) {
        this.taskController = taskController;
    }

    public void showArchivedTasksView(BorderPane layout) {
        VBox archivedTasksList = new VBox(20);
        archivedTasksList.getStyleClass().add("container");

        Label pageTitle = new Label("Expired Tasks");
        pageTitle.getStyleClass().add("title");
        archivedTasksList.getChildren().add(pageTitle);

        List<TaskModel> archivedTasks = taskController.getArchivedTasks();

        for (TaskModel task : archivedTasks) {
            VBox taskBox = new VBox(10);
            taskBox.getStyleClass().add("task-item");

            Label titleLabel = new Label("Title: " + task.getTitle());
            Label descriptionLabel = new Label("Description: " + task.getDescription());
            Label labelLabel = new Label("Label: " + task.getLabel());
            Label deadlineLabel = new Label("Deadline: " + task.getDeadlineDate());

            HBox buttons = new HBox(7);

            Button unarchiveButton = new Button("Unarchive Task");
            unarchiveButton.setOnAction(e -> {
                taskController.unarchiveTask(task); 
                showArchivedTasksView(layout); 
            });

            Button deleteButton = new Button("❌ Delete Task");
            deleteButton.getStyleClass().add("button--delete");
            deleteButton.setOnAction(e -> {
                taskController.deleteTask(task); 
                showArchivedTasksView(layout); 
            });

            buttons.getChildren().addAll(unarchiveButton, deleteButton);
            taskBox.getChildren().addAll(titleLabel, descriptionLabel, labelLabel, deadlineLabel, buttons);

            archivedTasksList.getChildren().add(taskBox);
        }

        if (archivedTasks.isEmpty()) {
            Label noTasksLabel = new Label("No Archived tasks found.");
            archivedTasksList.getChildren().add(noTasksLabel);
        }

        layout.setCenter(archivedTasksList);
    }
}