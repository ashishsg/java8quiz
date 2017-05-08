package quiz.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizDataProvider {
	public static final String TEAM1 = "Rising Super Giants";
	public static final String TEAM2 = "Royal Challengers Bangalore";
	public static final String TEAM3 = "Mumbai Indians";
	public static final String TEAM4 = "Kolkata Night Riders";
	private static final List<Team> teams;
	private static final LocalDate MATCH1_DATE = LocalDate.of(2017, 4, 1);
	private static final LocalDate MATCH2_DATE = LocalDate.of(2017, 4, 2);
	private static final LocalDate MATCH3_DATE = LocalDate.of(2017, 4, 3);
	private static final LocalDate MATCH4_DATE = LocalDate.of(2017, 4, 4);
	private static final LocalDate MATCH5_DATE = LocalDate.of(2017, 4, 5);
	private static final LocalDate MATCH6_DATE = LocalDate.of(2017, 4, 6);

	static {
		List<Match> team1Matches = Arrays.asList(
				new Match(TEAM2, 150, 120, 148, 120, Ground.GAHUNJE, 1, MATCH1_DATE, TEAM1, false),
				new Match(TEAM3, 111, 98, 113, 68, Ground.GAHUNJE, 3, MATCH3_DATE, TEAM1, false),
				new Match(TEAM4, 190, 120, 168, 120, Ground.EDENGARDENS, 4, MATCH4_DATE, TEAM1, true));

		List<Match> team2Matches = Arrays.asList(
				new Match(TEAM1, 148, 120, 150, 120, Ground.GAHUNJE, 1, MATCH1_DATE, TEAM2, false),
				new Match(TEAM3, 200, 120, 197, 120, Ground.CHINNASWAMI, 2, MATCH2_DATE, TEAM2, true),
				new Match(TEAM4, 95, 111, 88, 99, Ground.CHINNASWAMI, 5, MATCH5_DATE, TEAM2, false));

		List<Match> team3Matches = Arrays.asList(
				new Match(TEAM1, 113, 68, 111, 98, Ground.GAHUNJE, 3, MATCH3_DATE, TEAM3, false),
				new Match(TEAM2, 197, 120, 200, 120, Ground.CHINNASWAMI, 2, MATCH2_DATE, TEAM3, true),
				new Match(TEAM4, 208, 120, 210, 119, Ground.WANKHEDE, 6, MATCH6_DATE, TEAM3, false));

		List<Match> team4Matches = Arrays.asList(
				new Match(TEAM1, 168, 120, 190, 120, Ground.EDENGARDENS, 4, MATCH4_DATE, TEAM4, true),
				new Match(TEAM2, 88, 99, 95, 111, Ground.CHINNASWAMI, 5, MATCH5_DATE, TEAM4, false),
				new Match(TEAM3, 210, 119, 208, 120, Ground.WANKHEDE, 6, MATCH6_DATE, TEAM4, false));

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
