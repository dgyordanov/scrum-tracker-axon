package edu.diyan.scrum.tracker.domain.model;

import edu.diyan.scrum.tracker.domain.command.CreateBacklogItemCmd;
import edu.diyan.scrum.tracker.domain.event.BacklogItemCreatedEvt;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Slf4j
public class BacklogItem {

    @AggregateIdentifier
    private BacklogItemId backlogItemId;
    private BacklogItemType backlogItemType;
    private String title;
    private String description;
    private SprintId sprintId;

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

    @EventSourcingHandler
    public void on(BacklogItemCreatedEvt evt) {
        this.backlogItemId = evt.getId();
        this.backlogItemType = evt.getBacklogItemType();
        this.description = evt.getDescription();
        this.title = evt.getTitle();

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
