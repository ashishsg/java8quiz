package quiz.runner.zinge;

import java.util.Comparator;
import java.util.List;

import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		//System.out.println(teams);
		quiz_one(teams);
	}
	
	private static void quiz_one(List<Team> teams){
		

		//1.Print all the team name and home ground. Sample output – "Rising Super Giants : GAHUNJE".
		System.out.println("1)=============  Print all the team name and home ground =========================");
		teams.stream()
		.map(x -> x.getName() + " : "+x.getHomeGround())
		.forEach(System.out::println);
		
		//2.	Print the team names in natural sorted order.
		System.out.println("\n\n2)=============  Print the team names in natural sorted order =================");
		teams.stream()
		.sorted(Comparator.comparing(Team::getName))
		.map(x->x.getName())
		.forEach(System.out::println);
		
		//3.	Print the team names in reverse sorted order
		System.out.println("\n\n3)=============  Print the team names in reverse sorted order =================");
		teams.stream()
		.sorted(Comparator.comparing(Team::getName).reversed())
		.map(x->x.getName())
		.forEach(System.out::println);
		
		//4.	Print the home ground names in alphabetical order.
		System.out.println("\n\n3)=============  Print the home ground names in alphabetical order =================");
		teams.stream()
		.map(x->x.getHomeGround().name())
		.sorted()
		.forEach(System.out::println);
		
	}
	

}
