package edu.diyan.scrum.tracker.domain.team;

import edu.diyan.scrum.tracker.domain.Email;
import edu.diyan.scrum.tracker.domain.team.TeamId;
import edu.diyan.scrum.tracker.domain.team.TeamMemberId;
import lombok.Value;

@Value
public class TeamMemberCreatedEvt {
    TeamMemberId teamMemberId;
    String username;
    Email email;
    TeamId teamId;
    String firstName;
    String lastName;
}
