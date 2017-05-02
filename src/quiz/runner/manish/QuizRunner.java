package quiz.runner.manish;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import quiz.model.Ground;
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
		Collections.sort(teams, (t1,t2) -> t1.getName().compareTo(t2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\n***************** Print Team in Reverse Sorted Order ************************* ");
		Collections.sort(teams, (t1,t2) -> t2.getName().compareTo(t1.getName()));
		teams.forEach(team -> System.out.println(team.getName()));
		
		System.out.println("\n**************** Print the home ground names in alphabetical order ************************* ");
		List<Ground> homeGroundList = Arrays.asList(Ground.values());
		Collections.sort(homeGroundList, (g1,g2) -> g1.toString().compareTo(g2.toString()));
		System.out.println(homeGroundList);
	}

}
