package quiz.runner.ashish;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;

public class Quiz4Solution {

	public static void main(String[] args) {


		// Quiz 1: Find out the dates when match(s) were played at that venue.
		// Sample output : EDENGARDENS=[2017-04-04]
		List<Team> teams = QuizDataProvider.getTeams();
		teams.stream().flatMap(t -> t.getMatches().stream()).distinct()
				.collect(Collectors.groupingBy(Match::getVenue, HashMap::new,
						Collectors.mapping(Match::getMatchDate, Collectors.toList())))
				.entrySet().forEach(System.out::println);
		
		//Quiz 2: Find the match in which maximum number of runs were scored at each venue
		teams.stream().flatMap(t -> t.getMatches().stream()).distinct()
				.collect(Collectors.groupingBy(Match::getVenue,
						Collectors.collectingAndThen(
								Collectors.maxBy(Comparator.comparingInt(Match::getTotalRunsScored)), Optional::get)))
				.entrySet().forEach(System.out::println);
		
		// Quiz 3: Find total runs scored at a venue
		teams.stream().flatMap(t -> t.getMatches().stream()).distinct().collect(Collectors.groupingBy(Match::getVenue,
				Collectors.summingInt(Match::getTotalRunsScored))).entrySet().forEach(System.out::println);

		// Quiz 4: Separate out the matches as day match or night match

		// Map<Boolean, List<Integer>> --> dayOrNightToListOFMatchNumberMap
		teams.stream().flatMap(t -> t.getMatches().stream()).distinct()
				.collect(Collectors.partitioningBy(Match::isDay)).entrySet().forEach(System.out::println);

		teams.stream().flatMap(t -> t.getMatches().stream()).distinct()
				.collect(Collectors.partitioningBy(Match::isDay, Collectors.mapping(Match::getMatchNumber, Collectors.toList())))
				.entrySet().forEach(System.out::println);


	}

}
