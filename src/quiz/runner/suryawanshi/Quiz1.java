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
		System.out.println(
				teams.stream().map(team -> (team.getName() + ":" + team.getHomeGround())).collect(Collectors.toList()));
		System.out.println("-------------- Solution 1 another approch ---------------------------");
		System.out.println(teams.stream().map(team -> team.getName() + ":" + team.getHomeGround())
				.collect(Collectors.joining(",")));
	}

	public static void printTeamSorting(List<Team> teams) {
		System.out.println("-------------- Solution 2 ---------------------------");
		System.out.println(teams.stream().map(Team::getName).sorted().reduce("", String::concat));
	}

	public static void printTeamReverSorting(List<Team> teams) {
		System.out.println("-------------- Solution 3 ---------------------------");
		System.out.println(teams.stream().map(Team::getName).sorted(java.util.Collections.reverseOrder()).reduce("",
				String::concat));
	}

	public static void printTeamHomeGroundSorting(List<Team> teams) {
		System.out.println("-------------- Solution 4 ---------------------------");
		System.out.println(
				teams.stream().map(team -> team.getHomeGround().name()).sorted().collect(Collectors.joining(",")));
		System.out.println("-------------- Solution 4 Reverse Order  ---------------------------");
		System.out.println(teams.stream().map(team -> team.getHomeGround().name())
				.sorted(java.util.Collections.reverseOrder()).collect(Collectors.joining(",")));
	}

}
