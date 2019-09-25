package edu.diyan.scrum.tracker.domain.product.sprint;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Sprint {

    @AggregateIdentifier
    private SprintId sprintId;
    private Instant begins;
    private Instant ends;
    private String name;
    private String goal;
    private List<CommittedItem> committedItems;

    @CommandHandler
    public Sprint(CreateSprintCmd cmd) {
        Assert.notNull(cmd.getSprintId(), "SprintId required");
        Assert.hasLength(cmd.getName(), "Name required");
        Assert.notNull(cmd.getBegins(), "Begin date required");
        Assert.notNull(cmd.getEnds(), "End date required");
        Assert.isTrue(cmd.getBegins().isBefore(cmd.getEnds()), "Begin date must be before end date");

        apply(new SprintCreatedEvent(
                cmd.getSprintId(),
                cmd.getBegins(),
                cmd.getEnds(),
                cmd.getName(),
                cmd.getGoal()
        ));
    }

    @CommandHandler
    public void on(CommitToSpringCmd cmd) {
        Assert.notNull(cmd.getBacklogItemId(), "Backlog item ID is required");
        apply(new ItemCommittedToSprintEvt(
                cmd.getSprintId(),
                cmd.getBacklogItemId()
        ));
    }

    @EventSourcingHandler
    public void on(SprintCreatedEvent evt) {
        this.sprintId = evt.getSprintId();
        this.begins = evt.getBegins();
        this.ends = evt.getEnds();
        this.name = evt.getName();
        this.goal = evt.getGoal();
        this.committedItems = new LinkedList<>();
    }

    @EventSourcingHandler
    public void on(ItemCommittedToSprintEvt evt) {
        boolean isItemAlreadyInSprint = committedItems.stream()
                .anyMatch(item -> item.getBacklogItemId().equals(evt.getBacklogItemId()));
        if (!isItemAlreadyInSprint) {
            addAtLastPosition(evt.getBacklogItemId());
        }
    }

    private void addAtLastPosition(BacklogItemId backlogItemId) {
        committedItems.add(new CommittedItem(backlogItemId, committedItems.size() + 1));
    }

}
