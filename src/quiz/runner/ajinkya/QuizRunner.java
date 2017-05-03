package quiz.runner.ajinkya;

import quiz.model.Ground;
import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		//quiz1(teams);
		quiz2(teams);
	}

	private static void quiz1(List<Team> teams) {
		// 1
		teams.stream().map(team -> {
			return new StringBuilder(team.getName()).append(": ").append(team.getHomeGround()).toString();
		}).forEach(System.out::println);

		// 2
		teams.stream().map(team -> {
			return team.getName();
		}).sorted().forEach(System.out::println);
		
		// 3
		teams.stream().map(team -> {
			return team.getName();
		}).sorted(Collections.reverseOrder()).forEach(System.out::println);
		
		// 4
		teams.stream().map(team -> {
			return team.getHomeGround().name();
		}).sorted().forEach(System.out::println);
	}
	
	private static void quiz2(List<Team> teams) {
		// 1
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround)).entrySet().forEach(System.out::println);

		// 2
		teams.stream()
				.filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()))
				.entrySet().stream()
				.forEach(System.out::println);

		// 3
		System.out.println(teams.stream()
				.flatMap(team -> team.getMatches().stream())
				.filter(match -> QuizDataProvider.TEAM1.equals(match.getOpponent()))
				.mapToInt(Match::getRunsScored)
				.sum());

		// 4
		boolean playedAtVenue = teams.stream()
				.filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.anyMatch(match -> Ground.WANKHEDE == match.getVenue());
		System.out.println(playedAtVenue ? "YES": "NO");

		// 5
		System.out.println(teams.stream()
				.filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
				.flatMap(team -> team.getMatches().stream())
				.filter(match -> match.getRunsScored() > match.getRunsConceded())
				.count());
	}
}