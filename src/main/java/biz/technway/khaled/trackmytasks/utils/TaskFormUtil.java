package biz.technway.khaled.trackmytasks.utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskFormUtil {
    /**
     * Resets the values of the given TextField controls to empty strings.
     * @param fields the TextField controls to reset
     */
    public static void resetFields(TextField... fields) {
        for (TextField field : fields) {
            field.setText("");
        }
    }

    /**
     * Resets the values of the given TextArea controls to empty strings.
     * @param textAreas the TextArea controls to reset
     */
    public static void resetTextAreas(TextArea... textAreas) {
        for (TextArea textArea : textAreas) {
            textArea.setText("");
        }
    }

    /**
     * Resets the values of the given DatePicker controls to null.
     * @param datePickers the DatePicker controls to reset
     */
    public static void resetDateInputs(DatePicker... datePickers) {
        for (DatePicker datePicker : datePickers) {
            datePicker.setValue(null);
        }
    }
}
