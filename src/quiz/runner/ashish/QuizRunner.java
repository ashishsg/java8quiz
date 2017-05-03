package quiz.runner.ashish;

import java.util.Comparator;
import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();


		// Quiz 1: Print all team names and home ground
		// e.g "Rising Super Gients : GAHUNJE"
		System.out.println("-----Quiz 1-----");
		teams.stream().forEach(t -> System.out.println(t.getName() + " : " + t.getHomeGround()));
		// Quiz 2: Print the team names in natural sorted order.
		System.out.println("-----Quiz 2-----");
		teams.stream().map(Team::getName).sorted().forEach(System.out::println);
		// Quiz 3: Print the team names in reverse sorted order.
		System.out.println("-----Quiz 3-----");
		teams.stream().map(Team::getName).sorted(Comparator.reverseOrder()).forEach(System.out::println);
		// Quiz 4: Print the home ground names in alphabetical order
		System.out.println("-----Quiz 4-----");
		teams.stream().map(Team::getHomeGround).sorted((g1, g2) -> g1.toString().compareTo(g2.toString()))
				.forEach(System.out::println);
		// or
		teams.stream().map(team -> team.getHomeGround().name()).sorted().forEach(System.out::println);
	}

}
