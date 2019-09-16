package edu.diyan.scrum.tracker.domain.team;

import edu.diyan.scrum.tracker.domain.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@AllArgsConstructor
@NoArgsConstructor
class TeamMemberCreatedEvtFixture {
    @Wither
    private TeamMemberId teamMemberId = new TeamMemberId();
    @Wither
    private TeamId teamId = new TeamId();
    @Wither
    private String username = "testuser";
    @Wither
    private String firstName = "Test";
    @Wither
    private String lastName = "User";
    @Wither
    private Email email = new Email("test@user.org");

    public TeamMemberCreatedEvt build() {
        return new TeamMemberCreatedEvt(
                teamMemberId,
                username,
                email,
                teamId,
                firstName,
                lastName
        );
    }

}
