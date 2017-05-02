package quiz.runner.dadhich;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		// 1.Print all the team name and home ground
		teams.stream().forEach(teamDetail -> {
			System.out.println("1 Ans:" + teamDetail.getName() + ":" + teamDetail.getHomeGround());
		});
		System.out.println("----------End 1----------");
		
		// 2.Print the team names in natural sorted order.
		Collections.sort(teams, (team1, team2) -> team1.getName().compareTo(team2.getName()));
		teams.stream().forEach(teamDetail -> {
			System.out.println("2 Ans:" + teamDetail.getName() + ":" + teamDetail.getHomeGround());
		});
		System.out.println("----------End 2----------");
		
		// 3.Print the team names in reverse sorted order.
		Collections.sort(teams, (team1, team2) -> team2.getName().compareTo(team1.getName()));
		teams.stream().forEach(teamDetail -> {
			System.out.println("3 Ans:" + teamDetail.getName() + ":" + teamDetail.getHomeGround());
		});
		System.out.println("----------End 3 ----------");
		
		// 4.Print the home ground names in alphabetical order
		Collections.sort(teams, (team1, team2) -> team1.getHomeGround().toString().compareTo(team2.getHomeGround().toString()));
		teams.stream().forEach(teamDetail -> {
			System.out.println("4 Ans:" + teamDetail.getHomeGround());
		});
		System.out.println("----------End 4----------");
		// Second detailed Approach.
		teams.stream().sorted(new Comparator<Team>() {
			@Override
			public int compare(Team o1, Team o2) {
				return o1.getHomeGround().toString().compareTo(o2.getHomeGround().toString());
			}
			
		}).forEach(teamDetail -> {
			System.out.println("4 (Other Way) Ans:" + teamDetail.getHomeGround());
		});
		System.out.println("----------End 4 2 Way----------");
	}

}
