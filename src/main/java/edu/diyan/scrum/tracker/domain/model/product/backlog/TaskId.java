package edu.diyan.scrum.tracker.domain.model.product.backlog;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
public class TaskId {

    UUID id;

    public TaskId() {
        id = UUID.randomUUID();
    }

}
