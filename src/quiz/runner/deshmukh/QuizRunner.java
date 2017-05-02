package quiz.runner.deshmukh;

import java.util.List;
import java.util.stream.Collectors;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {
	}

	public static void main(String[] args) {
		System.out.println("\n1. Print all the team name and home ground. Sample output – Rising Super Giants : GAHUNJE");
		List<Team> teams = QuizDataProvider.getTeams();
		teams.stream().forEach(team -> System.out.println(team.getName() + " : " + team.getHomeGround()));
		
		System.out.println("\n2. Print the team names in natural sorted order");
		teams.stream().sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
				.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\n3. Print the team names in reverse sorted order");
		teams.stream().sorted((t1, t2) -> t2.getName().compareTo(t1.getName()))
				.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\n4. Print the home ground names in alphabetical order");
		teams.stream().sorted((t1, t2) -> t1.getHomeGround().toString().compareTo(t2.getHomeGround().toString()))
				.forEach(team -> System.out.println(team.getHomeGround()));
	}

}
