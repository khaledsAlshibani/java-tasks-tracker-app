package biz.technway.khaled.trackmytasks.view;

import biz.technway.khaled.trackmytasks.controller.TaskController;
import biz.technway.khaled.trackmytasks.model.TaskModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ExpiredTasksView {
    private final TaskController taskController;

    public ExpiredTasksView(TaskController taskController) {
        this.taskController = taskController;
    }

    /**
     * Shows the expired tasks view in the given layout
     *
     * The expired tasks view displays all expired tasks in a vertical list.
     * Each task is displayed with its title, description, label, and deadline.
     * In addition, each task has a delete button. Clicking the delete button
     * removes the task from the expired tasks view and from the database.
     *
     * @param layout the layout to show the expired tasks view in
     */
    public void showExpiredTasksView(BorderPane layout) {
        VBox expiredTasksList = new VBox(20);
        expiredTasksList.getStyleClass().add("container");

        Label pageTitle = new Label("Expired Tasks");
        pageTitle.getStyleClass().add("title");
        expiredTasksList.getChildren().add(pageTitle);

        List<TaskModel> expiredTasks = taskController.getExpiredTasks();

        for (TaskModel task : expiredTasks) {
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
                showExpiredTasksView(layout);
            });

            taskBox.getChildren().addAll(titleLabel, descriptionLabel, labelLabel, deadlineLabel, deleteButton);

            expiredTasksList.getChildren().add(taskBox);
        }

        if (expiredTasks.isEmpty()) {
            Label noTasksLabel = new Label("No Expired tasks found.");
            expiredTasksList.getChildren().add(noTasksLabel);
        }

        layout.setCenter(expiredTasksList);
    }
}
