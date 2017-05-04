package quiz.runner.vipul;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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

		java8Quiz1(teams);
		java8Quiz2(teams);
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

}
