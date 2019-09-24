package edu.diyan.scrum.tracker.application;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemCommittedEvt;
import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import edu.diyan.scrum.tracker.domain.product.sprint.CommitToSpringCmd;
import edu.diyan.scrum.tracker.domain.product.sprint.SprintId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SprintCommitmentServiceTest {

    private SprintCommitmentService sprintCommitmentService;
    private CommandGateway commandGatewayMock;

    @Before
    public void setUp() throws Exception {
        commandGatewayMock = mock(CommandGateway.class);
        sprintCommitmentService = new SprintCommitmentService(commandGatewayMock);
    }

    @Test
    public void test_BacklogItemCommittedEvt_emits_CommitToSpringCmd() {
        var backlogItemCommittedEvt = new BacklogItemCommittedEvt(
                new BacklogItemId(),
                new SprintId()
        );

        sprintCommitmentService.handle(backlogItemCommittedEvt);

        verify(commandGatewayMock).send(
                new CommitToSpringCmd(
                        backlogItemCommittedEvt.getSprintId(),
                        backlogItemCommittedEvt.getBacklogItemId()
                )
        );
    }
}