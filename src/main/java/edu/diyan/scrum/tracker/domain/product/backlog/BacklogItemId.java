package edu.diyan.scrum.tracker.domain.product.backlog;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class BacklogItemId {
    UUID id;

    public BacklogItemId() {
        id = UUID.randomUUID();
    }

}
