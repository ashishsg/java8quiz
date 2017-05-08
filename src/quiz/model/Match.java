package quiz.model;

import java.time.LocalDate;
public class Match {
	private String opponent;
	private int runsScored;
	private int ballsTaken;
	private int runsConceded;
	private int ballsbowled;
	private Ground venue;
	private int matchNumber;
	private LocalDate matchDate;
	private String team1Name;
	private boolean day;

	public Match(String opponent, int runsScored, int ballsTaken, int runsConceded, int ballsbowled, Ground venue,
			int matchNumber, LocalDate matchDate, String team1Name, boolean day) {
		super();
		this.opponent = opponent;
		this.runsScored = runsScored;
		this.ballsTaken = ballsTaken;
		this.runsConceded = runsConceded;
		this.ballsbowled = ballsbowled;
		this.venue = venue;
		this.matchNumber = matchNumber;
		this.matchDate = matchDate;
		this.team1Name = team1Name;
		this.day = day;
	}
	
	public String getOpponent() {
		return opponent;
	}
	public int getRunsScored() {
		return runsScored;
	}
	public int getBallsTaken() {
		return ballsTaken;
	}
	public int getRunsConceded() {
		return runsConceded;
	}
	public int getBallsbowled() {
		return ballsbowled;
	}
	public Ground getVenue() {
		return venue;
	}

	public int getMatchNumber() {
		return matchNumber;
	}

	public LocalDate getMatchDate() {
		return matchDate;
	}


	public String getTeam1Name() {
		return team1Name;
	}

	public int getTotalRunsScored() {
		return runsConceded + runsScored;
	}

	public boolean isDay() {
		return day;
	}


	@Override
	public String toString() {
		return "Match [opponent=" + opponent + ", runsScored=" + runsScored + ", ballsTaken=" + ballsTaken
				+ ", runsConceded=" + runsConceded + ", ballsbowled=" + ballsbowled + ", venue=" + venue
				+ ", matchNumber=" + matchNumber + ", matchDate=" + matchDate + ", team1Name=" + team1Name + ", day="
				+ day + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matchDate == null) ? 0 : matchDate.hashCode());
		result = prime * result + matchNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		if (matchDate == null) {
			if (other.matchDate != null)
				return false;
		} else if (!matchDate.equals(other.matchDate))
			return false;
		if (matchNumber != other.matchNumber)
			return false;
		return true;
	}
	
	
	
}
