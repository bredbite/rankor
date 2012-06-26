package actors;

import java.util.ArrayList;
import java.util.List;

import tournament.Match;

public class Player {

	Integer rating;
	private Integer matchesPlayed = 0;

	List<Match> matches = new ArrayList<>();

	public Player(Integer rating) {
		this.rating = rating;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(Integer gamesPlayed) {
		this.matchesPlayed = gamesPlayed;
	}

	public Integer getKFactor() {
		if (matchesPlayed < 30) {
			return 32;
		}

		if (rating < 2400) {
			return 16;
		}

		return 10;
	}

	//TODO: Should add to matchesplayed, leaving as is for now
	public void addPlayedMatch(Match match) {
		matches.add(match);
	}
}
