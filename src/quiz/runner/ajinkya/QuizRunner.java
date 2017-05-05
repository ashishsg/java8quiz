package quiz.runner.ajinkya;

import quiz.model.Ground;
import quiz.model.Match;
import quiz.model.QuizDataProvider;
import quiz.model.Team;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuizRunner {

    private QuizRunner() {

    }

    public static void main(String[] args) {

        List<Team> teams = QuizDataProvider.getTeams();
        //quiz1(teams);
        //quiz2(teams);
        quiz3(teams);
    }

    private static void quiz1(List<Team> teams) {
        /*
        1.	Print all the team name and home ground. Sample output – "Rising Super Giants : GAHUNJE".
        2.	Print the team names in natural sorted order.
        3.	Print the team names in reverse sorted order.
        4.	Print the home ground names in alphabetical order.
        */

        // 1
        teams.stream()
                .map(team -> new StringBuilder(team.getName()).append(": ").append(team.getHomeGround()).toString())
                .forEach(System.out::println);

        // 2
        teams.stream()
                .map(Team::getName)
                .sorted()
                .forEach(System.out::println);

        // 3
        teams.stream()
                .map(Team::getName)
                .sorted(Collections.reverseOrder())
                .forEach(System.out::println);

        // 4
        teams.stream()
                .map(team -> team.getHomeGround().name())
                .sorted()
                .forEach(System.out::println);
    }

    private static void quiz2(List<Team> teams) {

        /*
            1.	Create a map of Team name -> Home Ground and print the contents.
            2.	Count number of matches Pune team played at a ground. Print the output in format “Ground name : Count”. (hint: use team.getMatches())
            3.	Calculate total runs scored by Pune team.
            4.	Did Pune team play a match at WANKHEDE?
            5.	How many matches did Pune team win?
         */
        // 1
        teams.stream()
                .collect(Collectors.toMap(Team::getName, Team::getHomeGround))
                .entrySet()
                .forEach(System.out::println);

        // 2
        teams.stream()
                .filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
                .findAny().ifPresent(
                (team) -> team.getMatches().stream()
                        .collect(Collectors.groupingBy(Match::getVenue, Collectors.counting()))
                        .entrySet().stream()
                        .forEach(System.out::println));

        // 3
        teams.stream()
                .filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
                .findAny().ifPresent(
                (team) -> {
                    int runsScored = team.getMatches().stream()
                            .mapToInt(Match::getRunsScored)
                            .sum();
                    System.out.println(runsScored);
                });

        // 4
        teams.stream()
                .filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
                .findAny().ifPresent(team -> {
            boolean playedAtVenue = team.getMatches().stream()
                    .anyMatch(match -> Ground.WANKHEDE == match.getVenue());
            System.out.println(playedAtVenue ? "YES" : "NO");
        });

        // 5
        teams.stream()
                .filter(team -> QuizDataProvider.TEAM1.equals(team.getName()))
                .findAny().ifPresent(team -> {
            long count = team.getMatches().stream()
                    .filter(match -> match.getRunsScored() > match.getRunsConceded())
                    .count();
            System.out.println(count);
        });
    }

    private static void quiz3(List<Team> teams) {
        /*
        1.	Print the details of matches played on 4th April 2017.
        2.	Find out the duration(number of days) of this tournament.(Hint: Use java.time.Period API)
        3.	Provide(print) details of all the matches to each team.(If there are n teams, print the details n times)
        4.	4.	Find out the maximum number of balls team 2 can take to score 110 runs to have NRR better than team 1.(This does not require the data model)
            -	Team 1 NRR = 0.109
            -	Team 2 has currently conceded 390 runs in 360 balls.(This includes the runs conceded in 3rd match)
            -	Team 2 has scored 250 runs in 240 balls in 2 matches.
            -	Team 2 need to score 110 runs in 120 balls to win the 3rd match
            -	NRR = (Runs scored/Balls faced) – (Runs conceded/Balls bowled)
            -	Use precision of 3.

        */

        // 1
        teams.stream()
                .flatMap(team -> team.getMatches().stream())
                .distinct()
                .filter(match -> LocalDate.of(2017, 4, 4).equals(match.getMatchDate()))
                .forEach(System.out::println);

        // 2
        Optional<LocalDate> startDateOptional = teams.stream()
                .flatMap(team -> team.getMatches().stream())
                .distinct()
                .map(Match::getMatchDate)
                .min(Comparator.naturalOrder());

        Optional<LocalDate> endDateOptional = teams.stream()
                .flatMap(team -> team.getMatches().stream())
                .distinct()
                .map(Match::getMatchDate)
                .max(Comparator.naturalOrder());
        if (startDateOptional.isPresent() && endDateOptional.isPresent()) {
            System.out.println(Period.between(startDateOptional.get(), endDateOptional.get()).getDays() + 1);
        }


        // 3
        IntStream.range(0, teams.size()).forEach((count) -> teams.stream()
                .flatMap(team -> team.getMatches().stream())
                .distinct()
                .forEach(System.out::println));

        // 4
        IntStream.rangeClosed(1, 120)
                .filter(numberOfBalls -> BigDecimal.valueOf(0.109).compareTo(calculateNetRunRate(360, 240 + numberOfBalls, 390, 360)) < 0
                ).max().ifPresent(System.out::println);
    }

    private static BigDecimal calculateNetRunRate(int runsScored, int ballsFaced, int runsConceded, int ballsBowled) {
        BigDecimal batting = BigDecimal.valueOf(runsScored).setScale(3, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(ballsFaced), RoundingMode.HALF_UP);
        BigDecimal bowling = BigDecimal.valueOf(runsConceded).setScale(3, RoundingMode.HALF_UP).divide(BigDecimal.valueOf(ballsBowled), RoundingMode.HALF_UP);
        return batting.subtract(bowling);
    }
}