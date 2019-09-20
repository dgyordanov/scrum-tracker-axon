package edu.diyan.scrum.tracker.domain.product.sprint;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.time.Instant;

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
    }

}