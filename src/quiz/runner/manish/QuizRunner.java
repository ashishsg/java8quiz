package quiz.runner.manish;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import quiz.model.Ground;
import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		System.out.println("******************* Print Team Name and Home Ground **********************");
		teams.forEach(team -> System.out.println(team.getName() + " : " + team.getHomeGround()));

		System.out.println("\n***************** Print Team in Natural Sorted Order ************************* ");
		Collections.sort(teams, (t1, t2) -> t1.getName().compareTo(t2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));
		System.out.println("\n***************** Print Team in Reverse Sorted Order ************************* ");
		Collections.sort(teams, (t1, t2) -> t2.getName().compareTo(t1.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println(
			"\n**************** Print the home ground names in alphabetical order ************************* ");
		List<Ground> homeGroundList = Arrays.asList(Ground.values());
		Collections.sort(homeGroundList, (g1, g2) -> g1.toString().compareTo(g2.toString()));
		System.out.println(homeGroundList);

		System.out.println("\n1. Map of Team : ");
		Map<String, Ground> teamMap = teams.stream()
				.collect(Collectors.toMap(Team::getName, Team::getHomeGround));
		teamMap.forEach((k, v) -> System.out.println(k + " : " + v));

		System.out.println("\n2. Number of matches Pune team played at a ground ");
		teams.stream().filter(t -> t.getName().equals(QuizDataProvider.TEAM1))
				.flatMap(t -> t.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()))
				.forEach((venue, count) -> System.out.println(venue + " : " + count));

		System.out
				.println("\n3. Pune Scored : " + teams.stream().filter(t -> t.getName().equals(QuizDataProvider.TEAM1))
						.flatMap(t -> t.getMatches().stream()).map(Match::getRunsScored).reduce(0, (a, b) -> a + b));

		System.out.println("\n4. Pune played @ Wankhede : "+ teams.stream().filter(t -> t.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(t -> t.getMatches().stream()).map(Match::getVenue).anyMatch( g -> g.equals(Ground.WANKHEDE)));
		
		System.out.println("\n5. Pune won : "+teams.stream().filter(t -> t.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(t -> t.getMatches().stream()).filter(m -> m.getRunsScored() > m.getRunsConceded()).count());
		
	}
}
