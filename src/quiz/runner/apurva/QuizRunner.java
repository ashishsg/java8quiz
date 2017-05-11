package quiz.runner.apurva;

import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		Team t1;
		t1.getName();
		System.out.println(teams);
	}

}
