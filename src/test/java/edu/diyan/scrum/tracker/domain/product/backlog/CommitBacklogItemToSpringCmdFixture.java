package edu.diyan.scrum.tracker.domain.product.backlog;

import edu.diyan.scrum.tracker.domain.product.sprint.SprintId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@AllArgsConstructor
@NoArgsConstructor
public class CommitBacklogItemToSpringCmdFixture {
    @Wither
    BacklogItemId backlogItemId = new BacklogItemId();
    @Wither
    SprintId sprintId = new SprintId();

    public CommitBacklogItemToSpringCmd build() {
        return new CommitBacklogItemToSpringCmd(
                backlogItemId,
                sprintId
        );
    }
}
