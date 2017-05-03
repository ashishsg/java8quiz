package quiz.runner.ajinkya;

import java.util.Collections;
import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
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
}