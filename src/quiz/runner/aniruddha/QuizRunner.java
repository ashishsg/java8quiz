package quiz.runner.aniruddha;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import quiz.model.QuizDataProvider;
import quiz.model.Team;


public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		System.out.println("---------------1.	Print all the team name and home ground-------------------");
		teams.stream().forEach(t -> {
			System.out.println(t.getName() + " :" + t.getHomeGround());
		});

		System.out.println("---------------2.	Print the team names in natural sorted order.-------------------");
		teams.stream().map(Team::getName).sorted().forEach(System.out::println);


		System.out.println("---------------3.	Print the team names in reverse sorted order.-------------------");
		teams.stream().map(Team::getName).sorted(Comparator.reverseOrder()).forEach(System.out::println);


		System.out
				.println("---------------4.	Print the home ground names in alphabetical order-------------------");
		teams.stream().map(Team::getHomeGround).sorted((t1,t2)->t1.toString().compareTo(t2.toString())).forEach(System.out::println);



	}

}
