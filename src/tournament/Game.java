package tournament;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private Result result = Result.UNPLAYED;

	private List<Game> subgames = new ArrayList<>();

	public Game() {

	}

	public void addResult(Result result) {
		Game subGame = new Game();
		subGame.result = result;
		subgames.add(subGame);
	}

	public Result getResult() {
		return result;
	}

	public int getGamePoints() {
		return 0;
	}

	public boolean isMultiGame() {
		return !subgames.isEmpty();
	}

	public List<Game> getSubgames() {
		return subgames;
	}
}
