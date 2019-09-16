package edu.diyan.scrum.tracker.domain.product.backlog;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemType;
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
