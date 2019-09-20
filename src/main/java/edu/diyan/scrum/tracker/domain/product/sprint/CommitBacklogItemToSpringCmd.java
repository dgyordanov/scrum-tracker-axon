package edu.diyan.scrum.tracker.domain.product.sprint;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CommitBacklogItemToSpringCmd {
    @TargetAggregateIdentifier
    SprintId sprintId;
    BacklogItemId backlogItemId;
}
