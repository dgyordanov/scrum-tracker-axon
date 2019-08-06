package edu.diyan.scrum.tracker.domain.command;

import edu.diyan.scrum.tracker.domain.model.BacklogItemId;
import edu.diyan.scrum.tracker.domain.model.BacklogItemType;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateBacklogItemCmd {
    @TargetAggregateIdentifier
    BacklogItemId id;
    BacklogItemType backlogItemType;
    String title;
    String description;
}
