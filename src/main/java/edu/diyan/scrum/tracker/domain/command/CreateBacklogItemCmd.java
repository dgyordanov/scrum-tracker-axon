package edu.diyan.scrum.tracker.domain.command;

import lombok.Value;

import java.util.UUID;

@Value
public class CreateBacklogItemCmd {
    UUID id;
    String backlogItemType;
    String title;
    String description;
}
