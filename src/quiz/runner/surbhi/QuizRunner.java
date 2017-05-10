package quiz.runner.surbhi;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.omg.Messaging.SyncScopeHelper;

import quiz.model.Ground;
import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {
		quiz1();
	}
	public static void quiz1(){
		List<Team> teams = QuizDataProvider.getTeams();
		Team t;

		System.out.println("-------------Team and respective HomeGround-------------");
		teams.stream().forEach(teams1->
		{
			System.out.println(teams1.getName()+" : "+teams1.getHomeGround());
		});

		System.out.println("\n\n------------Teams in order-------------");
		System.out.println("Name--> ");
		teams.sort((Team t1,Team t2) -> t1.getName().compareTo(t2.getName()));
		teams.forEach(teams1 -> {
			System.out.println(teams1.getName());
		});
		
		System.out.println("\n\n------------Teams in Reverse order-------------");
		System.out.println("Name--> ");
		teams.sort((Team t1,Team t2) -> t2.getName().compareTo(t1.getName()));
		teams.forEach(teams1 -> {
			System.out.println(teams1.getName());
		});
		
		System.out.println("\n\n------------HomeGrounds-------------");
		teams.sort((Team t1,Team t2) -> t1.getHomeGround().toString().compareTo(t2.getHomeGround().toString()));
		teams.forEach(teams1 -> {
			System.out.println(teams1.getHomeGround());
		});
	}
	
	
}
