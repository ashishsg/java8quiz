package quiz.runner.purnima;


import java.util.Comparator;
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

		//Quiz1
		System.out.println("-------Quiz1 ------");
		System.out.println("\n1.Print all the team name and home ground.");
		System.out.println("-----------------------------------------------");
		teams.forEach(team -> System.out.println(team.getName() + " : " + team.getHomeGround()));
		
		System.out.println("\n2.Print the team names in natural sorted order.");
		System.out.println("-----------------------------------------------");
		Comparator<Team> teamNameComparator = (team1, team2) -> team1.getName().compareTo(team2.getName());
		teams.sort(teamNameComparator);
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\n3.Print the team names in reverse sorted order.");
		System.out.println("-----------------------------------------------");
		teams.sort(teamNameComparator.reversed());
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\n4.Print the home ground names in alphabetical order.");
		System.out.println("-----------------------------------------------");
		teams.sort((team1, team2) -> team1.getHomeGround().toString().compareTo(team2.getHomeGround().toString()));
		teams.forEach(team -> System.out.println(team.getHomeGround()));
		

		//QUIZ 2
		System.out.println("\n-------Quiz2 ------");
		System.out.println("1.Create a map of Team name -> Home Ground and print the contents.");
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround))
				.forEach((teamName, homeGround) -> System.out.println(teamName + " : " + homeGround));

		Team teamPune = teams.stream().filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
				.collect(Collectors.toList()).get(0);

		System.out.println(
			"\n2.Count number of matches Pune team played at a ground. Print the output in format “Ground name : Count”");
		teamPune.getMatches().stream().collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()))
				.forEach((ground, count) -> System.out.println(ground + " : " + count));

		int runScored = teamPune.getMatches().stream().mapToInt(Match::getRunsScored).sum();
		System.out.println("\n3.Calculate total runs scored by Pune team. : " + runScored);

		boolean result1 = teamPune.getMatches().stream()
				.anyMatch(match -> Ground.WANKHEDE.equals(match.getVenue()));
		System.out.println("\n4.Did Pune team play a match at WANKHEDE?  " + result1);

		System.out.println("\n5.How many matches did Pune team win? " + teamPune.getMatches().stream()
				.filter(teamMatch -> (teamMatch.getRunsScored() - teamMatch.getRunsConceded() > 0)).count());
	}
}
