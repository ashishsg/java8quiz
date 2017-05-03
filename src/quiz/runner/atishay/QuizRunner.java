package quiz.runner.atishay;

import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;

public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();

		// Case 1
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
}
