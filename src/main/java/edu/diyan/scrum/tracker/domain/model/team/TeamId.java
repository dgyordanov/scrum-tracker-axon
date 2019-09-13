package edu.diyan.scrum.tracker.domain.model.team;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
public class TeamId {
    UUID id;

    public TeamId() {
        id = UUID.randomUUID();
    }

}
