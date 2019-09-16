package edu.diyan.scrum.tracker.domain.product.backlog;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.product.backlog.TaskId;
import lombok.Value;

@Value
public class NewTaskAddedToBacklogItemEvt {
    TaskId taskId;
    BacklogItemId backlogItemId;
    String name;
    String description;
    int hoursRemaining;
    int estimatedHours;
}
