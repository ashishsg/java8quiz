package quiz.runner.abhishek;

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

		System.out.println("**********QUIZ 1**********");
		System.out.println("\nTeam Names: ");
		System.out.println(teams);

		System.out.println("\nTeam Name - Home Ground Names");
		teams.forEach(team -> System.out.println(team.getName() + " - " + team.getHomeGround()));

		System.out.println("\nTeam Names Sorted in Natural Order: ");
		teams.sort((Team team1, Team team2) -> team1.getName().compareTo(team2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println("\nTeam Names Sorted in Reverse Order: ");
		teams.sort((Team team1, Team team2) -> team2.getName().compareTo(team1.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println("\nHome Ground Names in Alphabetical Order: ");
		List<Ground> homeGrounds = Arrays.asList(Ground.values());
		homeGrounds.sort((Ground ground1, Ground ground2) -> ground1.name().compareTo(ground2.name()));
		homeGrounds.stream().forEach(ground -> {
			System.out.println(ground.name());
		});

		System.out.println("**********QUIZ 2**********");
		System.out.println("\nCreating map and printing the Team name and Home ground: ");
		teams.stream().collect(Collectors.toMap(team -> team.getName(), team -> team.getHomeGround()))
				.forEach((k, v) -> System.out.println(k + ":" + v));

		System.out.println("\nNumber of matches of Pune team, Ground Name : count");
		Map<Ground, Long> venueMatchesMap = teams.stream()
				.filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()));
		venueMatchesMap.forEach((k, v) -> System.out.println(k + ":" + v));

		System.out.println("\nTotal runs scored by Pune team: ");
		Integer runsScored = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream()).collect(Collectors.summingInt(Match::getRunsScored));
		System.out.println(runsScored);

		System.out.println("\nDid Pune team play a match at WANKHEDE: ");
		boolean hasPunePlayedAtWankhede = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.anyMatch(match -> (Ground.WANKHEDE).equals(match.getVenue()));
		System.out.println(hasPunePlayedAtWankhede);

		System.out.println("\nNumber of matches win by Pune team: ");
		long numberOfMatchesWon = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.filter(match -> (match.getRunsScored() - match.getRunsConceded() > 0)).count();
		System.out.println(numberOfMatchesWon);
	}
}
