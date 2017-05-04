package quiz.runner.zinge;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import quiz.model.Ground;
import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {

		List<Team> teams = QuizDataProvider.getTeams();
		//System.out.println(teams);
		System.out.println("QuizRunner quiz 1 : Begin");
		quiz_one(teams);
		System.out.println("QuizRunner quiz 1 : End");
		
		
		System.out.println("QuizRunner quiz 2 : Begin");
		quiz_two(teams);
		System.out.println("QuizRunner quiz 2 : End");
	}
	
	private static void quiz_one(List<Team> teams){
		
		//1.Print all the team name and home ground. Sample output – "Rising Super Giants : GAHUNJE".
		System.out.println("1)=============  Print all the team name and home ground =========================");
		teams.stream()
		.map(x -> x.getName() + " : "+x.getHomeGround())
		.forEach(System.out::println);
		
		//2.	Print the team names in natural sorted order.
		System.out.println("\n2)=============  Print the team names in natural sorted order =================");
		teams.stream()
		.sorted(Comparator.comparing(Team::getName))
		.map(x->x.getName())
		.forEach(System.out::println);
		
		//3.	Print the team names in reverse sorted order
		System.out.println("\n3)=============  Print the team names in reverse sorted order =================");
		teams.stream()
		.sorted(Comparator.comparing(Team::getName).reversed())
		.map(x->x.getName())
		.forEach(System.out::println);
		
		//4.	Print the home ground names in alphabetical order.
		System.out.println("\n4)=============  Print the home ground names in alphabetical order =================");
		teams.stream()
		.map(x->x.getHomeGround().name())
		.sorted()
		.forEach(System.out::println);
		
	}

	private static void quiz_two(List<Team> teams){
		//1.	Create a map of Team name -> Home Ground and print the contents.
		System.out.println("\n1)=============  Create a map of Team name -> Home Ground and print the contents. ======");
		teams.stream()
		.collect(Collectors.toMap(Team::getName,v -> v.getHomeGround().name()))
		.forEach((k,v)->System.out.println("Team Name : " + k + ",  Home Ground: " + v));
		
		//2.	Count number of matches Pune team played at a ground. 
		//Print the output in format “Ground name : Count”. (hint: use team.getMatches())
		System.out.println("\n2)=============  Print the output in format Ground name : Count. ======");
		teams.stream()
		.filter(puneTeam -> puneTeam.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(team -> team.getMatches().stream())
		.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()))
		.entrySet()
		.forEach(System.out::println);
		
		//3.	Calculate total runs scored by Pune team
		System.out.println("\n3)=============  Calculate total runs scored by Pune team. ======");
		long totalRun = teams.stream()
		.flatMap(team -> team.getMatches().stream())
		.filter(puneTeam -> puneTeam.getOpponent().equals(QuizDataProvider.TEAM1))
		.collect(Collectors.summarizingLong(Match::getRunsScored)).getSum();
		System.out.println("Pune team total run : " +totalRun);
		
		//4.	Did Pune team play a match at WANKHEDE?
		System.out.println("\n4)=============  Did Pune team play a match at WANKHEDE?. ======");
		boolean yesNo = teams.stream()
		.filter(puneTeam -> puneTeam.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(team -> team.getMatches().stream())
		.anyMatch(ground -> Ground.WANKHEDE == ground.getVenue());
		System.out.println("Did Pune team play a match at WANKHEDE? "+ ((yesNo) ? "Yes" : "No"));
		
		//5.	How many matches did Pune team win?
		System.out.println("\n5)=============  How many matches did Pune team win?. ======");
		long count = teams.stream()
		.filter(puneTeam -> puneTeam.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(team -> team.getMatches().stream())
		.filter(win -> win.getRunsScored() > win.getRunsConceded())
		.count();
		
		System.out.println("How many matches did Pune team win?? "+ count);
		
	}

}
