package factory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import tournament.Game;
import tournament.Match;
import tournament.Result;

public class ResultFactory {

	public static Result getResultFor(Game game) {
		if (game.isMultiGame()) {
			List<Game> games = game.getSubgames();
			return getResultForGames(games, games.size());
		}
		return game.getResult();
	}

	public static int getScoreFor(Game game) {
		if (game.isMultiGame()) {
			List<Game> games = game.getSubgames();

			int score = 0;
			for (Game subGame : games) {
				score += getScoreFor(subGame);
			}

			return score;
		}
		return PointFactory.getPointsForResult(game.getResult());
	}

	public static int getScoreFor(Match match) {
		int score = 0;
		for (Game game : match.getGames()) {
			score += getScoreFor(game);
		}
		return score;
	}

	public static Result getResultFor(Match match) {
		return getResultForGames(match.getGames(), match.getNoOfGames());
	}

	public static List<Match> sortMatchesOnPosition(List<Match> allPlayersMatches) {
		List<Match> sortedMatches = new ArrayList<>();
		Map<Integer, Match> matchTree = new TreeMap<>(Collections.reverseOrder());

		for (Match match : allPlayersMatches) {
			int score = getScoreFor(match);
			matchTree.put(score, match);
		}

		for (Match match : matchTree.values()) {
			sortedMatches.add(match);
		}

		return sortedMatches;
	}

	private static Result getResultForGames(List<Game> games, int winningBreakPoint) {

		BigDecimal res = BigDecimal.ZERO;

		int noOfGames = games.size();
		for (int i = 0; i < noOfGames; i++) {

			Game game = games.get(i);

			Result gameResult = getResultFor(game);
			if (gameResult == Result.BYE) {
				return Result.BYE;
			}

			res = res.add(getScoreForResult(gameResult));
			BigDecimal winComparator = res.multiply(BigDecimal.valueOf(2));
			int comparator = winComparator.compareTo(BigDecimal.valueOf(winningBreakPoint));

			// Add score to the result here

			if (comparator > 0) {
				return Result.WIN;
			}

			if (i == (noOfGames - 1) && comparator == 0) {
				return Result.DRAW;
			}

		}

		return Result.LOSE;
	}

	private static BigDecimal getScoreForResult(Result result) {
		switch (result) {
			case BYE:
			case WIN:
				return BigDecimal.ONE;
			case LOSE:
				return BigDecimal.ZERO;
			default:
				return BigDecimal.valueOf(0.5);
		}
	}

}
