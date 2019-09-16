package edu.diyan.scrum.tracker.domain.product.backlog;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemType;
import lombok.Value;

@Value
public class BacklogItemCreatedEvt {
    BacklogItemId id;
    BacklogItemType backlogItemType;
    String title;
    String description;
}
