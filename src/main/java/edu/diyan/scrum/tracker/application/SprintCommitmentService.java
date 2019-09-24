package edu.diyan.scrum.tracker.application;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemCommittedEvt;
import edu.diyan.scrum.tracker.domain.product.sprint.CommitToSpringCmd;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SprintCommitmentService {

    private final CommandGateway commandGateway;

    @EventHandler
    public void handle(BacklogItemCommittedEvt backlogItemCommittedEvt) {
        commandGateway.send(
                new CommitToSpringCmd(
                        backlogItemCommittedEvt.getSprintId(),
                        backlogItemCommittedEvt.getBacklogItemId()
                )
        );
    }

}
