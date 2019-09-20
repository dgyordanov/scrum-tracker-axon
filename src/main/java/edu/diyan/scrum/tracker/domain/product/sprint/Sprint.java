package edu.diyan.scrum.tracker.domain.product.sprint;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.time.Instant;

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
    // TODO: add CommittedBacklogItems with order in the sprint

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

    @EventSourcingHandler
    public void on(SprintCreatedEvent evt) {
        this.sprintId = evt.getSprintId();
        this.begins = evt.getBegins();
        this.ends = evt.getEnds();
        this.name = evt.getName();
        this.goal = evt.getGoal();
    }

}
