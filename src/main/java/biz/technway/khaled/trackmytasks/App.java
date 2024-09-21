package biz.technway.khaled.trackmytasks;

import biz.technway.khaled.trackmytasks.controller.TaskController;
import biz.technway.khaled.trackmytasks.view.DashboardView;
import biz.technway.khaled.trackmytasks.view.SidebarView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
    private final TaskController taskController = new TaskController();
    private BorderPane layout;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Track My Tasks");

        layout = new BorderPane();

        SidebarView sidebarView = new SidebarView(taskController);
        sidebarView.showSidebarView(layout);

        DashboardView homeView = new DashboardView(taskController);
        homeView.showDashboardView(layout);

        Scene scene = new Scene(layout, 800, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
