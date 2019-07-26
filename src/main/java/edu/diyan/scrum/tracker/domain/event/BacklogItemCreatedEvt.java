package edu.diyan.scrum.tracker.domain.event;

import lombok.Value;

import java.util.UUID;

@Value
public class BacklogItemCreatedEvt {
    UUID id;
    String backlogItemType;
    String title;
    String description;
}
