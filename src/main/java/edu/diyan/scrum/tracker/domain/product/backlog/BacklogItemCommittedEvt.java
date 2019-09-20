package edu.diyan.scrum.tracker.domain.product.backlog;

import edu.diyan.scrum.tracker.domain.product.sprint.SprintId;
import lombok.Value;

@Value
public class BacklogItemCommittedEvt {
    BacklogItemId backlogItemId;
    SprintId sprintId;

}
