package edu.diyan.scrum.tracker.domain.product.sprint;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import lombok.Value;

@Value
public class CommittedItem {
    BacklogItemId backlogItemId;
    int order;

}
