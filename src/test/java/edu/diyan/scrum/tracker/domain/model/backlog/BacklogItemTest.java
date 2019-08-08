package edu.diyan.scrum.tracker.domain.model.backlog;

import edu.diyan.scrum.tracker.domain.command.CreateBacklogItemCmd;
import edu.diyan.scrum.tracker.domain.event.BacklogItemCreatedEvt;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class BacklogItemTest {

    private FixtureConfiguration<BacklogItem> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(BacklogItem.class);
    }

    @Test
    public void test_CreateBacklogItemCmd_emits_BacklogItemCreatedEvt() {
        CreateBacklogItemCmd createBacklogItemCmd = new CreateBacklogItemCmd(
                new BacklogItemId(),
                BacklogItemType.SPIKE,
                "Event sourcing research",
                "Experiment with axon framework"
        );

        fixture.given()
                .when(createBacklogItemCmd)
                .expectSuccessfulHandlerExecution()
                .expectEvents(new BacklogItemCreatedEvt(
                        createBacklogItemCmd.getId(),
                        createBacklogItemCmd.getBacklogItemType(),
                        createBacklogItemCmd.getTitle(),
                        createBacklogItemCmd.getDescription()
                ));
    }

    @Test
    public void testCreateBacklogItemCmdWithoutTitleThrowException() {
        fixture.given()
                .when(new CreateBacklogItemCmd(
                        new BacklogItemId(),
                        BacklogItemType.SPIKE,
                        "",
                        "Experiment with axon framework"
                ))
                .expectException(IllegalArgumentException.class);
    }

}
