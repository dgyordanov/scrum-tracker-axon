package edu.diyan.scrum.tracker.domain.model.product.backlog;

import edu.diyan.scrum.tracker.domain.event.product.BacklogItemCreatedEvt;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@AllArgsConstructor
@NoArgsConstructor
public class BacklogItemCreatedEvtFixture {

    @Wither
    private BacklogItemId id = new BacklogItemId();

    @Wither
    private BacklogItemType backlogItemType = BacklogItemType.STORY;

    @Wither
    private String title = "Event sourcing research";

    @Wither
    private String description = "Experiment with axon framework";

    public BacklogItemCreatedEvt build() {
        return new BacklogItemCreatedEvt(
                id,
                backlogItemType,
                title,
                description
        );
    }

}
