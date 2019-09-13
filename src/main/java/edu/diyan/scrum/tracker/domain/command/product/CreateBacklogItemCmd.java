package edu.diyan.scrum.tracker.domain.command.product;

import edu.diyan.scrum.tracker.domain.model.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.model.product.backlog.BacklogItemType;
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
