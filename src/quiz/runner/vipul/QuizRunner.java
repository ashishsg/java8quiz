package quiz.runner.vipul;

import java.util.Collections;
import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		System.out.println(teams);
		
		System.out.println("\n\n 1. Print all the team name and home ground. Sample output – Rising Super Giants : GAHUNJE.");
		teams.forEach( team -> System.out.println(team.getName() + " : " + team.getHomeGround()));
		
		System.out.println("\n\n 2. Print the team names in natural sorted order.");
		teams.sort((Team t1, Team t2) -> t1.getName().compareTo(t2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\n\n 3. Print the team names in reverse sorted order.");
		teams.sort((Team t1, Team t2) -> t1.getName().compareTo(t2.getName()));
		Collections.reverse(teams);
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\n\n 4. Print the home ground names in alphabetical order.");
		teams.sort((Team t1, Team t2) -> t1.getHomeGround().name().compareTo(t2.getHomeGround().name()));
		teams.forEach(team -> System.out.println(team.getHomeGround()));
	}

}
