package edu.diyan.scrum.tracker.domain.team;

import lombok.Value;

import java.util.UUID;

@Value
public class TeamId {
    UUID id;

    public TeamId() {
        id = UUID.randomUUID();
    }

}
