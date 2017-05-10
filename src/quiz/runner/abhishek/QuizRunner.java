package quiz.runner.abhishek;

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
import quiz.runner.abhishek.QuizRunner;
public class QuizRunner {

	private QuizRunner() {
	}

	public static void quizOne(List<Team> teams) {
		System.out.println("**********QUIZ 1**********");

		System.out.println("\nTeam Name - Home Ground Names");
		teams.forEach(team -> System.out.println(team.getName() + " - " + team.getHomeGround()));

		System.out.println("\nTeam Names Sorted in Natural Order: ");
		teams.sort((Team team1, Team team2) -> team1.getName().compareTo(team2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println("\nTeam Names Sorted in Reverse Order: ");
		teams.sort((Team team1, Team team2) -> team2.getName().compareTo(team1.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println("\nHome Ground Names in Alphabetical Order: ");
		List<Ground> homeGrounds = Arrays.asList(Ground.values());
		homeGrounds.sort((Ground ground1, Ground ground2) -> ground1.name().compareTo(ground2.name()));
		homeGrounds.stream().forEach(ground -> {
			System.out.println(ground.name());
		});
	}
	
	public static void quizTwo(List<Team> teams) {
		System.out.println("**********QUIZ 2**********");
		System.out.println("\nCreating map and printing the Team name and Home ground: ");
		teams.stream().collect(Collectors.toMap(team -> team.getName(), team -> team.getHomeGround()))
				.forEach((k, v) -> System.out.println(k + ":" + v));

		System.out.println("\nNumber of matches of Pune team, Ground Name : count");
		Map<Ground, Long> venueMatchesMap = teams.stream()
				.filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()));
		venueMatchesMap.forEach((k, v) -> System.out.println(k + ":" + v));

		System.out.println("\nTotal runs scored by Pune team: ");
		Integer runsScored = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream()).collect(Collectors.summingInt(Match::getRunsScored));
		System.out.println(runsScored);

		System.out.println("\nDid Pune team play a match at WANKHEDE: ");
		boolean hasPunePlayedAtWankhede = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.anyMatch(match -> (Ground.WANKHEDE).equals(match.getVenue()));
		System.out.println(hasPunePlayedAtWankhede);

		System.out.println("\nNumber of matches win by Pune team: ");
		long numberOfMatchesWon = teams.stream().filter(team -> (QuizDataProvider.TEAM1).equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.filter(match -> (match.getRunsScored() - match.getRunsConceded() > 0)).count();
		System.out.println(numberOfMatchesWon);
	}
	
	public static void quizThree(List<Team> teams) {
		System.out.println("**********QUIZ 3**********");
		
		System.out.println("\nDetails of matches played on 4th April 2017: ");
		teams.stream().flatMap(team -> team.getMatches().stream()).distinct()
				.filter(match -> match.getMatchDate().equals(LocalDate.of(2017, 4, 4))).collect(Collectors.toList())
				.forEach(System.out::println);
		
		System.out.println("\nDuration(number of days) of this tournament: ");
		Optional<LocalDate> tournamentStartDate = teams.stream().flatMap(team -> team.getMatches().stream())
				.map(Match::getMatchDate).min(LocalDate::compareTo);

		Optional<LocalDate> tournamentEndDate = teams.stream().flatMap(team -> team.getMatches().stream())
				.map(Match::getMatchDate).max(LocalDate::compareTo);

		if (tournamentStartDate.isPresent() && tournamentEndDate.isPresent()) {

			System.out.println(Period.between(tournamentStartDate.get(), tournamentEndDate.get()).getDays() + " Days");
		}

		System.out.println("\nDetails of all the matches to each team: ");
		teams.stream().flatMap(team -> team.getMatches().stream()).collect(Collectors.groupingBy(Match::getTeam1Name))
				.forEach((k, v) -> System.out.println(k + " : " + v));
		
		System.out.println("\nMaximum number of balls team 2 can take to score 110 runs to have NRR better than team 1: ");
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getMatches)).forEach((k,v) -> System.out.println(" Team: "+ k +"\n Match: "+ v));
		BigDecimal runsConceded = new BigDecimal(390.0).setScale(3, RoundingMode.CEILING);
		BigDecimal ballsBowled = new BigDecimal(360.0).setScale(3, RoundingMode.CEILING);
		BigDecimal runScored = new BigDecimal(360.0).setScale(3, RoundingMode.CEILING);
		System.out.println("\n Maximum number of balls team 2 can take to score 110 runs to have NRR better than team 1 :"+IntStream.range(1, 120).filter(ballsFaced -> BigDecimal.valueOf(0.109).compareTo(runScored.divide(new BigDecimal(240 + ballsFaced).setScale(3, RoundingMode.CEILING), 3, RoundingMode.CEILING).subtract(runsConceded.divide(ballsBowled, 3, RoundingMode.CEILING))) < 0).max().getAsInt());
	}
	
	public static void quizFour(List<Team> teams) {
		System.out.println("**********QUIZ 4**********");
		
		System.out.println("\nDates when match(s) were played at each venue: ");
		teams.stream().flatMap(team -> team.getMatches().stream()).distinct().collect(
				Collectors.groupingBy(Match::getVenue, Collectors.mapping(Match::getMatchDate, Collectors.toList())))
				.forEach((k, v) -> {
					System.out.println(k + " : " + v);
				});

		System.out.println("\nMatch in which maximum number of runs were scored at each venue: ");
		teams.stream().flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.summarizingInt(Match::getRunsScored)))
				.forEach((k, v) -> {
					System.out.println(k + " : " + v.getMax());
				});

		System.out.println("\nTotal runs scored at a venue: ");
		teams.stream().flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.summingInt(Match::getRunsScored)))
				.forEach((k, v) -> {
					System.out.println(k + " : " + v);
				});

		System.out.println("\nDay and Night match: ");
		teams.stream().flatMap(team -> team.getMatches().stream()).distinct().collect(
				Collectors.partitioningBy(Match::isDay, Collectors.mapping(Match::getMatchNumber, Collectors.toList())))
				.forEach((k, v) -> {
					System.out.println(k + " : " + v);
				});
	}
	
	public static void main(String[] args) {
		List<Team> teams = QuizDataProvider.getTeams();

		System.out.println("\nTeam Names: ");
		System.out.println(teams);
		
		QuizRunner.quizOne(teams);
		System.out.println("\n");
		
		QuizRunner.quizTwo(teams);
		System.out.println("\n");
		
		QuizRunner.quizThree(teams);
		System.out.println("\n");
		
		QuizRunner.quizFour(teams);
	}
}
