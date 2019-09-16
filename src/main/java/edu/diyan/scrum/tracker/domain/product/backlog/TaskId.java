package edu.diyan.scrum.tracker.domain.product.backlog;

import lombok.Value;

import java.util.UUID;

@Value
public class TaskId {

    UUID id;

    public TaskId() {
        id = UUID.randomUUID();
    }

}
