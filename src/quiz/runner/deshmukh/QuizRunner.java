package quiz.runner.deshmukh;

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
		//quiz1(teams);
		quiz2(teams);
	}
	
	private static void quiz1(List<Team> teams) {
		System.out.println("\n1. Print all the team name and home ground. Sample output – Rising Super Giants : GAHUNJE");
		teams.stream().forEach(team -> System.out.println(team.getName() + " : " + team.getHomeGround()));

		System.out.println("\n2. Print the team names in natural sorted order");
		teams.stream().sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
				.forEach(team -> System.out.println(team.getName()));

		System.out.println("\n3. Print the team names in reverse sorted order");
		teams.stream().sorted((t1, t2) -> t2.getName().compareTo(t1.getName()))
				.forEach(team -> System.out.println(team.getName()));

		System.out.println("\n4. Print the home ground names in alphabetical order");
		teams.stream().sorted((t1, t2) -> t1.getHomeGround().toString().compareTo(t2.getHomeGround().toString()))
				.forEach(team -> System.out.println(team.getHomeGround()));
	}
	
	private static void quiz2(List<Team> teams) {
		
		System.out.println("\n1. Create a map of Team name -> Home Ground and print the contents.");
		Map<String, Ground> teamHomeGroundMap = teams.stream()
				.collect(Collectors.toMap(Team::getName, Team::getHomeGround));
		teamHomeGroundMap.forEach((k, v) -> System.out.println("Team : " + k + "; Home Ground : " + v));
		
		System.out.println("\n2. Count number of matches Pune team played at a ground. Print the output in format “Ground name : Count”");
		teams.get(0).getMatches().stream().collect(Collectors.groupingBy(Match::getVenue))
				.forEach((k, v) -> System.out.println("Ground name : " + k + " Count : " + v.size()));
		
		System.out.println("\n3. Calculate total runs scored by Pune team.");
		//System.out.println(teams.get(0).getMatches().stream().collect(Collectors.summingInt(Match::getRunsScored)));
		System.out.println(teams.get(0).getMatches().stream().mapToInt(Match::getRunsScored).sum());
		
		System.out.println("\n4. Did Pune team play a match at WANKHEDE?");
		System.out.println(teams.get(0).getMatches().stream().anyMatch(match -> match.getVenue().equals(Ground.WANKHEDE)));
		
		System.out.println("\n5. How many matches did Pune team win?");
		System.out.println(teams.get(0).getMatches().stream()
				.filter(match -> match.getRunsScored() > match.getRunsConceded()).count());
	}

}
