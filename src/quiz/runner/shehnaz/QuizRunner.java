package quiz.runner.shehnaz;

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
		
		System.out.println("1.Print all the team name and home ground. Sample output – \"Rising Super Giants : GAHUNJE\".");
		teams
		.forEach(team -> System.out.println(team.getName() +" : "+ team.getHomeGround()));
		
		System.out.println("2.Print the team names in natural sorted order.");
		teams
		.stream()
		.sorted(Comparator.comparing(Team::getName))
		.forEach(team -> System.out.println(team.getName()));
	
		System.out.println("3.Print the team names in reverse sorted order.");
		teams
		.stream()
		.sorted(Comparator.comparing(Team::getName).reversed())
		.forEach(team -> System.out.println(team.getName()));
	
		System.out.println("4.Print the home ground names in alphabetical order.");
		teams
		  .stream()
		  .sorted((team1, team2) -> team1.getHomeGround().name().compareTo(team2.getHomeGround().name()))
		  .forEach(team -> System.out.println(team.getHomeGround()));
	}

}
