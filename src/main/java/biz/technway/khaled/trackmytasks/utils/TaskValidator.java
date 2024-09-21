package biz.technway.khaled.trackmytasks.utils;

import java.time.LocalDate;
import javafx.scene.control.Alert;

public class TaskValidator {
    private final int titleMinLength = 4;
    private final int titleMaxLength = 150;
    private final int descriptionMinLength = 0;
    private final int descriptionMaxLength = 500;
    private final int labelMinLength = 2;
    private final int labelMaxLength = 80;

    /**
     * Validate if the given string has a length that is within the given 
     * min and max parameters. If the string length is not valid, show an
     * error alert and return false. Otherwise, return true.
     * @param txt the string to validate
     * @param min the minimum allowed string length
     * @param max the maximum allowed string length
     * @return true if the string length is valid, false otherwise
     */
    public boolean validateStringLength(String txt, int min, int max) {
        if (txt.length() < min || txt.length() > max) {
            TaskValidationAlert.showErrorAlert("Validation Error", "\"" + txt + "\" Length is not valid. Should be between " + min + " and " + max + " characters.");
            return false;
        }
        return true;
    }

    /**
     * Validate if the given title string is not empty and is within the given min and max lengths.
     * If the title is empty or has an invalid length, show an error alert and return false.
     * Otherwise, return true.
     * @param title the title to validate
     * @return true if the title is valid, false otherwise
     */
    public boolean validateTaskTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            TaskValidationAlert.showErrorAlert("Validation Error", "Title is empty");
            return false;
        }
        return validateStringLength(title, this.titleMinLength, this.titleMaxLength);
    }

    /**
     * Validate if the given description string is not empty and is within the given min and max lengths.
     * If the description is empty or has an invalid length, show an error alert and return false.
     * Otherwise, return true.
     * @param description the description to validate
     * @return true if the description is valid, false otherwise
     */
    public boolean validateTaskDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            TaskValidationAlert.showErrorAlert("Validation Error", "Description is empty");
            return false;
        }
        return validateStringLength(description, this.descriptionMinLength, this.descriptionMaxLength);
    }

    /**
     * Validate if the given label string is not empty and is within the given min and max lengths.
     * If the label is empty or has an invalid length, show an error alert and return false.
     * Otherwise, return true.
     * @param label the label to validate
     * @return true if the label is valid, false otherwise
     */
    public boolean validateTaskLabel(String label) {
        if (label == null || label.trim().isEmpty()) {
            TaskValidationAlert.showErrorAlert("Validation Error", "Label is empty");
            return false;
        }
        return validateStringLength(label, this.labelMinLength, this.labelMaxLength);
    }

    /**
     * Validate if the given deadline is valid. A deadline is valid if it is not null and is in the future.
     * If the deadline is invalid, show an error alert and return false. Otherwise, return true.
     * @param deadline the deadline to validate
     * @return true if the deadline is valid, false otherwise
     */
    public boolean validateTaskDeadline(LocalDate deadline) {
        if (deadline == null) {
            TaskValidationAlert.showErrorAlert("Validation Error", "Deadline is not set");
            return false;
        }
        if (deadline.isBefore(LocalDate.now())) {
            TaskValidationAlert.showErrorAlert("Validation Error", "Deadline must be in the future");
            return false;
        }
        return true;
    }

    /**
     * Validate if the given task parameters are valid. A task is valid if its title, description, label, and deadline are all valid.
     * If the task is invalid, show an error alert and return false. Otherwise, return true.
     * @param title the title of the task to validate
     * @param description the description of the task to validate
     * @param label the label of the task to validate
     * @param deadline the deadline of the task to validate
     * @return true if the task is valid, false otherwise
     */
    public boolean validateTask(String title, String description, String label, LocalDate deadline) {
        boolean validatedTaskTitle = validateTaskTitle(title);
        boolean validatedTaskDescription = validateTaskDescription(description);
        boolean validatedTaskLabel = validateTaskLabel(label);
        boolean validatedTaskDeadline = validateTaskDeadline(deadline);

        return validatedTaskTitle && validatedTaskDescription && validatedTaskLabel && validatedTaskDeadline;
    }
}
