package quiz;

public class Match {
	private String opponent;
	private int runsScored;
	private int ballsTaken;
	private int runsConceded;
	private int ballsbowled;
	private Ground venue;
	
	public Match(String opponent, int runsScored, int ballsTaken, int runsConceded, int ballsbowled, Ground venue) {
		super();
		this.opponent = opponent;
		this.runsScored = runsScored;
		this.ballsTaken = ballsTaken;
		this.runsConceded = runsConceded;
		this.ballsbowled = ballsbowled;
		this.venue = venue;
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

	@Override
	public String toString() {
		return "Match [opponent=" + opponent + ", runsScored=" + runsScored + ", ballsTaken=" + ballsTaken
				+ ", runsConceded=" + runsConceded + ", ballsbowled=" + ballsbowled + ", venue=" + venue + "]";
	}
	
	
	
}
