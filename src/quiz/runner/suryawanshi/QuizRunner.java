package quiz.runner.suryawanshi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sun.org.apache.xerces.internal.impl.dv.xs.BooleanDV;

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
		// quiz2(teams);
		quiz3(teams);
	}

	/**
	 * Method for Quiz 2
	 * 
	 * @param teams
	 */
	public static void quiz2(List<Team> teams) {
		System.out.println("-------------- Quiz 2 Solution 1 ---------------------------");
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround)).entrySet()
				.forEach(System.out::println);
		// System.out.println(map);

		System.out.println("-------------- Quiz 2 Solution 2 ---------------------------");
		// Usage of limit may be useful to improve performance as we know only
		// one pune team is there/can user findfirst too
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

	/**
	 * Method for Quiz 2
	 * 
	 * @param teams
	 */
	public static void quiz3(List<Team> teams) {
		System.out.println("-------------- Quiz 3 Solution 1 A ---------------------------");
		teams.stream().flatMap(team -> team.getMatches().stream())
				.filter(match -> match.getMatchDate().compareTo(LocalDate.of(2017, 4, 4)) == 0).distinct()
				.forEach(System.out::println);
		System.out.println("-------------- Quiz 3 Solution 1 B ---------------------------");
		teams.stream().flatMap(team -> team.getMatches().stream())
				.filter(match -> match.getMatchDate().equals(LocalDate.of(2017, 4, 4))).forEach(System.out::println);

		System.out.println("-------------- Quiz 3 Solution 2  ---------------------------");
		Optional<Match> minItem = teams.stream().flatMap(team -> team.getMatches().stream())
				.min((match1, match2) -> match1.getMatchDate().compareTo(match2.getMatchDate()));
		Optional<Match> maxItem = teams.stream().flatMap(team -> team.getMatches().stream())
				.max((match1, match2) -> match1.getMatchDate().compareTo(match2.getMatchDate()));
		System.out.println(ChronoUnit.DAYS.between(minItem.get().getMatchDate(), maxItem.get().getMatchDate()));
		System.out.println(
				Duration.between(minItem.get().getMatchDate().atTime(0, 0), maxItem.get().getMatchDate().atTime(0, 0))
						.toDays());

		System.out.println("-------------- Quiz 3 Solution 3  ---------------------------");
		teams.stream().flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(match -> match.getTeam1Name())).entrySet().forEach(System.out::println);

		System.out.println("-------------- Quiz 3 Solution 4  ---------------------------");
		teams.stream().flatMap(team -> team.getMatches().stream()).forEach(System.out::println);

		System.out.println("-------------- Quiz 3 Solution 4 ---------------------------");
		BigDecimal team2RunScored = BigDecimal.valueOf(360);
		BigDecimal team2ballFaced = BigDecimal.valueOf(240);
		BigDecimal team2RunConceded = BigDecimal.valueOf(390).divide(BigDecimal.valueOf(360), 3, RoundingMode.CEILING);

		IntStream.rangeClosed(1, 120)
				.filter(noOfBallreq -> BigDecimal.valueOf(0.109)
						.compareTo((team2RunScored.divide(BigDecimal.valueOf(team2ballFaced.intValue() + noOfBallreq),
								3, RoundingMode.CEILING)).subtract(team2RunConceded)) < 0)
				.max().ifPresent(System.out::print);
	}

}
