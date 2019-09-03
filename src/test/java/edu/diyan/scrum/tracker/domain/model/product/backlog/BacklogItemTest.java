package edu.diyan.scrum.tracker.domain.model.product.backlog;

import edu.diyan.scrum.tracker.domain.event.BacklogItemCreatedEvt;
import edu.diyan.scrum.tracker.domain.event.NewTaskAddedToBacklogItemEvt;
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
        var createBacklogItemCmd = new CreateBacklogItemCmdFixture().build();

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
                .when(new CreateBacklogItemCmdFixture().withTitle(null).build())
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateBacklogItemCmdWithoutBacklogItemTypeThrowException() {
        fixture.given()
                .when(new CreateBacklogItemCmdFixture().withBacklogItemType(null).build())
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testBacklogItemCreatedEvtConsumption() {
        var backlogItem = new BacklogItem();
        var backlogItemCreatedEvt = new BacklogItemCreatedEvtFixture().build();

        backlogItem.on(backlogItemCreatedEvt);

        assertThat(backlogItem.getBacklogItemId()).isEqualTo(backlogItemCreatedEvt.getId());
        assertThat(backlogItem.getBacklogItemType()).isEqualTo(backlogItemCreatedEvt.getBacklogItemType());
        assertThat(backlogItem.getTitle()).isEqualTo(backlogItemCreatedEvt.getTitle());
        assertThat(backlogItem.getDescription()).isEqualTo(backlogItemCreatedEvt.getDescription());
        assertThat(backlogItem.getTasks()).isEmpty();
    }

    @Test
    public void test_AddNewTaskCmd_emits_NewTaskAddedToBacklogItemEvt() {
        BacklogItemId backlogItemId = new BacklogItemId();

        var addNewTaskCmd = new AddNewTaskCmdFixture()
                .withBacklogItemId(backlogItemId)
                .build();

        fixture.given(new BacklogItemCreatedEvtFixture().withId(backlogItemId).build())
                .when(addNewTaskCmd)
                .expectEvents(new NewTaskAddedToBacklogItemEvt(
                                addNewTaskCmd.getTaskId(),
                                addNewTaskCmd.getBacklogItemId(),
                                addNewTaskCmd.getName(),
                                addNewTaskCmd.getDescription(),
                                addNewTaskCmd.getHoursRemaining(),
                                addNewTaskCmd.getEstimatedHours()
                        )
                );
    }

    @Test
    public void addNewTaskCmdNoBacklogItemIdThrowsException() {
        var addNewTaskCmd = new AddNewTaskCmdFixture()
                .withBacklogItemId(null)
                .build();

        fixture.given()
                .when(addNewTaskCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void addNewTaskCmdNoTaskIdThrowsException() {
        var backlogItemId = new BacklogItemId();

        var addNewTaskCmd = new AddNewTaskCmdFixture()
                .withTaskId(null)
                .withBacklogItemId(backlogItemId)
                .build();

        fixture.given(new BacklogItemCreatedEvtFixture().withId(backlogItemId).build())
                .when(addNewTaskCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void addNewTaskCmdNoNameThrowsException() {
        var addNewTaskCmd = new AddNewTaskCmdFixture()
                .withName("")
                .build();

        fixture.given(new BacklogItemCreatedEvtFixture().withId(addNewTaskCmd.getBacklogItemId()).build())
                .when(addNewTaskCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void addNewTaskCmdNegativeEstimatedHoursThrowsException() {
        var addNewTaskCmd = new AddNewTaskCmdFixture()
                .withEstimatedHours(-1)
                .build();

        fixture.given(new BacklogItemCreatedEvtFixture().withId(addNewTaskCmd.getBacklogItemId()).build())
                .when(addNewTaskCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void addNewTaskCmdNegativeHoursRemainingThrowsException() {
        var addNewTaskCmd = new AddNewTaskCmdFixture()
                .withHoursRemaining(-1)
                .build();

        fixture.given(new BacklogItemCreatedEvtFixture().withId(addNewTaskCmd.getBacklogItemId()).build())
                .when(addNewTaskCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testNewTaskAddedToBacklogItemEvtConsumption() {
        var backlogItem = new BacklogItem();
        var backlogItemCreatedEvt = new BacklogItemCreatedEvtFixture().build();
        backlogItem.on(backlogItemCreatedEvt);

        NewTaskAddedToBacklogItemEvt newTaskAddedToBacklogItemEvt = new NewTaskAddedToBacklogItemEvtFixture()
                .withBacklogItemId(backlogItem.getBacklogItemId())
                .build();

        backlogItem.on(newTaskAddedToBacklogItemEvt);

        assertThat(backlogItem.getTasks()).hasSize(1);
        assertThat(backlogItem.getTasks().get(0).getTaskId()).isEqualTo(newTaskAddedToBacklogItemEvt.getTaskId());
        assertThat(backlogItem.getTasks().get(0).getName()).isEqualTo(newTaskAddedToBacklogItemEvt.getName());
        assertThat(backlogItem.getTasks().get(0).getDescription()).isEqualTo(newTaskAddedToBacklogItemEvt.getDescription());
        assertThat(backlogItem.getTasks().get(0).getEstimatedHours()).isEqualTo(newTaskAddedToBacklogItemEvt.getEstimatedHours());
        assertThat(backlogItem.getTasks().get(0).getHoursRemaining()).isEqualTo(newTaskAddedToBacklogItemEvt.getHoursRemaining());
        assertThat(backlogItem.getTasks().get(0).getStatus()).isEqualTo(TaskStatus.NOT_STARTED);
    }

}
