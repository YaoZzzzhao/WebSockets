package org.demo.model;

import java.time.LocalDateTime;

public class TaskJoin {
    private Long id;
    private String actionName;
    private String taskName;
    private LocalDateTime createdTime;
    private String status;

    public TaskJoin(Long id, String actionName, String taskName, LocalDateTime createdTime, String status) {
        this.id = id;
        this.actionName = actionName;
        this.taskName = taskName;
        this.createdTime = createdTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskJoin{" +
                "id=" + id +
                ", actionName='" + actionName + '\'' +
                ", taskName='" + taskName + '\'' +
                ", createdTime=" + createdTime +
                ", status='" + status + '\'' +
                '}';
    }
}
