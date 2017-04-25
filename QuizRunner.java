package quiz;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class QuizRunner {
	private static String team1 = "Rising Super Gients";
	private static String team2 = "Royal Challengers Benglore";
	private static String team3 = "Mumbai Indians";
	private static String team4 = "Kolkata Night Riders";
	public static void main(String[] args) {
		List<Match> team1Matches = Arrays.asList(
				new Match(team2, 150, 120, 148, 120, Ground.GAHUNJE),
				new Match(team3, 111, 98, 113, 68, Ground.GAHUNJE),
				new Match(team4, 190, 120, 168, 120, Ground.EDENGARDENS));
		
		List<Match> team2Matches = Arrays.asList(
				new Match(team1, 148, 120, 150, 120, Ground.GAHUNJE),
				new Match(team3, 200, 120, 197, 120, Ground.CHINNASWAMI),
				new Match(team4, 95, 111, 88, 99, Ground.CHINNASWAMI));
		
		List<Match> team3Matches = Arrays.asList(
				new Match(team1, 113, 68, 111, 98, Ground.GAHUNJE),
				new Match(team2, 197, 120, 200, 120, Ground.CHINNASWAMI),
				new Match(team4, 208, 120, 210, 119, Ground.WANKHEDE));
		
		List<Match> team4Matches = Arrays.asList(
				new Match(team1, 168, 120, 190, 120, Ground.EDENGARDENS),
				new Match(team4, 88, 99, 95, 111, Ground.CHINNASWAMI),
				new Match(team3, 210, 119, 208, 120, Ground.WANKHEDE));
		
		List<Team> teams = Arrays.asList(new Team(team1, Ground.GAHUNJE, team1Matches),
				new Team(team2, Ground.CHINNASWAMI, team2Matches),
				new Team(team3, Ground.WANKHEDE, team3Matches),
				new Team(team4, Ground.EDENGARDENS, team4Matches));
		
		//Quiz 1: Create a map of Team name -> Home Ground 
		Map<String, Ground> teamNameToHomeGroundMap =  teams.stream().collect(Collectors.toMap(Team :: getName, Team :: getHomeGround));
		teamNameToHomeGroundMap.entrySet().stream().forEach(System.out :: println);
		//Quiz 2: Count how many matches Pune team played at each ground
		
		Map<Ground, Long> result = teams.stream().filter(t -> t.getName().equals(team1)).findFirst().get().
		getMatches().stream().collect(Collectors.groupingBy(Match :: getVenue,Collectors.counting()));
		result.entrySet().stream().forEach(System.out :: println);
		
		//Quiz 3: Find the runs scored by pune team:
		Optional<Integer> totalRunsScored = teams.stream().filter(t -> t.getName().equals(team1)).findAny().get().
		getMatches().stream().map(Match::getRunsScored).reduce(Integer::sum);
		totalRunsScored.ifPresent(System.out::println);
		
		//Quiz 4: Find if pune team played at least 1 match at WANKHEDE
		boolean playedAtWankhede = teams.stream().filter(t -> t.getName().equals(team1)).findAny().get().getMatches().stream().anyMatch(m ->  Ground.WANKHEDE == m.getVenue());
		System.out.println("Did Pune team played any match at Wankhede - " + playedAtWankhede);
		
		//Quiz 5: List of matches won by pune
		List<String> matchesWon = teams.stream().filter(t -> t.getName().equals(team1)).findAny().get().getMatches().stream().map(m -> {
			if(m.getRunsScored() > m.getRunsConceded()) return m.getOpponent();
			return null;
		}).filter(s -> s != null).collect(Collectors.toList());
		
		System.out.println("Pune team defeated : " + matchesWon);
		
		//Quiz 6: Find NRR for pune
		Team puneTeam = teams.stream().filter(t -> t.getName().equals(team1)).findAny().get();
		Function<List<Match>, BigDecimal> nrrCalculator = matches -> {
			BigDecimal totalRunScored = new BigDecimal(matches.stream().map(Match::getRunsScored).reduce(Integer::sum).get());
			System.out.println("Total Run scored " + totalRunScored);
			BigDecimal totalBallFaced = new BigDecimal(matches.stream().map(Match::getBallsTaken).reduce(Integer::sum).get());
			System.out.println("Total ball faced " + totalBallFaced);
			BigDecimal totalRunConceded = new BigDecimal(matches.stream().map(Match::getRunsConceded).reduce(Integer::sum).get());
			System.out.println("Total run conceded " + totalRunConceded);
			BigDecimal totalBallBowled = new BigDecimal(matches.stream().map(Match::getBallsbowled).reduce(Integer::sum).get());
			System.out.println("Total ball bowled " + totalBallBowled);
			return (totalRunScored.divide(totalBallFaced, RoundingMode.UP)).subtract(totalRunConceded.divide(totalBallBowled, RoundingMode.UP));
		};
	
		
		System.out.println(puneTeam.getNRR(nrrCalculator));
		
		

	}

}
