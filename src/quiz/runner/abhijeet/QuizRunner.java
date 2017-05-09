package quiz.runner.abhijeet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		
		QuizRunner.quizOne(teams);
		System.out.println("\n\n\n");
		
		QuizRunner.quizTwo(teams);
		System.out.println("\n\n\n");
		
		QuizRunner.quizThree(teams);
		System.out.println("\n\n\n");
		
		QuizRunner.quizFour(teams);
		
	}
	
	public static void quizOne(List<Team> teams) {
		
		System.out.println("********** 1st Question ***********");
		
		// printing all the names and home grounds
		teams.forEach(team -> System.out.println(team.getName() + ":" + team.getHomeGround()));
		
		System.out.println("********** 2nd Question ***********");
		
		// printing team names sorted in natural order
		teams.sort((Team team1, Team team2) ->team1.getName().compareTo(team2.getName()));
		
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("********** 3rd Question ***********");
		
		// printing team names sorted in reverse order
		teams.sort((Team team1, Team team2)->team2.getName().compareTo(team1.getName()));
		
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("********** 4th Question ***********");
		
		// Printing all home grounds in alphabetical order.
		List<Ground> homeGrounds = Arrays.asList(Ground.values());
		
		homeGrounds.sort((Ground ground1, Ground ground2)->ground1.name().compareTo(ground2.name()));
		homeGrounds.stream().forEach(ground -> {
			System.out.println(ground.name());
		});
	}
	
	public static void quizTwo(List<Team> teams) {

		// Quiz 2 Solutions
		System.out.println("----------------------- Quiz 2 Solutions ---------------------------");
		System.out.println("********** 1st Question ***********");
		// 1 creating the map and printing the name of the team and it's ome
		// ground
		teams.stream().collect(Collectors.toMap(team -> team.getName(), team -> team.getHomeGround()))
				.forEach((k, v) -> System.out.println(k + ":" + v));

		System.out.println("********** 2nd Question ***********");
		// 2
		Map<Ground, Long> venueMatchesMap = teams.stream()
				.filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()));
		venueMatchesMap.forEach((k, v) -> System.out.println(k + ":" + v));

		System.out.println("********** 3rd Question ***********");
		// 3
		Integer runsScored = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream()).collect(Collectors.summingInt(Match::getRunsScored));
		System.out.println(runsScored);

		// 4

		System.out.println("********** 4th Question ***********");

		boolean hasPunePlayedAtWankhede = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.anyMatch(match -> (Ground.WANKHEDE).equals(match.getVenue()));
		System.out.println(hasPunePlayedAtWankhede);

		System.out.println("********** 5th Question ***********");

		// 5
		long numberOfMatchesWon = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.filter(match -> (match.getRunsScored() - match.getRunsConceded() > 0)).count();

		System.out.println(numberOfMatchesWon);
	}
	
	public static void quizThree(List<Team> teams) {

		// Quiz 3 Solutions
		System.out.println("----------------------- Quiz 3 Solutions ---------------------------");
		System.out.println("********** 1st Question ***********");

		teams.stream().flatMap(team -> team.getMatches().stream()).distinct()
				.filter(match -> match.getMatchDate().equals(LocalDate.of(2017, 4, 4))).collect(Collectors.toList())
				.forEach(System.out::println);

		System.out.println("********** 2nd Question ***********");

		Optional<LocalDate> tournamentStartDate = teams.stream().flatMap(team -> team.getMatches().stream())
				.map(Match::getMatchDate).min(LocalDate::compareTo);

		Optional<LocalDate> tournamentEndDate = teams.stream().flatMap(team -> team.getMatches().stream())
				.map(Match::getMatchDate).max(LocalDate::compareTo);

		if (tournamentStartDate.isPresent() && tournamentEndDate.isPresent()) {

			System.out.println(Period.between(tournamentStartDate.get(), tournamentEndDate.get()).getDays() + " Days");
		}

		System.out.println("********** 3rd Question ***********");

		teams.stream().flatMap(team -> team.getMatches().stream()).collect(Collectors.groupingBy(Match::getTeam1Name))
				.forEach((k, v) -> System.out.println(k + ":" + v));
		
		System.out.println("********** 4th Question ***********");
		
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getMatches)).forEach((k,v) -> System.out.println(" Team : "+ k +"\n Match : "+ v));
		BigDecimal runsConceded = new BigDecimal(390.0).setScale(3, RoundingMode.CEILING);
		BigDecimal ballsBowled = new BigDecimal(360.0).setScale(3, RoundingMode.CEILING);
		BigDecimal runScored = new BigDecimal(360.0).setScale(3, RoundingMode.CEILING);
		System.out.println("\n Maximum number of balls team 2 can take to score 110 runs to have NRR better than team 1 :"+IntStream.range(1, 120).filter(ballsFaced -> BigDecimal.valueOf(0.109).compareTo(runScored.divide(new BigDecimal(240 + ballsFaced).setScale(3, RoundingMode.CEILING), 3, RoundingMode.CEILING).subtract(runsConceded.divide(ballsBowled, 3, RoundingMode.CEILING))) < 0).max().getAsInt());
		
	}
	
	public static void quizFour(List<Team> teams) {

		// Quiz 4 Solutions
		System.out.println("----------------------- Quiz 4 Solutions ---------------------------");
		System.out.println("********** 1st Question ***********");

		teams.stream().flatMap(team -> team.getMatches().stream()).distinct().collect(
				Collectors.groupingBy(Match::getVenue, Collectors.mapping(Match::getMatchDate, Collectors.toList())))
				.forEach((k, v) -> {
					System.out.println(k + "= " + v);
				});

		System.out.println("********** 2nd Question ***********");

		teams.stream().flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.summarizingInt(Match::getRunsScored)))
				.forEach((k, v) -> {
					System.out.println(k + " = " + v.getMax());
				});

		System.out.println("********** 3rd Question ***********");

		teams.stream().flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.summingInt(Match::getRunsScored)))
				.forEach((k, v) -> {
					System.out.println(k + " = " + v);
				});

		System.out.println("********** 4th Question ***********");

		teams.stream().flatMap(team -> team.getMatches().stream()).distinct().collect(
				Collectors.partitioningBy(Match::isDay, Collectors.mapping(Match::getMatchNumber, Collectors.toList())))
				.forEach((k, v) -> {
					System.out.println(k + " = " + v);
				});
	}

}
