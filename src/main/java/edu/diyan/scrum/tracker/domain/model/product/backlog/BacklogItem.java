package edu.diyan.scrum.tracker.domain.model.product.backlog;

import edu.diyan.scrum.tracker.domain.command.AddNewTaskCmd;
import edu.diyan.scrum.tracker.domain.command.CreateBacklogItemCmd;
import edu.diyan.scrum.tracker.domain.event.BacklogItemCreatedEvt;
import edu.diyan.scrum.tracker.domain.event.NewTaskAddedToBacklogItemEvt;
import edu.diyan.scrum.tracker.domain.model.product.sprint.Sprint;
import edu.diyan.scrum.tracker.domain.model.product.sprint.SprintId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Getter
@Slf4j
@EqualsAndHashCode
public class BacklogItem {

    @AggregateIdentifier
    private BacklogItemId backlogItemId;
    private BacklogItemType backlogItemType;
    private String title;
    private String description;
    private SprintId sprintId;
    @AggregateMember
    private List<Task> tasks = new ArrayList<>();

    @CommandHandler
    public BacklogItem(CreateBacklogItemCmd cmd) {
        Assert.notNull(cmd.getBacklogItemType(), "BacklogItemType required");
        Assert.hasLength(cmd.getTitle(), "Title required");

        apply(new BacklogItemCreatedEvt(
                cmd.getId(),
                cmd.getBacklogItemType(),
                cmd.getTitle(),
                cmd.getDescription()
        ));
    }

    @CommandHandler
    public void on(AddNewTaskCmd cmd) {
        Assert.notNull(cmd.getTaskId(), "TaskId required");
        Assert.notNull(cmd.getBacklogItemId(), "TaskId required");
        Assert.hasLength(cmd.getName(), "Task name should not be empty");
        Assert.isTrue(cmd.getHoursRemaining() >= 0, "Remaining hours should not be negative");
        Assert.isTrue(cmd.getEstimatedHours() >= 0, "Estimated hours should not be negative");

        apply(new NewTaskAddedToBacklogItemEvt(
                cmd.getTaskId(),
                cmd.getBacklogItemId(),
                cmd.getName(),
                cmd.getDescription(),
                cmd.getHoursRemaining(),
                cmd.getEstimatedHours()
        ));
    }

    @EventSourcingHandler
    public void on(BacklogItemCreatedEvt evt) {
        this.backlogItemId = evt.getId();
        this.backlogItemType = evt.getBacklogItemType();
        this.description = evt.getDescription();
        this.title = evt.getTitle();
        this.tasks = new ArrayList<>();
    }

    @EventSourcingHandler
    public void on(NewTaskAddedToBacklogItemEvt evt) {
        var newTask = new Task(
                evt.getTaskId(),
                evt.getName(),
                evt.getDescription(),
                evt.getHoursRemaining(),
                evt.getEstimatedHours(),
                TaskStatus.NOT_STARTED
        );
        this.getTasks().add(newTask);
    }

    public void commitToSprint(Sprint sprint) {

    }

    public void uncommitFromSprint() {

    }

    public void scheduleForSprint(Sprint sprint) {

    }

    public void unscheduleForSprint(Sprint sprint) {

    }

}
