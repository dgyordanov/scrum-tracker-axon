package edu.diyan.scrum.tracker.domain.product.sprint;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Instant;

@Value
public class CreateSprintCmd {
    @TargetAggregateIdentifier
    SprintId sprintId;
    Instant begins;
    Instant ends;
    String name;
    String goal;
}
