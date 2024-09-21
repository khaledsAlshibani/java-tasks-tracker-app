package biz.technway.khaled.trackmytasks.utils;

import javafx.scene.control.Alert;

public class TaskValidationAlert {
    /**
     * Shows an alert dialog with the given title and message, and
     * with the type ERROR. The dialog is modal and will block
     * until the user closes it.
     *
     * @param title the title of the alert dialog
     * @param message the message of the alert dialog
     */
    public static void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shows an alert dialog with the given title and message, and
     * with the type INFORMATION. The dialog is modal and will block
     * until the user closes it.
     *
     * @param title the title of the alert dialog
     * @param message the message of the alert dialog
     */
    public static void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}