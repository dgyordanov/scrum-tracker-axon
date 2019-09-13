package edu.diyan.scrum.tracker.domain.event.product;

import edu.diyan.scrum.tracker.domain.model.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.model.product.backlog.TaskId;
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
