package quiz.runner.abhishek;

import java.util.Arrays;
import java.util.List;

import quiz.model.Ground;
import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {
	}

	public static void main(String[] args) {
		List<Team> teams = QuizDataProvider.getTeams();
		System.out.println("\nTeam Names: ");
		System.out.println(teams);
		
		System.out.println("\nTeam Name - Home Ground Names");
		teams.forEach(team -> System.out.println(team.getName() + " - " + team.getHomeGround()));
		
		System.out.println("\nTeam Names Sorted in Natural Order: ");
		teams.sort((Team team1, Team team2)->team1.getName().compareTo(team2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\nTeam Names Sorted in Reverse Order: ");
		teams.sort((Team team1, Team team2)->team2.getName().compareTo(team1.getName()));
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\nHome Ground Names in Alphabetical Order: ");
		List<Ground> homeGrounds = Arrays.asList(Ground.values());
		homeGrounds.sort((Ground ground1, Ground ground2)->ground1.name().compareTo(ground2.name()));
		homeGrounds.stream().forEach(ground -> {
			System.out.println(ground.name());
		});
	}
}
