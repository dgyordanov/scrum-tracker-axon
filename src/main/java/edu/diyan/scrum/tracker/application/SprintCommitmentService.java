package edu.diyan.scrum.tracker.application;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemCommittedEvt;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintCommitmentService {

    @Autowired
    private transient CommandGateway commandGateway;

    @EventHandler
    public void handle(BacklogItemCommittedEvt backlogItemCommittedEvt) {
        // TODO: emit command to the Sprint aggregate
    }

}
