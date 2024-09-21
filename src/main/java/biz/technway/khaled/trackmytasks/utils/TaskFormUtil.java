package biz.technway.khaled.trackmytasks.utils;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaskFormUtil {
    public static void resetFields(TextField... fields) {
        for (TextField field : fields) {
            field.setText("");
        }
    }

    public static void resetTextAreas(TextArea... textAreas) {
        for (TextArea textArea : textAreas) {
            textArea.setText("");
        }
    }

    public static void resetDateInputs(DatePicker... datePickers) {
        for (DatePicker datePicker : datePickers) {
            datePicker.setValue(null);
        }
    }
}
