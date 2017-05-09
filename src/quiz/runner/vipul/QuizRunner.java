package quiz.runner.vipul;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;

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

		// java8Quiz1(teams);
		// java8Quiz2(teams);
		// jav8Quiz3(teams);
		jav8Quiz4(teams);
	}

	private static void java8Quiz1(List<Team> teams) {
		System.out.println(
				"*********************************************  Quiz 1 ********************************************* \n\n");
		System.out.println(
				"\n\n 1. Print all the team name and home ground. Sample output – Rising Super Giants : GAHUNJE.");
		teams.forEach(team -> System.out.println(team.getName() + " : " + team.getHomeGround()));

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

	private static void java8Quiz2(List<Team> teams) {
		System.out.println(
				"*********************************************  Quiz 2 ********************************************* \n\n");
		System.out.println("\n\n 1.	Create a map of Team name -> Home Ground and print the contents.");
		Map<String, Ground> teamsMap = teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround));
		teamsMap.forEach((t, g) -> System.out.println("Team : " + t + " , Ground : " + g));

		// Get the Pune team from the list of Teams
		List<Team> puneTeams = teams.stream().filter(team -> team.getName().equals("Rising Super Gients"))
				.collect(Collectors.toList());

		if (puneTeams.size() != 0) {
			Team puneTeam = puneTeams.get(0);
			System.out.println(
					"\n\n 2. Count number of matches Pune team played at a ground. Print the output in format “Ground name : Count”.");
			Map<Ground, Long> matchCountOnGround = puneTeam.getMatches().stream()
					.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()));
			matchCountOnGround.forEach((g, c) -> System.out.println("Ground : " + g + " , Count : " + c));

			System.out.println("\n\n  3. Calculate total runs scored by Pune team.");
			System.out.println("Total Run Scored By Pune Team : "
					+ puneTeam.getMatches().stream().collect(Collectors.summingInt(Match::getRunsScored)));

			System.out.println("\n\n  4. Did Pune team play a match at WANKHEDE?");
			System.out.println(" Did Pune team play a match at WANKHEDE? : " + (puneTeam.getMatches().stream()
					.anyMatch(match -> Ground.WANKHEDE.equals(match.getVenue())) == true ? "Yes" : "No"));

			System.out.println("\n\n 5.	How many matches did Pune team win?");
			System.out.println(" Number of Matches Pune wone are : " + (puneTeam.getMatches().stream()
					.filter(match -> match.getRunsScored() > match.getRunsConceded()).count()));
		}
	}

	private static void jav8Quiz3(List<Team> teams) {
		System.out.println(
				"*********************************************  Quiz 3 ********************************************* \n\n");
		LocalDate localDate = LocalDate.of(2017, Month.APRIL, 4);
		System.out.println("Specific Date=" + localDate);
		Map<String, List<Match>> teamsMatchMap = teams.stream()
				.collect(Collectors.toMap(Team::getName, Team::getMatches));
		teamsMatchMap.forEach((team, matches) -> {
			List<Match> matchList = matches.stream().filter(match -> localDate.equals(match.getMatchDate()))
					.collect(Collectors.toList());
			if (matchList.size() != 0) {
				matchList.forEach(match -> System.out.println("Opponenet " + match));
			}
		});
	}

	private static void jav8Quiz4(List<Team> teams) {
		System.out.println(
				"*********************************************  Quiz 4 ********************************************* \n\n");

		System.out.println(
				"\n\n 1. Find out the dates when match(s) were played at each venue. Sample output : EDENGARDENS=[2017-04-04]");

		Map<String, List<Match>> teamsMatchMap = teams.stream()
				.collect(Collectors.toMap(Team::getName, Team::getMatches));
		Set<String> matcheSet = new HashSet<>();
		teamsMatchMap.forEach((team, matches) -> {
			matches.forEach(match -> matcheSet.add(match.getVenue() + " = [" + match.getMatchDate() + "]"));
		});

		matcheSet.forEach(s -> System.out.println(s));

		System.out.println("\n\n 2. Find the match in which maximum number of runs were scored at each venue");
		Set<String> matcheRunScoreSet = new HashSet<>();
		teamsMatchMap.forEach((team, matches) -> {
			matches.forEach(match -> matcheRunScoreSet
					.add((match.getVenue() + " = [" + ((match.getRunsScored() > match.getRunsConceded()
							? match.getRunsScored() : match.getRunsConceded())) + "]")));
		});

		matcheRunScoreSet.forEach(s -> System.out.println(s));

		System.out.println("\n\n 3. Find total runs scored at a venue");
		Stream<Match> matches = teams.stream().flatMap(team -> team.getMatches().stream());

		Map<Ground, Integer> sum = matches
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.summingInt(Match::getRunsScored)));
		sum.forEach((t, g) -> System.out.println("Venue : " + t + " , Total Run scored : " + g));

		System.out.println(
				"\n\n 4.	Separate out the match numbers as day match or night match. (Hint: final result structure Map<Boolean, List<Integer>> dayOrNightToMatchNumbers)");
		Map<Boolean, Set<Integer>> dayNightMatch = teams.stream().flatMap(team -> team.getMatches().stream()).collect(Collectors.partitioningBy(Match::isDay, Collectors.mapping(Match::getMatchNumber, Collectors.toSet())));
		dayNightMatch.forEach((isDay, matchNumber) -> System.out.println(isDay + " = " + matchNumber));
	}
}
