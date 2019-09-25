package edu.diyan.scrum.tracker.domain.product.sprint;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import lombok.Value;

@Value
public class ItemCommittedToSprintEvt {
    SprintId sprintId;
    BacklogItemId backlogItemId;
}
