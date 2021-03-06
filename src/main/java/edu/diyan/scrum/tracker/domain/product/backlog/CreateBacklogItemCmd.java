package edu.diyan.scrum.tracker.domain.product.backlog;

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
