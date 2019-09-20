package edu.diyan.scrum.tracker.domain.product.backlog;

import edu.diyan.scrum.tracker.domain.product.sprint.SprintId;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CommitBacklogItemToSpringCmd {
    @TargetAggregateIdentifier
    BacklogItemId backlogItemId;
    SprintId sprintId;
}
