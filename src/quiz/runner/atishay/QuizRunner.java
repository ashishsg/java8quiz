package quiz.runner.atishay;

import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;

public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();

		System.out.println("***Printing all the team name and home ground***");
		// Case:1 :Printing Team name and home Ground.
		teams.stream().forEach(team -> System.out.println(team.getName() + ":" + team.getHomeGround()));

		System.out.println();
		System.out.println("***Printing the team names in natural sorted order***");
		// Case:2 :Team names in natural sorted order.
		teams.sort((Team team1, Team team2) -> team1.getName().compareTo(team2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println();
		System.out.println("***Printing the team names in reverse sorted order***");
		// Case:2 :Team names in natural sorted order.
		teams.sort((Team team1, Team team2) -> team2.getName().compareTo(team1.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println();
		System.out.println("***Printing the home ground names in alphabetical order***");
		// Case:2 :Team names in natural sorted order.
		teams.sort((Team team1, Team team2) -> team1.getHomeGround().name().compareTo(team2.getHomeGround().name()));
		teams.forEach(team -> System.out.println(team.getHomeGround().name()));
	}
}
