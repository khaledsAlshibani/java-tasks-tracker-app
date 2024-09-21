package biz.technway.khaled.trackmytasks.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import biz.technway.khaled.trackmytasks.model.TaskModel;
import biz.technway.khaled.trackmytasks.utils.TaskValidationAlert;
import biz.technway.khaled.trackmytasks.utils.TaskValidator;

public class TaskController {

    private final List<TaskModel> tasks = new ArrayList<>();
    private final TaskValidator validator = new TaskValidator();

    public List<TaskModel> getTasks() {
        tasks.forEach(TaskModel::updateTaskStatus);
        return tasks;
    }

    public void addTask(String title, String description, String label, LocalDate deadlineDate) {
        tasks.add(new TaskModel(title, description, label, deadlineDate));
    }

    public void completeTask(TaskModel task) {
        if (task != null && !task.isArchived() && !task.isExpired()) {
            task.setCompleted();
        }
    }

    public void archiveTask(TaskModel task) {
        if (task != null && !task.isArchived()) {
            task.setArchived();
        }
    }

    public void unarchiveTask(TaskModel task) {
        if (task != null && task.isArchived()) {
            task.setUnarchive();
        }
    }

    public void deleteTask(TaskModel task) {
        tasks.remove(task);
    }

    public List<TaskModel> getActiveTasks() {
        return tasks.stream()
                .filter(task -> task.isActive())
                .collect(Collectors.toList());
    }

    public List<TaskModel> getExpiredTasks() {
        return tasks.stream()
                .filter(TaskModel::isExpired)
                .collect(Collectors.toList());
    }

    public List<TaskModel> getArchivedTasks() {
        return tasks.stream()
                .filter(TaskModel::isArchived)
                .collect(Collectors.toList());
    }

    public List<TaskModel> getCompletedTasks() {
        return tasks.stream()
                .filter(TaskModel::isCompleted)
                .collect(Collectors.toList());
    }

    public List<TaskModel> findTasksByLabel(String label) {
        return tasks.stream()
                .filter(task -> task.getLabel().equalsIgnoreCase(label))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    public int countTasks() {
        return tasks.size();
    }

    public int countCompletedTasks() {
        return getCompletedTasks().size();
    }

    public int countExpiredTasks() {
        return getExpiredTasks().size();
    }

    public int countActiveTasks() {
        return getActiveTasks().size();
    }

    public int countArchivedTasks() {
        return getArchivedTasks().size();
    }

    public List<TaskModel> sortTasksByDeadline() {
        return tasks.stream()
                .sorted((task1, task2) -> task1.getDeadlineDate().compareTo(task2.getDeadlineDate()))
                .collect(Collectors.toList());
    }

    public void saveTask(String title, String description, String label, LocalDate deadline) {
        if (validator.validateTask(title, description, label, deadline)) {
            addTask(title, description, label, deadline);
            TaskValidationAlert.showSuccessAlert("Success", "Task saved successfully.");
        } else {
            TaskValidationAlert.showErrorAlert("Error: ", "Please ensure all fields are filled correctly.");
        }
    }
}
