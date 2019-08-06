package edu.diyan.scrum.tracker.infrastructure;

import edu.diyan.scrum.tracker.domain.command.CreateBacklogItemCmd;
import edu.diyan.scrum.tracker.domain.model.BacklogItemId;
import edu.diyan.scrum.tracker.domain.model.BacklogItemType;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BacklogItemController {

    private final CommandGateway commandGateway;

    @PostMapping("/v1/backlogItem")
    public ResponseEntity createBacklogItem(@RequestBody BacklogItemDto backlogItemDto) {
        commandGateway.send(new CreateBacklogItemCmd(
                new BacklogItemId(UUID.randomUUID()),
                BacklogItemType.valueOf(backlogItemDto.getBacklogItemType()),
                backlogItemDto.getTitle(),
                backlogItemDto.getDescription()
        ));

        return ResponseEntity.ok().build();
    }

}
