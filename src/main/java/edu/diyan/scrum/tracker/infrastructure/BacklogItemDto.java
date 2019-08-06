package edu.diyan.scrum.tracker.infrastructure;

import lombok.Value;

@Value
class BacklogItemDto {
    private String backlogItemId;
    private String backlogItemType;
    private String title;
    private String description;
}
