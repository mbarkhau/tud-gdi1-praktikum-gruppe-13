package mis.gdi1lab07.student.soccer;

import atan2.model.Team;

/**
 * This class starts a soccer team and connects it to a running soccer server. <br />
 * Take care of using correct command line arguments.
 */
public class SoccerTeamRun {

	// team size
	private static final int TEAM_SIZE = 5;

	/**
	 * Creates a team and connects to a SoccerServer.
	 * 
	 * @param args When called with no command line arguments, creates a new team of name
	 *          "MyTeam", and connects to a SoccerServer running on localhost on port 6000.
	 *          When called with command line arguments "host" "port" "team name", creates a
	 *          team of the specified name and connects to a SoccerServer at the specified
	 *          host and port.
	 */
	public static void main(String[] args) {

		try {
			// the team
			Team t = null;

			// no command line arguments given
			if(args.length == 0) {
				t = new SoccerTeam("MyTeam", 6000, "localhost", TEAM_SIZE);
			}

			// team name given
			else if(args.length == 1) {
				String teamName = args[0];
				t = new SoccerTeam(teamName, 6000, "localhost", TEAM_SIZE);
			}

			// host, port and team name given
			else {
				String hostname = args[0];
				int port = Integer.valueOf(args[1]);
				String teamName = args[2];
				t = new SoccerTeam(teamName, port, hostname, TEAM_SIZE);
			}

			// connect to server
			t.connectAll();
		}
		catch(Exception e) {
			System.err.println("An unexpected error occured...");
			e.printStackTrace();
		}
	}
}
