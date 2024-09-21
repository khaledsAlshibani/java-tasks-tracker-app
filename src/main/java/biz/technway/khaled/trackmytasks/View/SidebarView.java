package biz.technway.khaled.trackmytasks.view;

import biz.technway.khaled.trackmytasks.controller.TaskController;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class SidebarView {
    private final TaskController taskController;

    public SidebarView(TaskController taskController) {
        this.taskController = taskController;
    }

    /**
     * Sets up the Sidebar view, which contains a button to add a new
     * task, as well as a menu with links to the Dashboard, Active Tasks,
     * Completed Tasks, Archived Tasks, and Expired Tasks views.
     *
     * @param layout The BorderPane to which the Sidebar view should be
     *               added.
     */
    public void showSidebarView(BorderPane layout) {
        VBox sidebar = new VBox();
        sidebar.getStyleClass().add("sidebar");

        Button addTaskBtn = new Button("➕ Add Task");
        addTaskBtn.getStyleClass().add("sidebar__add-button");
        addTaskBtn.getStyleClass().add("button--add");
        addTaskBtn.setOnAction(e -> {
            AddNewTasksView addNewTaskView = new AddNewTasksView(taskController);
            addNewTaskView.showAddNewTask(layout);
        });

        VBox sidebarMenu = new VBox();
        sidebarMenu.getStyleClass().add("sidebar__menu");

        Button dashboardButton = new Button("Dashboard");
        dashboardButton.getStyleClass().remove("button");
        dashboardButton.getStyleClass().add("sidebar__menu-item");
        dashboardButton.setOnAction(e -> {
            DashboardView dashboardView = new DashboardView(taskController);
            dashboardView.showDashboardView(layout);
        });

        Button viewTasksBtn = new Button("View Active Tasks");
        viewTasksBtn.getStyleClass().remove("button");
        viewTasksBtn.getStyleClass().add("sidebar__menu-item");
        viewTasksBtn.setOnAction(e -> {
            ActiveTasksView activeTasksView = new ActiveTasksView(taskController);
            activeTasksView.showActiveTasksView(layout);
        });

        Button completedTasksBtn = new Button("Completed Tasks");
        completedTasksBtn.getStyleClass().remove("button");
        completedTasksBtn.getStyleClass().add("sidebar__menu-item");
        completedTasksBtn.setOnAction(e -> {
            CompletedTasksView completedTasksView = new CompletedTasksView(taskController);
            completedTasksView.showCompletedTasksView(layout);
        });
        
        Button archivedTasksBtn = new Button("Archived Tasks");
        archivedTasksBtn.getStyleClass().remove("button");
        archivedTasksBtn.getStyleClass().add("sidebar__menu-item");
        archivedTasksBtn.setOnAction(e -> {
            ArchivedTasksView archivedTasksView = new ArchivedTasksView(taskController);
            archivedTasksView.showArchivedTasksView(layout);
        });

        Button expiredTasksBtn = new Button("Expired Tasks");
        expiredTasksBtn.getStyleClass().remove("button");
        expiredTasksBtn.getStyleClass().add("sidebar__menu-item");
        expiredTasksBtn.setOnAction(e -> {
            ExpiredTasksView expiredTasksView = new ExpiredTasksView(taskController);
            expiredTasksView.showExpiredTasksView(layout);
        });

        sidebarMenu.getChildren().addAll(dashboardButton, viewTasksBtn, completedTasksBtn, archivedTasksBtn, expiredTasksBtn);
        sidebar.getChildren().addAll(addTaskBtn, sidebarMenu);

        layout.setLeft(sidebar);
    }
}
