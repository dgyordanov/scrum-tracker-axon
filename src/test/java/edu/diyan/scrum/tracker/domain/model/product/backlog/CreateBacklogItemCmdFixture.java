package edu.diyan.scrum.tracker.domain.model.product.backlog;

import edu.diyan.scrum.tracker.domain.command.CreateBacklogItemCmd;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@AllArgsConstructor
@NoArgsConstructor
public class CreateBacklogItemCmdFixture {

    @Wither
    private BacklogItemId backlogItemId = new BacklogItemId();

    @Wither
    private BacklogItemType backlogItemType = BacklogItemType.SPIKE;

    @Wither
    private String title = "Event sourcing research";

    @Wither
    private String description = "Experiment with axon framework";

    public CreateBacklogItemCmd build() {
        return new CreateBacklogItemCmd(
                backlogItemId,
                backlogItemType,
                title,
                description
        );
    }

}
