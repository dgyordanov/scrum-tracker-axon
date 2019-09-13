package edu.diyan.scrum.tracker.domain.model.team;

import lombok.Value;

import java.util.UUID;

@Value
public class TeamMemberId {
    private UUID id;

    public TeamMemberId() {
        id = UUID.randomUUID();
    }
}
