package quiz.runner.atishay;

import java.util.List;
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

		// Quiz 1
		java8Quiz1(teams);

		// Quiz 2
		java8Quiz2(teams);
	}

	private static void java8Quiz1(List<Team> teams) {
		// Case 1
		System.out.println("***Java 8 Quiz 1***");
		System.out.println("***Printing all the team name and home ground***");
		teams.stream().forEach(team -> System.out.println(team.getName() + ":" + team.getHomeGround()));

		// Case 2: First Way
		System.out.println();
		System.out.println("***First Way: Printing the team names in natural sorted order***");
		teams.sort((Team team1, Team team2) -> team1.getName().compareTo(team2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		// Case 2: Second Way
		System.out.println();
		System.out.println("***Second Way: Printing the home ground names in natural sorted order***");
		teams.stream().sorted((team1, team2) -> {
			return team1.getName().compareTo(team2.getName());
		}).forEach(teamName -> System.out.println(teamName.getName()));

		// Case 3: First Way
		System.out.println();
		System.out.println("***First Way:  Printing the team names in reverse sorted order***");
		teams.sort((Team team1, Team team2) -> team2.getName().compareTo(team1.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		// Case 3: Second Way
		System.out.println();
		System.out.println("***Second Way: Printing the home ground names in reverse sorted order***");
		teams.stream().sorted((team1, team2) -> {
			return team2.getName().compareTo(team1.getName());
		}).forEach(teamName -> System.out.println(teamName.getName()));

		// Case 4
		System.out.println();
		System.out.println("***First Way: Printing the home ground names in alphabetical order***");
		teams.sort((Team team1, Team team2) -> team1.getHomeGround().name().compareTo(team2.getHomeGround().name()));
		teams.forEach(team -> System.out.println(team.getHomeGround().name()));

		System.out.println();
		System.out.println("***Second Way: Printing the home ground names in alphabetical order***");
		teams.stream().sorted((team1, team2) -> {
			return team1.getHomeGround().name().compareTo(team2.getHomeGround().name());
		}).forEach(team -> System.out.println(team.getHomeGround().name()));
	}

	private static void java8Quiz2(List<Team> teams) {
		System.out.println();
		System.out.println("***Java 8 Quiz 2***");
		// CASE 1
		System.out.println("***Creating a map of Team name -> Home Ground and printing the contents.***");
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround)).entrySet()
				.forEach(System.out::println);

		// CASE 2
		System.out.println();
		System.out.println("***Printing number of matches Pune team played at a ground");
		teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting())).entrySet()
				.forEach(System.out::println);

		// CASE 3
		System.out.println();
		System.out.println("***Calculating total runs scored by Pune team***");
		long runsScored = teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
				.flatMap(team -> team.getMatches().stream()).collect(Collectors.summarizingInt(Match::getRunsScored))
				.getSum();
		System.out.println("Runs Scored: " + runsScored);

		// CASE 4
		System.out.println();
		System.out.println("***Printing Did Pune team play a match at WANKHEDE***");
		boolean puneAtWankhede = teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.anyMatch(match -> Ground.WANKHEDE.equals(match.getVenue()));
		System.out.println(puneAtWankhede);

		// CASE 5
		System.out.println();
		System.out.println("***Printing how many matches did Pune team win?***");
		System.out.println(teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.filter(match -> match.getRunsScored() > match.getRunsConceded()).count());
	}
}
