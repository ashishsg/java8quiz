package quiz.runner.shehnaz;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
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
		
		System.out.println("1. Create a map of Team name -> Home Ground and print the contents.");
		teams
		.stream().collect(Collectors.toMap(Team :: getName, Team :: getHomeGround))
		.forEach((key, value) -> System.out.println(key + " -> " + value));
		
		System.out.println("\n\n2.Count number of matches Pune team played at a ground. Print the output in format “Ground name : Count”. (hint: use team.getMatches())");
		teams
		.stream()
		.filter(team -> team.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(team -> team.getMatches().stream())
		.collect(Collectors.groupingBy(Match :: getVenue, Collectors.counting()))
		.forEach((key, value) -> System.out.println(key + " -> " + value));
		
		System.out.println("\n\n3.Calculate total runs scored by Pune team.");
		System.out.println(teams
		.stream()
		.filter(team -> team.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(team -> team.getMatches().stream())
		.mapToInt(Match :: getRunsScored)
		.sum());
		
		System.out.println("\n\n4.Did Pune team play a match at WANKHEDE?");
		System.out.println(teams.stream()
		.filter(team -> team.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(team -> team.getMatches().stream())
		.anyMatch(teamMatch -> teamMatch.getVenue().equals(Ground.WANKHEDE)) ? "Yes" : "No");
		
		System.out.println("\n\n5.How many matches did Pune team win?");
		System.out.println(teams.stream()
		.filter(team -> team.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(team -> team.getMatches().stream())
		.filter(teamMatch -> (teamMatch.getRunsScored() - teamMatch.getRunsConceded() > 0))
		.count());
	}

}
