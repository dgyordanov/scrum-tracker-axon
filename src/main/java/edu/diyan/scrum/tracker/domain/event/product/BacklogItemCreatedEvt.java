package edu.diyan.scrum.tracker.domain.event.product;

import edu.diyan.scrum.tracker.domain.model.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.model.product.backlog.BacklogItemType;
import lombok.Value;

@Value
public class BacklogItemCreatedEvt {
    BacklogItemId id;
    BacklogItemType backlogItemType;
    String title;
    String description;
}