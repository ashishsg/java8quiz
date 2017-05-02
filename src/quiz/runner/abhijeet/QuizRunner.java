package quiz.runner.abhijeet;

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
		System.out.println(teams);
		
		System.out.println("********************************");
		
		// printing all the names and home grounds
		teams.forEach(team -> System.out.println(team.getName() + ":" + team.getHomeGround()));
		
		System.out.println("********************************");
		
		// printing team names sorted in natural order
		teams.sort((Team team1, Team team2) ->team1.getName().compareTo(team2.getName()));
		
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("********************************");
		
		// printing team names sorted in reverse order
		teams.sort((Team team1, Team team2)->team2.getName().compareTo(team1.getName()));
		
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("********************************");
		
		// Printing all home grounds in alphabetical order.
		List<Ground> homeGrounds = Arrays.asList(Ground.values());
		
		homeGrounds.sort((Ground ground1, Ground ground2)->ground1.name().compareTo(ground2.name()));
		homeGrounds.stream().forEach(ground -> {
			System.out.println(ground.name());
		});
		
	}

}
