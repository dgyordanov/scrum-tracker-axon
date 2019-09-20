package edu.diyan.scrum.tracker.domain.product.sprint;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.time.Duration;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
public class SprintCreatedEventFixture {

    @Wither
    SprintId sprintId = new SprintId();
    @Wither
    Instant begins = Instant.now().minus(Duration.ofDays(7));
    @Wither
    Instant ends = Instant.now().plus(Duration.ofDays(7));
    @Wither
    String name = "Test sprint name";
    @Wither
    String goal = "Test sprint goal";

    public SprintCreatedEvent build() {
        return new SprintCreatedEvent(
                sprintId,
                begins,
                ends,
                name,
                goal
        );
    }
}
