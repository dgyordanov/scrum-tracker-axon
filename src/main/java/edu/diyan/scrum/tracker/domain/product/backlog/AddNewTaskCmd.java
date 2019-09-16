package edu.diyan.scrum.tracker.domain.product.backlog;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.product.backlog.TaskId;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class AddNewTaskCmd {
    TaskId taskId;
    @TargetAggregateIdentifier
    BacklogItemId backlogItemId;
    String name;
    String description;
    int hoursRemaining;
    int estimatedHours;
}
