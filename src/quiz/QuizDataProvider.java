package quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizDataProvider {
	public static final String TEAM1 = "Rising Super Gients";
	public static final String TEAM2 = "Royal Challengers Benglore";
	public static final String TEAM3 = "Mumbai Indians";
	public static final String TEAM4 = "Kolkata Night Riders";
	private static final List<Team> teams;
	static {
		List<Match> team1Matches = Arrays.asList(new Match(TEAM2, 150, 120, 148, 120, Ground.GAHUNJE),
				new Match(TEAM3, 111, 98, 113, 68, Ground.GAHUNJE),
				new Match(TEAM4, 190, 120, 168, 120, Ground.EDENGARDENS));

		List<Match> team2Matches = Arrays.asList(new Match(TEAM1, 148, 120, 150, 120, Ground.GAHUNJE),
				new Match(TEAM3, 200, 120, 197, 120, Ground.CHINNASWAMI),
				new Match(TEAM4, 95, 111, 88, 99, Ground.CHINNASWAMI));

		List<Match> team3Matches = Arrays.asList(new Match(TEAM1, 113, 68, 111, 98, Ground.GAHUNJE),
				new Match(TEAM2, 197, 120, 200, 120, Ground.CHINNASWAMI),
				new Match(TEAM4, 208, 120, 210, 119, Ground.WANKHEDE));

		List<Match> team4Matches = Arrays.asList(new Match(TEAM1, 168, 120, 190, 120, Ground.EDENGARDENS),
				new Match(TEAM4, 88, 99, 95, 111, Ground.CHINNASWAMI),
				new Match(TEAM3, 210, 119, 208, 120, Ground.WANKHEDE));

		teams = Arrays.asList(new Team(TEAM1, Ground.GAHUNJE, team1Matches),
				new Team(TEAM2, Ground.CHINNASWAMI, team2Matches), new Team(TEAM3, Ground.WANKHEDE, team3Matches),
				new Team(TEAM4, Ground.EDENGARDENS, team4Matches));
	}

	private QuizDataProvider() {
	}

	public static List<Team> getTeams() {
		return new ArrayList<>(teams);
	}
}
