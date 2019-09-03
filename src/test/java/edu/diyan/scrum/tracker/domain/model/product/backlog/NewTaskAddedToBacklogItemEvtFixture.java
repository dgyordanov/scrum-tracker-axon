package edu.diyan.scrum.tracker.domain.model.product.backlog;

import edu.diyan.scrum.tracker.domain.event.NewTaskAddedToBacklogItemEvt;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@AllArgsConstructor
@NoArgsConstructor
public class NewTaskAddedToBacklogItemEvtFixture {

    @Wither
    TaskId taskId;

    @Wither
    BacklogItemId backlogItemId;

    @Wither
    String name;

    @Wither
    String description;

    @Wither
    int hoursRemaining;

    @Wither
    int estimatedHours;

    public NewTaskAddedToBacklogItemEvt build() {
        return new NewTaskAddedToBacklogItemEvt(
                taskId,
                backlogItemId,
                name,
                description,
                hoursRemaining,
                estimatedHours
        );
    }

}
