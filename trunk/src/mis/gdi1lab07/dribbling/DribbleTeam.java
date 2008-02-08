package mis.gdi1lab07.dribbling;

import atan2.model.Controller;
import atan2.model.Player;
import atan2.model.Team;

/**
 * A team of dribblers.
 */
public class DribbleTeam extends Team {

	public DribbleTeam(String teamName, int port, String hostname, int teamSize) {
		super(teamName, port, hostname, teamSize);
	}

	public DribbleTeam(String teamName, int teamSize) {
		super(teamName, teamSize);
	}

	public DribbleTeam(String teamName) {
		super(teamName);
	}

	public DribbleTeam(String name, int port, String hostname) {
		super(name, port, hostname);
	}

	@Override
	public Controller getNewController(int i, Player p) {
		/*
		 * We just have one controller type in this example. So always return DribblePlayer.
		 */
		return new DribblePlayer();
	}

	@Override
	public int getLabGroupId() {
		return -1;
	}

	@Override
	public String getLabGroupName() {
		return null;
	}

}
