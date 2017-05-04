package quiz.runner.aniruddha;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
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
		System.out.println("---------------Quiz 1-------------------");


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
		teams.stream().map(Team::getHomeGround).sorted((t1, t2) -> t1.toString().compareTo(t2.toString()))
				.forEach(System.out::println);

		System.out.println("---------------Quiz 2-------------------");


		System.out.println(
			"\n---------------1.Create a map of Team name -> Home Ground and print the contents.-------------------");
		System.out.println(teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround)));

		System.out.println(
			"\n---------------2.Count number of matches Pune team played at a ground. Print the output in format “Ground name : Count");
		Map<Ground, List<Match>> s = teams.stream().filter(t -> QuizDataProvider.TEAM1.equals(t.getName()))
				.findFirst().get().getMatches().stream().collect(Collectors.groupingBy(m -> m.getVenue()));
		System.out.println("Venue:\t\tCount\n-----------------------------");
		s.entrySet().forEach(i -> {
			System.out.println(i.getKey() + ":\t" + i.getValue().size());
		});

		System.out.println("\n---------------3.Calculate total runs scored by Pune team.");
		System.out.println(teams.stream().filter(t -> QuizDataProvider.TEAM1.equals(t.getName())).findFirst()
				.get().getMatches().stream().mapToInt(m -> m.getRunsScored()).sum());

		System.out.println("\n---------------4.Did Pune team play a match at WANKHEDE?");
		Predicate<Match> m = e -> Ground.WANKHEDE.toString().equals(e.getVenue());

		System.out.println(teams.stream().filter(t -> QuizDataProvider.TEAM1.equals(t.getName())).findFirst()
				.get().getMatches().stream().anyMatch(m));

		System.out.println("\n---------------	5.How many matches did Pune team win?");

		System.out.println(teams.stream().filter(t -> QuizDataProvider.TEAM1.equals(t.getName())).findFirst()
				.get().getMatches().stream().filter(e -> e.getRunsScored() > e.getRunsConceded()).count());


	}
}
