package quiz.runner.sumesh;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		//quiz2(teams);

		System.out.println("Print the details of matches played on 4th April 2017.");
		teams.stream().flatMap(team -> team.getMatches().stream()).filter(match -> match.getMatchDate().equals(LocalDate.of(2017, 04, 04))).forEach(System.out::println);
		
		System.out.println("\nFind out the duration(number of days) of this tournament.");
		LocalDate firsMatchDate = teams.stream().flatMap(team -> team.getMatches().stream()).map(Match::getMatchDate).min(LocalDate::compareTo).get();
		LocalDate lastMatchDate = teams.stream().flatMap(team -> team.getMatches().stream()).map(Match::getMatchDate).max(LocalDate::compareTo).get();
		Period period = Period.between(firsMatchDate, lastMatchDate);
		System.out.println("Number of days : " + period.getDays());
		
		System.out.println("\nProvide(print) details of all the matches to each team.");
		teams.stream().flatMap(team -> team.getMatches().stream()).forEach(System.out::println);
		
		System.out.println("\nFind out the maximum number of balls team 2 can take to score 110 runs to have NRR better than team 1.");
		BigDecimal runCondede = new BigDecimal(390).divide(new BigDecimal(360), 3, RoundingMode.CEILING);
		BigDecimal runScored = new BigDecimal(360);
		BigDecimal compareNrr = BigDecimal.valueOf(0.109);
		OptionalInt optInt = IntStream.range(240,360).filter(noOfBallsCounter -> runScored.divide(BigDecimal.valueOf(noOfBallsCounter),3,RoundingMode.CEILING).subtract(runCondede).compareTo(compareNrr) == 1).max();
		System.out.println(optInt.getAsInt() - 240);
	}

	private static void quiz2(List<Team> teams) {
		//Below both line can be put into one.
		//Create map
		Map<String, Ground> teamNameGrndMap = teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround));
		//print contents
		teamNameGrndMap.forEach((key, value) -> System.out.println("Key: " + key +", value: "+ value));
		
		System.out.println("\nCount number of matches Pune team played at a ground. Print the output in format �Ground name : Count�. ");
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
