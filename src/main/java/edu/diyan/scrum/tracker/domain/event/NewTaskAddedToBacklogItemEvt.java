package edu.diyan.scrum.tracker.domain.event;

import edu.diyan.scrum.tracker.domain.model.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.model.backlog.TaskId;
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
