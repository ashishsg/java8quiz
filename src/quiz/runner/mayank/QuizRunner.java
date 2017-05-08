package quiz.runner.mayank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import quiz.model.QuizDataProvider;
import quiz.model.Team;

/**
 * 
 * @author mayanks
 *
 */
public class QuizRunner {

	private QuizRunner() {

	}

	public static void main(String[] args) {
		quiz3();
	}

	private static void quiz3() {
		List<Team> teams = QuizDataProvider.getTeams();

		System.out.println("******** Matches played on 4th April 2017 *******");
		teams.stream().forEach(t -> t.getMatches().stream()
				.filter(p -> LocalDate.of(2017, 4, 4).equals(p.getMatchDate())).forEach(System.out::println));

		System.out.println("******** Duration of the Tournament *******");
		Set<LocalDate> set = teams.stream().flatMap(team -> team.getMatches().stream()).collect(Collectors.toList())
				.stream().map(m -> m.getMatchDate()).collect(Collectors.toSet());
		Optional<LocalDate> firstDate = set.stream().reduce((f, l) -> l);
		Optional<LocalDate> lastDate = set.stream().reduce((f, l) -> f);
		System.out.println(Period.between(firstDate.get(), lastDate.get()).getDays() + 1 + " Days");

		System.out.println("******** Matches played by all teams *******");
		teams.stream().forEach(t -> t.getMatches().stream().forEach(System.out::println));

		System.out.println("******** Maximum Balls required by team 2 to get NRR better than team 1 *******");
		BigDecimal runsConceded = new BigDecimal("390.000");
		BigDecimal ballsBowled = new BigDecimal("360.000");
		BigDecimal runsScored = new BigDecimal("360.000");
		BigDecimal ballsFaced = new BigDecimal("240.000");

		BigDecimal maxBalls = (runsScored.divide(
				new BigDecimal("0.109").add(runsConceded.divide(ballsBowled, 3, RoundingMode.HALF_UP)), 3,
				RoundingMode.HALF_UP)).subtract(ballsFaced);
		System.out.println("Maximum Balls Required : " + maxBalls.intValue());
	}

}
