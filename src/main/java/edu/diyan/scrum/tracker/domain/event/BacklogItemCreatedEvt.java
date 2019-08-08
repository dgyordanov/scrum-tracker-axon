package edu.diyan.scrum.tracker.domain.event;

import edu.diyan.scrum.tracker.domain.model.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.model.backlog.BacklogItemType;
import lombok.Value;

@Value
public class BacklogItemCreatedEvt {
    BacklogItemId id;
    BacklogItemType backlogItemType;
    String title;
    String description;
}
