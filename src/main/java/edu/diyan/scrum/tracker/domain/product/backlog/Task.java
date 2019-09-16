package edu.diyan.scrum.tracker.domain.product.backlog;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.axonframework.modelling.command.EntityId;

@Getter
@EqualsAndHashCode
public class Task {

    @EntityId
    private TaskId taskId;
    private String name;
    private String description;
    private int hoursRemaining;
    private int estimatedHours;
    private TaskStatus status;

    public Task(TaskId taskId, String name, String description, int hoursRemaining, int estimatedHours, TaskStatus status) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.hoursRemaining = hoursRemaining;
        this.estimatedHours = estimatedHours;
        this.status = status;
    }

}
