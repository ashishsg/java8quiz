package quiz.runner.suryawanshi;

import java.util.List;
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
		System.out.println(teams);
		// Quiz1.main(args);
		quiz2(teams);
	}

	/**
	 * Method for Quiz 2
	 * @param teams
	 */
	public static void quiz2(List<Team> teams) {
		System.out.println("-------------- Quiz 2 Solution 1 ---------------------------");
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround)).entrySet()
				.forEach(System.out::println);
		;
		// System.out.println(map);

		System.out.println("-------------- Quiz 2 Solution 2 ---------------------------");
		// Usage of limit may be useful to improve performance as we know only one pune team is there/can user findfirst too
		System.out.println(teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName())).limit(1)
				.flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting())));

		System.out.println("-------------- Quiz 2 Solution 3 ---------------------------");
		System.out.println(teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName())).limit(1)
				.flatMap(team -> team.getMatches().stream()).mapToInt(Match::getRunsScored).sum()); // .collect(Collectors.summingInt(Match::getRunsScored)).toString());

		System.out.println("-------------- Quiz 2 Solution 4 ---------------------------");
		System.out.println("Did Pune team play a match at " + Ground.WANKHEDE.name() + " = "
				+ teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName())).limit(1)
						.flatMap(team -> team.getMatches().stream())
						.anyMatch(match -> Ground.WANKHEDE.equals(match.getVenue())));

		System.out.println("-------------- Quiz 2 Solution 5 ---------------------------");
		System.out.println("Matchs win By Pune team = "
				+ teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
						.flatMap(team -> team.getMatches().stream())
						.filter(match -> match.getRunsScored() > match.getRunsConceded()).count());

	}

}
