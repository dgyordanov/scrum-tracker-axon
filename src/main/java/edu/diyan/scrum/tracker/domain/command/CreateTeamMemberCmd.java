package edu.diyan.scrum.tracker.domain.command;

import edu.diyan.scrum.tracker.domain.Email;
import edu.diyan.scrum.tracker.domain.model.team.TeamId;
import edu.diyan.scrum.tracker.domain.model.team.TeamMemberId;
import lombok.Value;

@Value
public class CreateTeamMemberCmd {
    TeamMemberId teamMemberId;
    TeamId teamId;
    String username;
    String firstName;
    String lastName;
    Email email;
}
