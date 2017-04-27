package quiz;

import static quiz.QuizDataProvider.TEAM1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
public class QuizRunner {

	private QuizRunner() {

	}
	public static void main(String[] args) {
		
		List<Team> teams = QuizDataProvider.getTeams();
		
		//Quiz 1: Create a map of Team name -> Home Ground 
		Map<String, Ground> teamNameToHomeGroundMap =  teams.stream().collect(Collectors.toMap(Team :: getName, Team :: getHomeGround));
		teamNameToHomeGroundMap.entrySet().stream().forEach(System.out :: println);
		//Quiz 2: Count how many matches Pune team played at each ground
		
		Map<Ground, Long> result = teams.stream().filter(t -> t.getName().equals(TEAM1)).findFirst().get().
		getMatches().stream().collect(Collectors.groupingBy(Match :: getVenue,Collectors.counting()));
		result.entrySet().stream().forEach(System.out :: println);
		
		//Quiz 3: Find the runs scored by pune team:
		Optional<Integer> totalRunsScored = teams.stream().filter(t -> t.getName().equals(TEAM1)).findAny().get().
		getMatches().stream().map(Match::getRunsScored).reduce(Integer::sum);
		totalRunsScored.ifPresent(System.out::println);
		
		//Quiz 4: Find if pune team played at least 1 match at WANKHEDE
		boolean playedAtWankhede = teams.stream().filter(t -> t.getName().equals(TEAM1)).findAny().get().getMatches()
				.stream().anyMatch(m -> Ground.WANKHEDE == m.getVenue());
		System.out.println("Did Pune team played any match at Wankhede - " + playedAtWankhede);
		
		//Quiz 5: List of matches won by pune
		List<String> matchesWon = teams.stream().filter(t -> t.getName().equals(TEAM1)).findAny().get().getMatches()
				.stream().map(m -> {
			if(m.getRunsScored() > m.getRunsConceded()) return m.getOpponent();
			return null;
		}).filter(s -> s != null).collect(Collectors.toList());
		
		System.out.println("Pune team defeated : " + matchesWon);
		
		//Quiz 6: Find NRR for pune
		Team puneTeam = teams.stream().filter(t -> t.getName().equals(TEAM1)).findAny().get();
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
