package biz.technway.khaled.trackmytasks.View;

import biz.technway.khaled.trackmytasks.controller.TaskController;
import biz.technway.khaled.trackmytasks.model.TaskModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class CompletedTasksView {
    private final TaskController taskController;

    public CompletedTasksView(TaskController taskController) {
        this.taskController = taskController;
    }

    public void showCompletedTasksView(BorderPane layout) {
        VBox completedTasksList = new VBox(20);
        completedTasksList.getStyleClass().add("container");

        Label pageTitle = new Label("Expired Tasks");
        pageTitle.getStyleClass().add("title");
        completedTasksList.getChildren().add(pageTitle);

        List<TaskModel> completedTasks = taskController.getCompletedTasks();

        for (TaskModel task : completedTasks) {
            VBox taskBox = new VBox(10);
            taskBox.getStyleClass().add("task-item");

            Label titleLabel = new Label("Title: " + task.getTitle());
            Label descriptionLabel = new Label("Description: " + task.getDescription());
            Label labelLabel = new Label("Label: " + task.getLabel());
            Label deadlineLabel = new Label("Deadline: " + task.getDeadlineDate());

            Button deleteButton = new Button("❌ Delete Task");
            deleteButton.getStyleClass().add("button--delete");
            deleteButton.setOnAction(e -> {
                taskController.deleteTask(task); 
                showCompletedTasksView(layout); 
            });

            taskBox.getChildren().addAll(titleLabel, descriptionLabel, labelLabel, deadlineLabel, deleteButton);

            completedTasksList.getChildren().add(taskBox);
        }

        if (completedTasks.isEmpty()) {
            Label noTasksLabel = new Label("No completed tasks found.");
            completedTasksList.getChildren().add(noTasksLabel);
        }

        layout.setCenter(completedTasksList);
    }
}
