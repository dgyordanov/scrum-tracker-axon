package edu.diyan.scrum.tracker.domain.model.backlog;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class TaskId {

    UUID id;

    public TaskId() {
        id = UUID.randomUUID();
    }

}
