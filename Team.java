package quiz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Team {
	private String name;
	private Ground homeGround;
	private List<Match> matches;
	
	public Team(String name, Ground homeGround, List<Match> matches) {
		super();
		this.name = name;
		this.homeGround = homeGround;
		this.matches = matches;
	}

	public String getName() {
		return name;
	}

	public Ground getHomeGround() {
		return homeGround;
	}

	public List<Match> getMatches() {
		return new ArrayList<>(matches);
	}

	@Override
	public String toString() {
		return "Team [name=" + name + ", homeGround=" + homeGround + ", matches=" + matches + "]";
	}
	
	public BigDecimal getNRR(Function<List<Match>, BigDecimal> nrrCalculator) {
		return nrrCalculator.apply(getMatches());
		
	}
	
	
}
