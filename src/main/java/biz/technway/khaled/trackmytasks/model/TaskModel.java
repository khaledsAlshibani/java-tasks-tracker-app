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

    /* Title Setter & Getter */
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /* Description Setter & Getter */
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* Label Setter & Getter */
    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    /* Completed Setter & Getter */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    /* Archived Setter & Getter */
    public boolean isArchived() {
        return this.isArchived;
    }

    public void setArchived() {
        this.isArchived = true;
    }

    public void setUnarchive() {
        this.isArchived = false;
    }

    /* Creation Date Getter */
    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    /* Deadline Date Getter & Setter */
    public LocalDate getDeadlineDate() {
        return this.deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    /* Expired Setter & Getter */
    public void setExpired() {
        this.isExpired = this.deadlineDate.isAfter(LocalDate.now());
    }

    public boolean isExpired() {
        return this.isExpired;
    }

    public boolean isActive() {
        return !this.isCompleted && !this.isArchived && !this.isExpired;
    }

    public void updateTaskStatus() {
        this.isExpired = LocalDate.now().isAfter(this.deadlineDate);
    }

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

    @Override
    public String toString() {
        return String.join("\n",
                "Title: " + this.title,
                "Description: " + (this.description.isEmpty() ? "No description" : this.description),
                "Label: " + this.label,
                "Status: " + getStatus(),
                "Deadline: " + this.deadlineDate);
    }
}
