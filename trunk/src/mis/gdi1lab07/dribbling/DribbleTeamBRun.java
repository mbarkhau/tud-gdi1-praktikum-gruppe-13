package mis.gdi1lab07.dribbling;

import atan2.model.Team;

/**
 * This class instantiates a dribbler team (consisting of one single dribbler) and
 * connects it to the soccer server.
 */
public class DribbleTeamBRun {

	/**
	 * Creates a DribbleTeam and connects to a SoccerServer.
	 * 
	 * @param args When called with no command line arguments, creates a new team of name
	 *          "MyTeam", and connects to a SoccerServer running on localhost, port 6000.
	 *          When called with command line arguments "team name" "host", creates a team
	 *          of the specified name and connects to a SoccerServer at the specified host.
	 */

	private static final int TEAM_SIZE = 1;

	// the port of the robocup server
	private static final int PORT = 6000;

	// hostname accepts hostname AND IP's like "192.168.0.15"
	private static String hostname = "127.0.0.1";

	private static String teamname = "TeamB";

	public static void main(String[] args) {
		if(args.length == 1)
			teamname = args[0];
		else if(args.length == 2) {
			teamname = args[0];
			hostname = args[1];
		}

		// the team
		Team t = new DribbleTeam(teamname, PORT, hostname, TEAM_SIZE);

		// connect to server
		t.connectAll();
	}
}
