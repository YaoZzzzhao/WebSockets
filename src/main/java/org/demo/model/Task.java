package org.demo.model;

//import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "WF_TASK_ACTION")
public class Task {
    @Column(name = "TASK")
    private String taskName;

    @Column(name = "TASK_ACTION_STATUS")
    private String status;

    @Column(name = "CREATED_TIME")
    private LocalDateTime localDateTime = LocalDateTime.now();

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
