package quiz.runner.sumesh;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
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
		//quiz1(teams);
		
		//Below both line can be put into one.
		//Create map
		Map<String, Ground> teamNameGrndMap = teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround));
		//print contents
		teamNameGrndMap.forEach((key, value) -> System.out.println("Key: " + key +", value: "+ value));
		
		System.out.println("\nCount number of matches Pune team played at a ground. Print the output in format “Ground name : Count”. ");
		//Get Pune Team - Don't know if this is correct team. lol :P
		Optional<Team> puneTeam = teams.stream().filter(team -> team.getName().equals("Rising Super Giants")).findAny();
		//if pune team presents then get count by grouping on Ground
		if(puneTeam.isPresent()){
			Map<Ground, Long> grndCountMap =  puneTeam.get().getMatches().stream().collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()));
			grndCountMap.forEach((key, value) -> System.out.println(key +" : "+ value));
			
			System.out.println("\nCalculate total runs scored by Pune team");
			System.out.println(" Count : " + puneTeam.get().getMatches().stream().mapToInt(Match::getRunsScored).sum());
			
			System.out.println("\n Did Pune team play a match at WANKHEDE? ");
			System.out.println(puneTeam.get().getMatches().stream().anyMatch(match -> match.getVenue().equals(Ground.WANKHEDE)));
			
			System.out.println("\n How many matches did Pune team win? ");
			System.out.print(puneTeam.get().getMatches().stream().filter(match -> match.getRunsScored() > match.getRunsConceded()).count());
		}
	}

	private static void quiz1(List<Team> teams) {
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
