package edu.diyan.scrum.tracker.domain.model.team;

import edu.diyan.scrum.tracker.domain.event.product.TeamMemberCreatedEvt;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamMemberTest {

    private FixtureConfiguration<TeamMember> fixture;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(TeamMember.class);
    }

    @Test
    public void test_CreateTeamMemberCmd_emits_TeamMemberCreatedEvt() {
        var createTeamMemberCmd = new CreateTeamMemberCmdFixture().build();

        fixture.given()
                .when(createTeamMemberCmd)
                .expectSuccessfulHandlerExecution()
                .expectEvents(new TeamMemberCreatedEvt(
                        createTeamMemberCmd.getTeamMemberId(),
                        createTeamMemberCmd.getUsername(),
                        createTeamMemberCmd.getEmail(),
                        createTeamMemberCmd.getTeamId(),
                        createTeamMemberCmd.getFirstName(),
                        createTeamMemberCmd.getLastName()
                ));
    }

    @Test
    public void testCreateTeamMemberCmdNoTeamMemberIdThrowsException() {
        var createTeamMemberCmd = new CreateTeamMemberCmdFixture()
                .withTeamMemberId(null)
                .build();

        fixture.given()
                .when(createTeamMemberCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateTeamMemberCmdNoTeamIdThrowsException() {
        var createTeamMemberCmd = new CreateTeamMemberCmdFixture()
                .withTeamId(null)
                .build();

        fixture.given()
                .when(createTeamMemberCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateTeamMemberCmdNoUsernameThrowsException() {
        var createTeamMemberCmd = new CreateTeamMemberCmdFixture()
                .withUsername(null)
                .build();

        fixture.given()
                .when(createTeamMemberCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testCreateTeamMemberCmdNoEmailThrowsException() {
        var createTeamMemberCmd = new CreateTeamMemberCmdFixture()
                .withEmail(null)
                .build();

        fixture.given()
                .when(createTeamMemberCmd)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void testTeamMemberCreatedEvtConsumption() {
        var teamMemberCreatedEvt = new TeamMemberCreatedEvtFixture().build();

        var teamMember = new TeamMember();
        teamMember.on(teamMemberCreatedEvt);

        assertThat(teamMember.getTeamMemberId()).isEqualTo(teamMemberCreatedEvt.getTeamMemberId());
        assertThat(teamMember.getTeamId()).isEqualTo(teamMemberCreatedEvt.getTeamId());
        assertThat(teamMember.getUsername()).isEqualTo(teamMemberCreatedEvt.getUsername());
        assertThat(teamMember.getFirstName()).isEqualTo(teamMemberCreatedEvt.getFirstName());
        assertThat(teamMember.getLastName()).isEqualTo(teamMemberCreatedEvt.getLastName());
        assertThat(teamMember.getEmail()).isEqualTo(teamMemberCreatedEvt.getEmail());
    }

}
