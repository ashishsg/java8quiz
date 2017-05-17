package quiz.runner.surbhi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.omg.Messaging.SyncScopeHelper;

import quiz.model.Ground;
import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {
		System.out.println("Quiz 1 ---->>>");
		quiz1();
		System.out.println("\n\n Quiz 2 ---->>>");
		quiz2();
		System.out.println("\n\n Quiz 3 ---->>>");
		quiz3();
	}
	public static void quiz1(){
		List<Team> teams = QuizDataProvider.getTeams();
		Team t;

		System.out.println("-------------Team and respective HomeGround-------------");
		teams
		.stream()
		.forEach(teams1->
		{
			System.out.println(teams1.getName()+" : "+teams1.getHomeGround());
		});

		System.out.println("\n------------Teams in order-------------");
		System.out.println("Name--> ");
		teams
		.sort((Team t1,Team t2) -> t1.getName().compareTo(t2.getName()));
		teams
		.forEach(teams1 -> {
			System.out.println(teams1.getName());
		});

		System.out.println("\n------------Teams in Reverse order-------------");
		System.out.println("Name--> ");
		teams
		.sort((Team t1,Team t2) -> t2.getName().compareTo(t1.getName()));
		teams
		.forEach(teams1 -> {
			System.out.println(teams1.getName());
		});

		System.out.println("\n------------HomeGrounds-------------");
		teams
		.sort((Team t1,Team t2) -> t1.getHomeGround().toString().compareTo(t2.getHomeGround().toString()));
		teams.forEach(teams1 -> {
			System.out.println(teams1.getHomeGround());
		});
	}

	public static void quiz2(){
		List<Team> teams= QuizDataProvider.getTeams();

		System.out.println("------------Team -> HomeGround with its Content------------");
		System.out.println(teams
				.stream()
				.collect(Collectors.toMap(Team :: getName, Team :: getHomeGround)));

		System.out.println("\n------------Pune Matches Played GroundName:Count------------");
		System.out.println(teams
				.stream()
				.filter(teams1 -> teams1.getName().equals(QuizDataProvider.TEAM1))
				.flatMap(teams1 -> teams1.getMatches().stream())
				.collect(Collectors.groupingBy(Match :: getVenue, Collectors.counting())));

		System.out.println("\n------------Runs Scored by Pune------------");
		System.out.println("Pune = "+teams
				.stream()
		.filter(teams1 -> teams1.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(teams1 -> teams1.getMatches().stream())
		.collect(Collectors.summingInt(Match :: getRunsScored)));

		System.out.println("\n------------Pune Played at Wankhede? ------------");
		boolean yn= teams
				.stream()
				.filter(teams1 -> teams1.getName().equals(QuizDataProvider.TEAM1))
				.flatMap(teams1 -> teams1.getMatches().stream())
				.anyMatch(ground -> Ground.WANKHEDE == ground.getVenue());
		System.out.println((yn) ? "Yes" : "No" );

		System.out.println("\n------------How many matches did pune team win? ------------");
		System.out.println(" "+teams
				.stream()
		.filter(teams1 -> teams1.getName().equals(QuizDataProvider.TEAM1))
		.flatMap(teams1 -> teams1.getMatches().stream())
		.filter(win -> win.getRunsScored() > win.getRunsConceded())
		.count());

	}

	public static void quiz3(){
		List<Team> teams= QuizDataProvider.getTeams();
		System.out.println("------------Matches played on 4th april 2017------------");
		teams
		.stream()
		.flatMap(teams1 -> teams1.getMatches().stream()).distinct()
		.filter(match -> match.getMatchDate().equals(LocalDate.of(2017, 04, 04)))
		.collect(Collectors.toList())
		.forEach(System.out::println);

		System.out.println("\n------------Duration of Tournament------------");
		System.out.println(" "+ teams
				.stream()
		.flatMap(teams1 -> teams1.getMatches().stream()).map(Match :: getMatchDate)
		.distinct().count());

		System.out.println("\n------------Details of all the Matches------------");
		teams
		.stream()
		.collect(Collectors.toMap(Team :: getName, Team :: getMatches))
		.forEach((k,v) -> {
			System.out.println("Team: "+k+ "\nMatch: "+v);
		});

		BigDecimal runsConceded = new BigDecimal(390.0).setScale(3, RoundingMode.CEILING);
		BigDecimal ballsBowled = new BigDecimal(360.0).setScale(3, RoundingMode.CEILING);
		BigDecimal runScored = new BigDecimal(360.0).setScale(3, RoundingMode.CEILING);

		System.out.println("\n------------The maximum number of balls team 2 can take to score 110 runs?------------");
		System.out.println(IntStream
				.range(1, 120)
				.filter(ballsFaced -> BigDecimal.valueOf(0.109).compareTo(runScored.divide(new BigDecimal(240 + ballsFaced).setScale(3, RoundingMode.CEILING),3,RoundingMode.CEILING).subtract(runsConceded.divide(ballsBowled, 3,RoundingMode.CEILING))) <0 )
				.max()
				.getAsInt());
	}
}
