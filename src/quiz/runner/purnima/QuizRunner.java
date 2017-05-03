package quiz.runner.purnima;

import java.util.Comparator;
import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		System.out.println(teams);

		System.out.println("\n1.Print all the team name and home ground.");
		System.out.println("-----------------------------------------------");
		teams.forEach(team -> System.out.println(team.getName() + " : " + team.getHomeGround()));

		System.out.println("\n2.Print the team names in natural sorted order.");
		System.out.println("-----------------------------------------------");
		Comparator<Team> teamNameComparator = (team1, team2) -> team1.getName().compareTo(team2.getName());
		teams.sort(teamNameComparator);
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println("\n3.Print the team names in reverse sorted order.");
		System.out.println("-----------------------------------------------");
		teams.sort(teamNameComparator.reversed());
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println("\n4.Print the home ground names in alphabetical order.");
		System.out.println("-----------------------------------------------");
		teams.sort((team1, team2) -> team1.getHomeGround().toString().compareTo(team2.getHomeGround().toString()));
		teams.forEach(team -> System.out.println(team.getHomeGround()));
		
	}
}
