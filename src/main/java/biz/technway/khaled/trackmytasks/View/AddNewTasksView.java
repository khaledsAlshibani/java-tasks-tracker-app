package biz.technway.khaled.trackmytasks.view;

import java.time.LocalDate;

import biz.technway.khaled.trackmytasks.controller.TaskController;
import biz.technway.khaled.trackmytasks.utils.TaskFormUtil;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddNewTasksView {
    private final TaskController taskController;

    public AddNewTasksView(TaskController taskController) {
        this.taskController = taskController;
    }

    /**
     * Shows the add new task view, which contains a form to
     * enter the details of a new task.
     *
     * @param layout the layout where the add new task view should
     *               be rendered
     */
    public void showAddNewTask(BorderPane layout) {
        VBox addNewTaskBox = new VBox(20);
        addNewTaskBox.getStyleClass().add("container");

        Label pageTitle = new Label("Add New Task");
        pageTitle.getStyleClass().add("title");
        addNewTaskBox.getChildren().add(pageTitle);

        VBox form = new VBox(15);
        TextField titleField = new TextField();
        titleField.setPromptText("Enter Task Title");

        TextArea descriptionField = new TextArea();
        descriptionField.setPromptText("Enter Description");

        TextField labelField = new TextField();
        labelField.setPromptText("Enter Label");

        DatePicker deadlinePicker = new DatePicker();
        deadlinePicker.setPromptText("Enter Deadline");

        HBox buttons = new HBox(7);
        Button saveButton = new Button("✅ Save Task");
        saveButton.getStyleClass().add("button--save");
        saveButton.setOnAction(e -> {
            String title = titleField.getText();
            String description = descriptionField.getText();
            String label = labelField.getText();
            LocalDate deadline = deadlinePicker.getValue();

            taskController.saveTask(title, description, label, deadline);

            TaskFormUtil.resetFields(titleField, labelField);
            TaskFormUtil.resetTextAreas(descriptionField);
            TaskFormUtil.resetDateInputs(deadlinePicker);
        });

        form.getChildren().addAll(titleField, descriptionField, labelField, deadlinePicker, buttons);
        addNewTaskBox.getChildren().add(form);

        layout.setCenter(addNewTaskBox);
    }
}
