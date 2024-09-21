package biz.technway.khaled.trackmytasks.model;

import java.time.LocalDate;

public class TaskModel {
    private String title;
    private String description;
    private String label;
    private LocalDate creationDate;
    private LocalDate deadlineDate;
    private boolean isCompleted;
    private boolean isArchived;
    private boolean isExpired;

    public TaskModel(String title, String description, String label, LocalDate deadlineDate) {
        this.title = title;
        this.description = description;
        this.label = label;
        this.creationDate = LocalDate.now();
        this.deadlineDate = deadlineDate;
        this.isCompleted = false;
        this.isArchived = false;
        this.isExpired = false;
    }

    /**
     * @return the title of the task
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title of the task to the given title
     * @param title the new title of the task
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task
     *
     * @param description the description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the label of the task
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Set the label of the task to the given label
     * @param label the new label of the task
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return true if the task has been marked as completed, false otherwise
     * 
     * This is useful for determining whether a task should be shown in the active tasks view
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /**
     * Mark the task as completed.
     * 
     * This will prevent the task from showing up in the active tasks view.
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * @return true if the task is archived, false otherwise
     * 
     * A task is considered archived if it has been marked as archived by the user.
     * Archiving a task will remove it from the active tasks view.
     */
    public boolean isArchived() {
        return this.isArchived;
    }

    /**
     * Sets the task as archived. This will remove the task from the active tasks view.
     */
    public void setArchived() {
        this.isArchived = true;
    }

    /**
     * Sets the task as unarchived. This will make the task visible again in the active tasks view.
     */
    public void setUnarchive() {
        this.isArchived = false;
    }

    /**
     * Returns the creation date of this task. The creation date is the date on which this task was created.
     * @return the creation date of this task
     */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    /**
     * Returns the deadline date of this task. The deadline date is the date by which the task should be completed.
     * @return the deadline date of this task
     */
    public LocalDate getDeadlineDate() {
        return this.deadlineDate;
    }

    /**
     * Sets the deadline date of this task to the given date.
     * @param deadlineDate the deadline date of this task
     */
    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /**
     * Sets the expired status of this task. A task is considered expired if its deadline date is before the current date.
     */
    public void setExpired() {
        this.isExpired = this.deadlineDate.isAfter(LocalDate.now());
    }

    /**
     * Returns whether this task has expired. A task is considered expired if the
     * current date is after its deadline date.
     * @return true if the task has expired, false otherwise.
     */
    public boolean isExpired() {
        return this.isExpired;
    }

    /**
     * Returns whether this task is currently active. A task is considered active
     * if it has not been completed, archived, or expired.
     * <p>
     * In other words, if the task is not completed, not archived, and not expired.
     * @return true if the task is active, false otherwise.
     */
    public boolean isActive() {
        return !this.isCompleted && !this.isArchived && !this.isExpired;
    }

    /**
     * Updates the task's status. If the current date is after the task's deadline date, the task is considered expired.
     */
    public void updateTaskStatus() {
        this.isExpired = LocalDate.now().isAfter(this.deadlineDate);
    }

    /**
     * Returns the status of this task as a human-readable string.
     * <p>
     * The status is either "Completed", "Expired", "Archived", or "Active",
     * depending on the task's current state.
     *
     * @return the status of this task
     */
    public String getStatus() {
        if (this.isCompleted) {
            return "Completed";
        } else if (this.isExpired) {
            return "Expired";
        } else if (this.isArchived) {
            return "Archived";
        } else {
            return "Active";
        }
    }
}
