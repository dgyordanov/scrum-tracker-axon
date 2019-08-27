package edu.diyan.scrum.tracker.domain.model.product.backlog;

import edu.diyan.scrum.tracker.domain.command.CreateBacklogItemCmd;
import edu.diyan.scrum.tracker.domain.event.BacklogItemCreatedEvt;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BacklogItemTest {

    private FixtureConfiguration<BacklogItem> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(BacklogItem.class);
    }

    @Test
    public void test_CreateBacklogItemCmd_emits_BacklogItemCreatedEvt() {
        final CreateBacklogItemCmd createBacklogItemCmd = new CreateBacklogItemCmd(
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
                ))
                .expectState(newBacklogItem -> {
                    assertThat(newBacklogItem.getBacklogItemId()).isEqualTo(createBacklogItemCmd.getId());
                    assertThat(newBacklogItem.getTitle()).isEqualTo(createBacklogItemCmd.getTitle());
                    assertThat(newBacklogItem.getDescription()).isEqualTo(createBacklogItemCmd.getDescription());
                    assertThat(newBacklogItem.getBacklogItemType()).isEqualTo(createBacklogItemCmd.getBacklogItemType());
                    assertThat(newBacklogItem.getTasks()).isEmpty();
                    assertThat(newBacklogItem.getSprintId()).isNull();
                });
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

    @Test
    public void testCreateBacklogItemCmdWithoutBacklogItemTypeThrowException() {
        fixture.given()
                .when(new CreateBacklogItemCmd(
                        new BacklogItemId(),
                        null,
                        "Title",
                        "Experiment with axon framework"
                ))
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testBacklogItemCreatedEvtConsumption() {
        BacklogItem backlogItem = new BacklogItem();
        BacklogItemId backlogItemId = new BacklogItemId();
        String title = "Event sourcing research";
        String description = "Experiment with axon framework";
        backlogItem.on(new BacklogItemCreatedEvt(
                backlogItemId,
                BacklogItemType.BUG,
                title,
                description
        ));

        assertThat(backlogItem.getBacklogItemId()).isEqualTo(backlogItemId);
        assertThat(backlogItem.getBacklogItemType()).isEqualTo(BacklogItemType.BUG);
        assertThat(backlogItem.getTitle()).isEqualTo(title);
        assertThat(backlogItem.getDescription()).isEqualTo(description);
        assertThat(backlogItem.getTasks()).isEmpty();
    }

}
