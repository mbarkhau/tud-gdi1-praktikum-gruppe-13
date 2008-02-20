package mis.gdi1lab07.student.soccer;

import utilities.ConsoleLogger;
import mis.gdi1lab07.automaton.AutomatonException;
import mis.gdi1lab07.student.StudentHFSM;
import mis.gdi1lab07.student.gameBehaviour.hfsms.PassAI;
import mis.gdi1lab07.student.gameData.FieldPlayer;
import mis.gdi1lab07.student.gameData.FieldPosition;
import mis.gdi1lab07.student.gameData.GameEnv;
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
		FieldPlayer<GameEnv> fieldPlayer = new FieldPlayer<GameEnv>(p, new GameEnv());
		fieldPlayer.setTeamName(this.getTeamName());
		fieldPlayer.setPosition(0, new FieldPosition(0, 0));
		p.setNumber(i);
		try {
			StudentHFSM<GameEnv> passHFSM = new PassAI<GameEnv>(fieldPlayer);
			passHFSM.setName("passPlayer");
			passHFSM.setLog(new ConsoleLogger("passPlayerLog"));
			passHFSM.reset();
			return new mis.gdi1lab07.student.gameBehaviour.Controller(fieldPlayer, passHFSM);
		} catch (AutomatonException e) {
			throw new IllegalStateException("Couldn't create player.", e);
		}
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
