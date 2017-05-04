package quiz.runner.ankush;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import quiz.model.Ground;
import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		System.out.println(teams);
		
		
		// 1 
		teams.stream().collect(Collectors.toMap(team -> team.getName(), team -> team.getHomeGround()))
			 .forEach((k,v)->System.out.println(k + ":" + v));
		
		// 2
		Map<Ground, Long> mapVenueMatches = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
														.flatMap(team -> team.getMatches().stream())
														.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()));
		mapVenueMatches.forEach((k,v)->System.out.println(k + ":" + v));
		
		// 3
		Integer totalRunsByRPS = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
												  .flatMap(team -> team.getMatches().stream()).collect(Collectors.summingInt(Match::getRunsScored));
		System.out.println(totalRunsByRPS);
		
		// 4
		boolean hasRPSPlayedAtWankhede = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
											   .flatMap(team -> team.getMatches().stream()).anyMatch(match -> (Ground.WANKHEDE).equals(match.getVenue()));
		System.out.println(hasRPSPlayedAtWankhede);
		
		
		// 5
		long numberOfMatchesWonByRPS = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
			 .flatMap(team -> team.getMatches().stream()).filter(match -> (match.getRunsScored() - match.getRunsConceded() > 0)).count();
		
		System.out.println(numberOfMatchesWonByRPS);
		
		
	}
	

}
