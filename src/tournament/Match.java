package tournament;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import actors.Player;

public class Match {

	private List<Game> games = new ArrayList<>();

	Map<Integer, Player> opponents = new TreeMap<>();

	public void addGame(Game game) {
		games.add(game);
	}

	public Result getMatchResult() {

		return null;
	}

	public int getMatchPoints() {
		return 0;
	}

	public Game getGame(int gameNo) {
		return games.get(gameNo);
	}

	public int getNoOfGames() {
		return games.size();
	}

	public List<Game> getGames() {
		return games;
	}

}
