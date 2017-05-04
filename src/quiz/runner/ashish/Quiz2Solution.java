package quiz.runner.ashish;

import static quiz.model.QuizDataProvider.TEAM1;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import quiz.model.Ground;
import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;
public class Quiz2Solution {

	private Quiz2Solution() {
	}

	public static void main(String[] args) {
		List<Team> teams = QuizDataProvider.getTeams();

		// Quiz 1: Create a map of Team name -> Home Ground
		teams.stream().collect(Collectors.toMap(Team::getName, Team::getHomeGround)).entrySet()
				.forEach(System.out::println);
		// It's better to check of a value is present.
		Optional<Team> puneTeam = teams.stream().filter(t -> t.getName().equals(TEAM1)).findAny();
		if (puneTeam.isPresent()) {
			// Quiz 2: Count how many matches Pune team played at each ground
			Map<Ground, Long> result = puneTeam.get().getMatches()
				.stream().collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()));
			result.entrySet().stream().forEach(System.out::println);

			// Quiz 3: Find the runs scored by pune team:
			Optional<Integer> totalRunsScored = puneTeam.get().getMatches().stream().map(Match::getRunsScored)
					.reduce(Integer::sum);
			totalRunsScored.ifPresent(System.out::println);
			// or
			System.out.println(puneTeam.get().getMatches().stream().mapToInt(Match::getRunsScored).sum());
			//or
			System.out
					.println("" + puneTeam.get().getMatches().stream()
							.collect(Collectors.summingInt(Match::getRunsScored)));

			// Quiz 4: Find if pune team played at least 1 match at WANKHEDE
			boolean playedAtWankhede = puneTeam.get().getMatches().stream()
					.anyMatch(m -> Ground.WANKHEDE == m.getVenue());
			System.out.println("Did Pune team played any match at Wankhede - " + playedAtWankhede);

			// Quiz 5: How many matches did Pune team win?
			System.out.print(puneTeam.get().getMatches().stream()
					.filter(match -> match.getRunsScored() > match.getRunsConceded()).count());

		}
	}
}
