package quiz.runner.abhijeet;

import java.util.Arrays;
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
		
		System.out.println("********************************");
		
		// printing all the names and home grounds
		teams.forEach(team -> System.out.println(team.getName() + ":" + team.getHomeGround()));
		
		System.out.println("********************************");
		
		// printing team names sorted in natural order
		teams.sort((Team team1, Team team2) ->team1.getName().compareTo(team2.getName()));
		
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("********************************");
		
		// printing team names sorted in reverse order
		teams.sort((Team team1, Team team2)->team2.getName().compareTo(team1.getName()));
		
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("********************************");
		
		// Printing all home grounds in alphabetical order.
		List<Ground> homeGrounds = Arrays.asList(Ground.values());
		
		homeGrounds.sort((Ground ground1, Ground ground2)->ground1.name().compareTo(ground2.name()));
		homeGrounds.stream().forEach(ground -> {
			System.out.println(ground.name());
		});
		
		// Quiz 2 Solutions
		System.out.println("----------------------- Quiz 2 Solutions ---------------------------");
		System.out.println("********** 1st Question ***********");
		// 1 creating the map and printing the name of the team and it's ome ground
		teams.stream().collect(Collectors.toMap(team -> team.getName(), team -> team.getHomeGround()))
			 .forEach((k,v)->System.out.println(k + ":" + v));
		
		System.out.println("********** 2nd Question ***********");
		// 2
		Map<Ground, Long> venueMatchesMap = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
														.flatMap(team -> team.getMatches().stream())
														.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()));
		venueMatchesMap.forEach((k,v)->System.out.println(k + ":" + v));
		
		System.out.println("********** 3rd Question ***********");
		// 3
		Integer runsScored = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
												  .flatMap(team -> team.getMatches().stream()).collect(Collectors.summingInt(Match::getRunsScored));
		System.out.println(runsScored);
		
		// 4
		
		System.out.println("********** 4th Question ***********");
		
		boolean hasPunePlayedAtWankhede = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
											   .flatMap(team -> team.getMatches().stream()).anyMatch(match -> (Ground.WANKHEDE).equals(match.getVenue()));
		System.out.println(hasPunePlayedAtWankhede);
		
		System.out.println("********** 5th Question ***********");
		
		// 5
		long numberOfMatchesWon = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
			 .flatMap(team -> team.getMatches().stream()).filter(match -> (match.getRunsScored() - match.getRunsConceded() > 0)).count();
		
		System.out.println(numberOfMatchesWon);
	}

}
