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

    /**
     * Returns an immutable list of all tasks, sorted by their deadlines.
     * Each task's status is updated before it is returned.
     * @return the list of tasks
     */
    public List<TaskModel> getTasks() {
        tasks.forEach(TaskModel::updateTaskStatus);
        return tasks;
    }

    /**
     * Adds a new task with the given details. The task is only added if its details are valid.
     * @param title the title of the task to add
     * @param description the description of the task to add
     * @param label the label of the task to add
     * @param deadlineDate the deadline of the task to add
     * @throws IllegalArgumentException if the given details are invalid
     */
    public void addTask(String title, String description, String label, LocalDate deadlineDate) {
        tasks.add(new TaskModel(title, description, label, deadlineDate));
    }

    /**
     * Marks the given task as completed. The task is only marked as completed if it is not archived and not expired.
     * @param task the task to mark as completed
     */
    public void completeTask(TaskModel task) {
        if (task != null && !task.isArchived() && !task.isExpired()) {
            task.setCompleted();
        }
    }

    /**
     * Marks the given task as archived. The task is only marked as archived if it is not currently archived and not expired or completed.
     * @param task the task to mark as archived
     */
    public void archiveTask(TaskModel task) {
        if (task != null && !task.isArchived() && !task.isExpired() && !task.isCompleted()) {
            task.setArchived();
        }
    }

    /**
     * Marks the given task as unarchived. The task is only marked as unarchived if it is currently archived and not expired or completed.
     * @param task the task to mark as unarchived
     */
    public void unarchiveTask(TaskModel task) {
        if (task != null && task.isArchived() && !task.isExpired() && !task.isCompleted()) {
            task.setUnarchive();
        }
    }

    /**
     * Deletes the given task from the list of tasks. The task is only removed if it exists in the list of tasks.
     * @param task the task to delete
     */
    public void deleteTask(TaskModel task) {
        tasks.remove(task);
    }

    /**
     * Returns an immutable list of all active tasks.
     * @return the list of active tasks
     */
    public List<TaskModel> getActiveTasks() {
        return tasks.stream()
                .filter(task -> task.isActive())
                .collect(Collectors.toList());
    }

    /**
     * Returns an immutable list of all expired tasks.
     * @return the list of expired tasks
     */
    public List<TaskModel> getExpiredTasks() {
        return tasks.stream()
                .filter(TaskModel::isExpired)
                .collect(Collectors.toList());
    }

    /**
     * Returns an immutable list of all archived tasks.
     * @return the list of archived tasks
     */
    public List<TaskModel> getArchivedTasks() {
        return tasks.stream()
                .filter(TaskModel::isArchived)
                .collect(Collectors.toList());
    }

    /**
     * Returns an immutable list of all completed tasks.
     * @return the list of completed tasks
     */
    public List<TaskModel> getCompletedTasks() {
        return tasks.stream()
                .filter(TaskModel::isCompleted)
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of tasks currently stored in the application.
     * @return the number of tasks
     */
    public int countTasks() {
        return tasks.size();
    }

    /**
     * Returns the number of tasks that have been marked as completed.
     * @return the number of completed tasks
     */
    public int countCompletedTasks() {
        return getCompletedTasks().size();
    }

    /**
     * Returns the number of tasks that have expired.
     * @return the number of expired tasks
     */
    public int countExpiredTasks() {
        return getExpiredTasks().size();
    }
    
    /**
     * Returns the number of tasks that are currently active.
     * @return the number of active tasks
     */
    public int countActiveTasks() {
        return getActiveTasks().size();
    }

    /**
     * Returns the number of tasks that have been marked as archived.
     * @return the number of archived tasks
     */
    public int countArchivedTasks() {
        return getArchivedTasks().size();
    }

    /**
     * Saves a new task with the given details, if the task's details are valid.
     * If the task is saved successfully, a success alert is shown. If the task is not saved,
     * an error alert is shown with more information.
     * @param title the title of the task to save
     * @param description the description of the task to save
     * @param label the label of the task to save
     * @param deadline the deadline of the task to save
     */
    public void saveTask(String title, String description, String label, LocalDate deadline) {
        if (validator.validateTask(title, description, label, deadline)) {
            addTask(title, description, label, deadline);
            TaskValidationAlert.showSuccessAlert("Success", "Task saved successfully.");
        } else {
            TaskValidationAlert.showErrorAlert("Error: ", "Please ensure all fields are filled correctly.");
        }
    }
}
