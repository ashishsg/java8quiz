package quiz.runner.vishwanath;

import java.util.Comparator;
import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

	List<Team> teams = QuizDataProvider.getTeams();
		

		teams.stream().map(team -> team.getName()+ " : " + team.getHomeGround()).forEach(System.out::println);
		System.out.println("________________________________________________");
		teams.stream().map(t -> t.getName()).sorted().forEach(System.out::println);
		System.out.println("________________________________________________");
		teams.stream().map(team -> team.getName()).sorted(Comparator.reverseOrder()).forEach(System.out::println);
		System.out.println("________________________________________________");
		teams.stream().map(t -> t.getHomeGround()).sorted().forEach(System.out::println);
	}

}
