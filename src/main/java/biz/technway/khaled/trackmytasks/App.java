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
    private final BorderPane layout = new BorderPane();

    /**
     * JavaFX application entry point.
     *
     * @param primaryStage primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Track My Tasks");

        SidebarView sidebarView = new SidebarView(taskController);
        sidebarView.showSidebarView(layout);

        // Show Dashboard Interface as the home interface
        DashboardView dashboardView = new DashboardView(taskController);
        dashboardView.showDashboardView(layout);

        Scene scene = new Scene(layout, 800, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Main entry point for this JavaFX application.
     *
     * @param args the command line arguments to pass to the application
     */
    public static void main(String args[]) {
        launch(args);
    }
}
