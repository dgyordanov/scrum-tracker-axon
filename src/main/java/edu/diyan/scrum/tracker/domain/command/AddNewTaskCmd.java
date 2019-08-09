package edu.diyan.scrum.tracker.domain.command;

import edu.diyan.scrum.tracker.domain.model.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.model.product.backlog.TaskId;
import lombok.Value;

@Value
public class AddNewTaskCmd {
    TaskId taskId;
    BacklogItemId backlogItemId;
    String name;
    String description;
    int hoursRemaining;
    int estimatedHours;
}
