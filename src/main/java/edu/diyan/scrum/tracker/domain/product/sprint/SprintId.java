package edu.diyan.scrum.tracker.domain.product.sprint;

import lombok.Value;

import java.util.UUID;

@Value
public class SprintId {
    UUID id;

    public SprintId() {
        id = UUID.randomUUID();
    }
}
