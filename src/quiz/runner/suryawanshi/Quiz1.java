package quiz.runner.suryawanshi;

import java.util.List;
import java.util.stream.Collectors;

import quiz.model.QuizDataProvider;
import quiz.model.Team;

public class Quiz1 {

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		System.out.println("----------- Quiz 1 ---------");
		printTeamAndGround(teams);
		printTeamSorting(teams);
		printTeamReverSorting(teams);
		printTeamHomeGroundSorting(teams);
		System.out.println("----------- Quiz 1 End ---------");
	}

	public static void printTeamAndGround(List<Team> teams) {
		System.out.println("-------------- Solution 1 ---------------------------");
		teams.stream().map(team -> (team.getName() + ":" + team.getHomeGround())).forEach(System.out::print);
		System.out.println("-------------- Solution 1 another approch ---------------------------");
		System.out.println(teams.stream().map(team -> team.getName() + ":" + team.getHomeGround())
				.collect(Collectors.joining(",")));
	}

	public static void printTeamSorting(List<Team> teams) {
		System.out.println("-------------- Solution 2 ---------------------------");
		teams.stream().map(Team::getName).sorted().forEach(System.out::println);
	}

	public static void printTeamReverSorting(List<Team> teams) {
		System.out.println("-------------- Solution 3 ---------------------------");
		teams.stream().map(Team::getName).sorted(java.util.Collections.reverseOrder()).forEach(System.out::println);
	}

	public static void printTeamHomeGroundSorting(List<Team> teams) {
		System.out.println("-------------- Solution 4 ---------------------------");
		teams.stream().map(team -> team.getHomeGround().name()).sorted().forEach(System.out::println);
		System.out.println("-------------- Solution 4 Reverse Order  ---------------------------");
		teams.stream().map(team -> team.getHomeGround().name())
				.sorted(java.util.Collections.reverseOrder()).forEach(System.out::println);
	}

}
