package edu.diyan.scrum.tracker.domain.product.sprint;

import edu.diyan.scrum.tracker.domain.product.backlog.BacklogItemId;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class SprintTest {

    private FixtureConfiguration<Sprint> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(Sprint.class);
    }

    @Test
    public void test_CreateSprintCmd_emits_SprintCreatedEvent() {
        var createSprintCmd = new CreateSprintCmdFixture().build();

        fixture.given()
                .when(createSprintCmd)
                .expectSuccessfulHandlerExecution()
                .expectEvents(new SprintCreatedEvent(
                        createSprintCmd.getSprintId(),
                        createSprintCmd.getBegins(),
                        createSprintCmd.getEnds(),
                        createSprintCmd.getName(),
                        createSprintCmd.getGoal()
                ));
    }

    @Test
    public void testCreateSprintCmdWithoutSprintIdThrowsException() {
        var createSprintCmd = new CreateSprintCmdFixture()
                .withSprintId(null)
                .build();

        fixture.given()
                .when(createSprintCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateSprintCmdWithoutNameThrowsException() {
        var createSprintCmd = new CreateSprintCmdFixture()
                .withName(null)
                .build();

        fixture.given()
                .when(createSprintCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateSprintCmdWithoutBeginThrowsException() {
        var createSprintCmd = new CreateSprintCmdFixture()
                .withBegins(null)
                .build();

        fixture.given()
                .when(createSprintCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateSprintCmdWithoutEndThrowsException() {
        var createSprintCmd = new CreateSprintCmdFixture()
                .withEnds(null)
                .build();

        fixture.given()
                .when(createSprintCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateSprintCmdWithEndBeforeBeginThrowsException() {
        Instant beginSprint = Instant.now();
        var createSprintCmd = new CreateSprintCmdFixture()
                .withBegins(beginSprint)
                .withEnds(beginSprint.minusMillis(1))
                .build();

        fixture.given()
                .when(createSprintCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testSprintCreatedEventConsumption() {
        var sprint = new Sprint();
        var sprintCreatedEvent = new SprintCreatedEventFixture().build();

        sprint.on(sprintCreatedEvent);

        assertThat(sprint.getSprintId()).isEqualTo(sprintCreatedEvent.getSprintId());
        assertThat(sprint.getBegins()).isEqualTo(sprintCreatedEvent.getBegins());
        assertThat(sprint.getEnds()).isEqualTo(sprintCreatedEvent.getEnds());
        assertThat(sprint.getName()).isEqualTo(sprintCreatedEvent.getName());
        assertThat(sprint.getGoal()).isEqualTo(sprintCreatedEvent.getGoal());
        assertThat(sprint.getCommittedItems()).isEqualTo(Collections.EMPTY_LIST);
    }

    @Test
    public void test_CommitToSpringCmd_emits_ItemCommitedToSprint() {
        var sprintCreatedEvent = new SprintCreatedEventFixture().build();

        var commitToSpringCmd = new CommitToSpringCmd(
                sprintCreatedEvent.getSprintId(),
                new BacklogItemId()
        );

        fixture.given(sprintCreatedEvent)
                .when(commitToSpringCmd)
                .expectSuccessfulHandlerExecution()
                .expectEvents(
                        new ItemCommittedToSprintEvt(
                                commitToSpringCmd.getSprintId(),
                                commitToSpringCmd.getBacklogItemId()
                        )
                );
    }

    @Test
    public void test_CommitToSpringCmdNoSprintIdThrowsException() {
        var sprintCreatedEvent = new SprintCreatedEventFixture().build();

        var commitToSpringCmd = new CommitToSpringCmd(
                null,
                new BacklogItemId()
        );

        fixture.given(sprintCreatedEvent)
                .when(commitToSpringCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void test_CommitToSpringCmdNoItemIdThrowsException() {
        var sprintCreatedEvent = new SprintCreatedEventFixture().build();

        var commitToSpringCmd = new CommitToSpringCmd(
                sprintCreatedEvent.getSprintId(),
                null
        );

        fixture.given(sprintCreatedEvent)
                .when(commitToSpringCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testItemCommittedToSprintConsumption() {
        var sprint = createSprint();

        BacklogItemId backlogItemId = new BacklogItemId();
        var itemCommittedToSprintEvt = new ItemCommittedToSprintEvt(
                sprint.getSprintId(),
                backlogItemId
        );

        sprint.on(itemCommittedToSprintEvt);

        assertThat(sprint.getCommittedItems()).hasSize(1);
        assertThat(sprint.getCommittedItems().get(0)).isEqualTo(new CommittedItem(backlogItemId, 1));
    }

    @Test
    public void testItemCommittedToSprintTwiceAddSingleCommittedItemInSprintConsumption() {
        Sprint sprint = createSprint();

        var itemCommittedToSprintEvt = new ItemCommittedToSprintEvt(
                sprint.getSprintId(),
                new BacklogItemId()
        );

        sprint.on(itemCommittedToSprintEvt);
        sprint.on(itemCommittedToSprintEvt);

        assertThat(sprint.getCommittedItems()).hasSize(1);
    }

    private Sprint createSprint() {
        var sprint = new Sprint();
        var sprintCreatedEvent = new SprintCreatedEventFixture().build();
        sprint.on(sprintCreatedEvent);
        return sprint;
    }

}
