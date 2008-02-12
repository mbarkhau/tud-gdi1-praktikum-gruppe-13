package mis.gdi1lab07.student.soccer;

import atan2.model.Controller;
import atan2.model.Player;
import atan2.model.Team;

/**
 * Default skeleton for a soccer team.
 */
public class SoccerTeam extends Team {

	public SoccerTeam(String teamName, int teamSize) {
		super(teamName, teamSize);
	}

	public SoccerTeam(String teamName) {
		super(teamName);
	}

	public SoccerTeam(String teamName, int port, String hostname) {
		super(teamName, port, hostname);
	}

	public SoccerTeam(String teamName, int port, String hostname, int teamSize) {
		super(teamName, port, hostname, teamSize);
	}

	@Override
	public Controller getNewController(int i, Player p) {
		return new mis.gdi1lab07.student.gameBehaviour.Controller(this.getTeamName(), p);
	}

	@Override
	public int getLabGroupId() {
		return 13;
	}

	@Override
	public String getLabGroupName() {
		return null;
	}
}
