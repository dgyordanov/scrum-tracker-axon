package edu.diyan.scrum.tracker.domain.model.team;

import edu.diyan.scrum.tracker.domain.Email;
import edu.diyan.scrum.tracker.domain.command.team.CreateTeamMemberCmd;
import edu.diyan.scrum.tracker.domain.event.product.TeamMemberCreatedEvt;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class TeamMember {

    @AggregateIdentifier
    private TeamMemberId teamMemberId;
    private TeamId teamId;
    private String username;
    private Email email;
    private String firstName;
    private String lastName;

    @CommandHandler
    public TeamMember(CreateTeamMemberCmd cmd) {
        Assert.notNull(cmd.getTeamMemberId(), "teamMemberId required");
        Assert.notNull(cmd.getTeamId(), "teamId required");
        Assert.notNull(cmd.getEmail(), "email required");
        Assert.hasLength(cmd.getUsername(), "username required");

        apply(new TeamMemberCreatedEvt(
                cmd.getTeamMemberId(),
                cmd.getUsername(),
                cmd.getEmail(),
                cmd.getTeamId(),
                cmd.getFirstName(),
                cmd.getLastName()
        ));
    }

    @EventSourcingHandler
    public void on(TeamMemberCreatedEvt evt) {
        this.teamMemberId = evt.getTeamMemberId();
        this.username = evt.getUsername();
        this.email = evt.getEmail();
        this.teamId = evt.getTeamId();
        this.firstName = evt.getFirstName();
        this.lastName = evt.getLastName();
    }

}
