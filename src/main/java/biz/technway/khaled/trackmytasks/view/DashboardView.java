package biz.technway.khaled.trackmytasks.view;

import biz.technway.khaled.trackmytasks.controller.TaskController;
import biz.technway.khaled.trackmytasks.utils.TaskValidator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DashboardView {
    private final TaskController taskController;

    public DashboardView(TaskController taskController) {
        this.taskController = taskController;
    }

    /**
     * Sets up the Dashboard view, showing a welcome message and some
     * statistics about the tasks stored in the application, such as the
     * number of active, completed, archived, and expired tasks. Additionally,
     * a button is provided to add a new task.
     *
     * @param layout The BorderPane to which the Dashboard view should be
     *               added.
     */
    public void showDashboardView(BorderPane layout) {
        VBox homeContent = new VBox(20); 
        homeContent.getStyleClass().add("container");
        homeContent.setAlignment(Pos.CENTER); 

        Image illustrationImage = new Image(getClass().getResourceAsStream("/images/illustration.png"));
        ImageView imageView = new ImageView(illustrationImage);
        imageView.setFitWidth(350); 
        imageView.setPreserveRatio(true); 

        Label welcomeLabel = new Label("Welcome to Track My Tasks");
        welcomeLabel.getStyleClass().add("dashboard-title");

        Label activeTasksLabel = new Label("Active Tasks: " + taskController.countActiveTasks());
        Label completedTasksLabel = new Label("Completed Tasks: " + taskController.countCompletedTasks());
        Label archivedTasksLabel = new Label("Archived Tasks: " + taskController.countArchivedTasks());
        Label expiredTasksLabel = new Label("Expired Tasks: " + taskController.countExpiredTasks());

        Button addNewTaskButton = new Button("➕ Add New Task");
        addNewTaskButton.getStyleClass().add("button");
        addNewTaskButton.getStyleClass().add("button--add");
        addNewTaskButton.setOnAction(e -> {
            AddNewTasksView addNewTaskView = new AddNewTasksView(taskController);
            addNewTaskView.showAddNewTask(layout);
        });

        homeContent.getChildren().addAll(imageView, welcomeLabel, activeTasksLabel, completedTasksLabel, archivedTasksLabel, expiredTasksLabel, addNewTaskButton);

        layout.setCenter(homeContent);
    }
}
