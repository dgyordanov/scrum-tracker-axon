package edu.diyan.scrum.tracker.domain.model.product.backlog;

import edu.diyan.scrum.tracker.domain.command.AddNewTaskCmd;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@NoArgsConstructor
@AllArgsConstructor
public class AddNewTaskCmdFixture {

    @Wither
    private TaskId taskId = new TaskId();

    @Wither
    private BacklogItemId backlogItemId = new BacklogItemId();

    @Wither
    private String name = "Task name";

    @Wither
    private String description = "Task description";

    @Wither
    private int hoursRemaining = 16;

    @Wither
    private int estimatedHours = 16;

    public AddNewTaskCmd build() {
        return new AddNewTaskCmd(
                taskId,
                backlogItemId,
                name,
                description,
                hoursRemaining,
                estimatedHours
        );
    }

}
