package edu.diyan.scrum.tracker.domain.team;

import edu.diyan.scrum.tracker.domain.Email;
import edu.diyan.scrum.tracker.domain.team.TeamId;
import edu.diyan.scrum.tracker.domain.team.TeamMemberId;
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
