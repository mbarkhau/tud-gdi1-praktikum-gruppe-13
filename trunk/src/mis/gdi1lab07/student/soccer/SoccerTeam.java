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
		// TODO return the correct controller according to the given player number
		return null;
	}

	@Override
	public int getLabGroupId() {
		// TODO return your GroupId
		return -1;
	}

	@Override
	public String getLabGroupName() {
		// TODO return your GroupName
		return null;
	}
}
