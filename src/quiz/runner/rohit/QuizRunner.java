package quiz.runner.rohit;

import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;

public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		System.out.println(teams);

		System.out.println("---------------1------------------");
		// Print all the team name and home ground.
		teams.forEach(
				team -> System.out.println("Name : " + team.getName() + " Home Ground : " + team.getHomeGround()));

		System.out.println("---------------2------------------");
		// Print the team names in natural sorted order
		teams.sort((Team team1, Team team2) -> team1.getName().compareTo(team2.getName()));
		teams.forEach(team -> System.out.println("Name : " + team.getName()));

		System.out.println("---------------3------------------");
		// Print the team names in reverse sorted order.
		teams.sort((Team team1, Team team2) -> team2.getName().compareTo(team1.getName()));
		teams.forEach(team -> System.out.println("Name : " + team.getName()));

		System.out.println("---------------4------------------");
		// Print the home ground names in alphabetical order.
		teams.sort((Team team1, Team team2) -> team1.getHomeGround().toString()
				.compareTo(team2.getHomeGround().toString()));
		teams.forEach(team -> System.out.println("Home Ground : " + team.getHomeGround()));

	}

}
