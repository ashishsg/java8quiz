package quiz.runner.manish;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
		System.out.println("******************* Print Team Name and Home Ground **********************");
		teams.forEach(team -> System.out.println(team.getName() + " : " + team.getHomeGround()));

		System.out.println("\n***************** Print Team in Natural Sorted Order ************************* ");
		Collections.sort(teams, (t1, t2) -> t1.getName().compareTo(t2.getName()));
		teams.forEach(team -> System.out.println(team.getName()));
		System.out.println("\n***************** Print Team in Reverse Sorted Order ************************* ");
		Collections.sort(teams, (t1, t2) -> t2.getName().compareTo(t1.getName()));
		teams.forEach(team -> System.out.println(team.getName()));

		System.out.println(
			"\n**************** Print the home ground names in alphabetical order ************************* ");
		List<Ground> homeGroundList = Arrays.asList(Ground.values());
		Collections.sort(homeGroundList, (g1, g2) -> g1.toString().compareTo(g2.toString()));
		System.out.println(homeGroundList);

		System.out.println("\n1. Map of Team : ");
		Map<String, Ground> teamMap = teams.stream()
				.collect(Collectors.toMap(Team::getName, Team::getHomeGround));
		teamMap.forEach((k, v) -> System.out.println(k + " : " + v));

		System.out.println("\n2. Number of matches Pune team played at a ground ");
		teams.stream().filter(t -> t.getName().equals(QuizDataProvider.TEAM1))
				.flatMap(t -> t.getMatches().stream())
				.collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()))
				.forEach((venue, count) -> System.out.println(venue + " : " + count));

		System.out
				.println("\n3. Pune Scored : " + teams.stream().filter(t -> t.getName().equals(QuizDataProvider.TEAM1))
						.flatMap(t -> t.getMatches().stream()).map(Match::getRunsScored).reduce(0, (a, b) -> a + b));

		System.out.println("\n4. Pune played @ Wankhede : "+ teams.stream().filter(t -> t.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(t -> t.getMatches().stream()).map(Match::getVenue).anyMatch( g -> g.equals(Ground.WANKHEDE)));
		
		System.out.println("\n5. Pune won : "+teams.stream().filter(t -> t.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(t -> t.getMatches().stream()).filter(m -> m.getRunsScored() > m.getRunsConceded()).count());
		
		System.out.println("\n Match played on 4th April : ");
		teams.stream().flatMap(t -> t.getMatches().stream()).filter(m -> m.getMatchDate().equals(LocalDate.of(2017, 4, 4))).forEach(System.out::println);
		
		System.out.println("\n Total Duration of Tournament : "+teams.stream().flatMap(t -> t.getMatches().stream()).map(Match::getMatchDate).distinct().count());
		
		System.out.println("\n Details of all the matches : ");
//		teams.stream().flatMap(t -> t.getMatches().stream()).forEach(System.out::println);
//		teams.stream().collect(Collectors.toMap(Team::getName, Team::getMatches)).forEach((k,v) -> System.out.println(" Team :"+k+ " Match : "+ v.forEach(System.out.print(v))));
//		team.stream().forEach(t-> System.out.println(" Team : "+t.getName()));
//		teams.stream().map(Team::getMatches).collect(Collectors.toList()).forEach(System.out::println);
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getMatches)).forEach((k,v) -> System.out.println(" Team : "+ k +"\n Match : "+ v));
		BigDecimal runsConceded = new BigDecimal(390.0).setScale(3, RoundingMode.CEILING);
		BigDecimal ballsBowled = new BigDecimal(360.0).setScale(3, RoundingMode.CEILING);
		BigDecimal runScored = new BigDecimal(360.0).setScale(3, RoundingMode.CEILING);
//		BigDecimal ballsFaced = new BigDecimal(280.0).setScale(3, RoundingMode.CEILING);
//		System.out.println(runScored.divide(ballsFaced, 3, RoundingMode.CEILING));
//		System.out.println(runsConceded.divide(ballsBowled, 3, RoundingMode.CEILING));
		System.out.println("\n Maximum number of balls team 2 can take to score 110 runs to have NRR better than team 1 :"+IntStream.range(1, 120).filter(ballsFaced -> BigDecimal.valueOf(0.109).compareTo(runScored.divide(new BigDecimal(240 + ballsFaced).setScale(3, RoundingMode.CEILING), 3, RoundingMode.CEILING).subtract(runsConceded.divide(ballsBowled, 3, RoundingMode.CEILING))) < 0).max().getAsInt());
		
		
		System.out.println("Quiz 4 : ");
		System.out.println(" Date and Matches : ");
		teams.stream().flatMap(t -> t.getMatches().stream()).distinct().collect(Collectors.groupingBy(Match::getVenue,Collectors.mapping(Match::getMatchDate, Collectors.toList()))).forEach((k,v) -> System.out.println(k + " = "+ v));
	
		System.out.println("\n Maximum number of runs were scored at each venue");
		teams.stream().flatMap(t -> t.getMatches().stream()).collect(Collectors.groupingBy(Match::getVenue,Collectors.summarizingInt(Match::getRunsScored))).forEach((k,v) -> System.out.println(" Venue :"+ k + " Max Run Scored :"+v.getMax()));
	
		System.out.println("\n Total runs scored at a venue : ");
		teams.stream().flatMap(t -> t.getMatches().stream()).collect(Collectors.groupingBy(Match::getVenue,Collectors.summingInt(Match::getRunsScored))).forEach((k,v) -> System.out.println(" Venue :"+ k + " Runs Scored :"+v));
	
		teams.stream().flatMap(t -> t.getMatches().stream()).distinct().collect(Collectors.toMap(Match::getMatchNumber, Match::isDay)).forEach((k,v) -> System.out.println("\n Match Number :"+k+ " Day Match :"+v.booleanValue()));
	}
}
