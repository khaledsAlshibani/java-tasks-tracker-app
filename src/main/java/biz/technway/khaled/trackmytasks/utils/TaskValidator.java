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

    public boolean validateStringLength(String txt, int min, int max) {
        if (txt.length() < min || txt.length() > max) {
            TaskValidationAlert.showErrorAlert("Validation Error", "\"" + txt + "\" Length is not valid. Should be between " + min + " and " + max + " characters.");
            return false;
        }
        return true;
    }

    public boolean validateTaskTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            TaskValidationAlert.showErrorAlert("Validation Error", "Title is empty");
            return false;
        }
        return validateStringLength(title, this.titleMinLength, this.titleMaxLength);
    }

    public boolean validateTaskDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            TaskValidationAlert.showErrorAlert("Validation Error", "Description is empty");
            return false;
        }
        return validateStringLength(description, this.descriptionMinLength, this.descriptionMaxLength);
    }

    public boolean validateTaskLabel(String label) {
        if (label == null || label.trim().isEmpty()) {
            TaskValidationAlert.showErrorAlert("Validation Error", "Label is empty");
            return false;
        }
        return validateStringLength(label, this.labelMinLength, this.labelMaxLength);
    }

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

    public boolean validateTask(String title, String description, String label, LocalDate deadline) {
        boolean validatedTaskTitle = validateTaskTitle(title);
        boolean validatedTaskDescription = validateTaskDescription(description);
        boolean validatedTaskLabel = validateTaskLabel(label);
        boolean validatedTaskDeadline = validateTaskDeadline(deadline);

        return validatedTaskTitle && validatedTaskDescription && validatedTaskLabel && validatedTaskDeadline;
    }
}
