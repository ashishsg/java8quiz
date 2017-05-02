package quiz.runner.sumesh;

import java.util.List;
import java.util.StringJoiner;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		//Print Team names : Home ground
		teams.stream().map(QuizRunner::joinString).forEach(System.out::println);
		
		System.out.println("\nPrint the team names in natural sorted order.");
		teams.stream().map(Team::getName).sorted().forEach(System.out::println);
		
		System.out.println("\nPrint the team names in reverse sorted order.");
		teams.stream().map(Team::getName).sorted((name1, name2) -> name2.compareTo(name1)).forEach(System.out::println);
		
		System.out.println("\nPrint the home ground names in alphabetical order");
		teams.stream().map(team -> team.getHomeGround().name()).sorted().forEach(System.out::println);
	}
	
	//Join Team name and Ground with  :
	private static String joinString(Team team){
		StringJoiner sj = new StringJoiner(":");
		sj.add(team.getName());
		sj.add(team.getHomeGround().name());
		return sj.toString();
	}

}
