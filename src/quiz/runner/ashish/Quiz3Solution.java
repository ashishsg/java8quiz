package quiz.runner.ashish;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;

public class Quiz3Solution {

	public static void main(String[] args) {
		List<Team> teams = QuizDataProvider.getTeams();

		// Quiz 1: Print the details of matches played on 4th April 2017
		LocalDate matchDate = LocalDate.of(2017, 4, 4);
		teams.stream().map(Team::getMatches).flatMap(List::stream).distinct()
				.forEach(m -> {
					if (m.getMatchDate().equals(matchDate))
						System.out.println(m);
				});

		// Quiz 2: Find out the duration of this tournament.
		Set<LocalDate> matchDates = teams.stream().map(Team::getMatches).flatMap(List::stream).map(Match::getMatchDate)
				.collect(Collectors.toSet());
		Period tournamentPeriod = Period.between(matchDates.stream().sorted().findFirst().get(),
				matchDates.stream().sorted().skip(matchDates.size() - 1).findFirst().get());
		System.out.println("Number of days the tournament lasted : " + (tournamentPeriod.getDays() + 1));

		// Quiz 3: Print all details of each match for all the teams.
		teams.stream().map(Team::getMatches).flatMap(List::stream).forEach(m -> {
			IntStream.rangeClosed(1, teams.size()).forEach(i -> {
				System.out.println("Printing copy " + i + " match details for match no. " + m.getMatchNumber());
				System.out.println(m);
			});
		});

		// Quiz 4: Team 1 scored 441 runs in 360 balls and conceded 402 runs in
		// 360 balls in 3 matches. Team 2 had conceded 390 runs in 360 balls in
		// 3 matches and scored 250 runs in 240...
		// Team 2 needs to score 110 runs to win 3rd match....
		// Find out max number of balls they can take to score these runs so
		// that there net run rate is better than team 1
		// Formula Net run rate = (Runs scored/Balls taken) - (Runs conceded/
		// Balls bowled)
		
		BigDecimal team1NetRunrate = BigDecimal.valueOf(0.109);
		BigDecimal team2RunsConcededToBallsBowled = BigDecimal.valueOf(1.083);
		BigDecimal team2BallsFaced = BigDecimal.valueOf(240);
		BigDecimal team2RunsScored = BigDecimal.valueOf(360);

		int result = IntStream.rangeClosed(1, 120).sequential().filter(i -> {
			BigDecimal maxBallsFaced = BigDecimal.valueOf(i).add(team2BallsFaced);
			BigDecimal team2NRR = team2RunsScored.divide(maxBallsFaced, 3, RoundingMode.HALF_UP)
					.subtract(team2RunsConcededToBallsBowled);
			if (team2NRR.compareTo(team1NetRunrate) <= 0) {
				return true;
			}
			return false;
		}).limit(1).min().getAsInt() - 1;

		System.out.println("Max balls that can be taken by team 2 is " + result);

	}

}
