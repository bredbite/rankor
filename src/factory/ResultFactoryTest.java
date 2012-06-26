package factory;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tournament.Game;
import tournament.Match;
import tournament.Result;

public class ResultFactoryTest {

	@Test
	public void testGetResultForMatchWon() {
		testGetResultForMatch(Result.WIN, Result.WIN, Result.WIN);
		testGetResultForMatch(Result.WIN, Result.WIN, Result.DRAW);
		testGetResultForMatch(Result.WIN, Result.WIN, Result.LOSE, Result.WIN);
		testGetResultForMatch(Result.WIN, Result.WIN, Result.WIN, Result.WIN);
	}

	@Test
	public void testGetResultForMatchLost() {
		testGetResultForMatch(Result.LOSE, Result.LOSE, Result.LOSE);
		testGetResultForMatch(Result.LOSE, Result.LOSE, Result.DRAW);
		testGetResultForMatch(Result.LOSE, Result.WIN, Result.LOSE, Result.LOSE);
		testGetResultForMatch(Result.LOSE, Result.LOSE, Result.LOSE, Result.LOSE);
	}

	@Test
	public void testGetResultForMatchDraw() {
		testGetResultForMatch(Result.DRAW, Result.DRAW);
		testGetResultForMatch(Result.DRAW, Result.LOSE, Result.WIN, Result.DRAW);

	}

	@Test
	public void testGetResultForMatchBye() {
		testGetResultForMatch(Result.BYE, Result.BYE);
	}

	@Test
	public void testGetResultForGameWon() {
		testGetResultForGame(Result.WIN, Result.WIN, Result.WIN);
		testGetResultForGame(Result.WIN, Result.WIN, Result.DRAW);
		testGetResultForGame(Result.WIN, Result.WIN, Result.LOSE, Result.WIN);
		testGetResultForGame(Result.WIN, Result.WIN, Result.WIN, Result.WIN);
	}

	@Test
	public void testGetResultForGameLost() {
		testGetResultForGame(Result.LOSE, Result.LOSE, Result.LOSE);
		testGetResultForGame(Result.LOSE, Result.LOSE, Result.DRAW);
		testGetResultForGame(Result.LOSE, Result.WIN, Result.LOSE, Result.LOSE);
		testGetResultForGame(Result.LOSE, Result.LOSE, Result.LOSE, Result.LOSE);
	}

	@Test
	public void testGetResultForGameDraw() {
		testGetResultForGame(Result.DRAW, Result.DRAW);
		testGetResultForGame(Result.DRAW, Result.LOSE, Result.WIN, Result.DRAW);

	}

	@Test
	public void testGetResultForGameBye() {
		testGetResultForGame(Result.BYE, Result.BYE);
	}

	@Test
	public void testGetResultForMatchWithSubGamesWin() {
		List<Game> games = new ArrayList<>();
		games.add(getGameWithSubGames(Result.WIN, Result.WIN));
		games.add(getGameWithSubGames(Result.LOSE, Result.LOSE));
		games.add(getGameWithSubGames(Result.WIN, Result.WIN));

		testGetResultForMatchWithSubGames(Result.WIN, games);
	}

	@Test
	public void testGetResultForMatchWithSubGamesWinEndingDraw() {
		List<Game> games = new ArrayList<>();
		games.add(getGameWithSubGames(Result.WIN, Result.WIN));
		games.add(getGameWithSubGames(Result.LOSE, Result.LOSE));
		games.add(getGameWithSubGames(Result.WIN, Result.WIN));
		games.add(getGameWithSubGames(Result.DRAW));

		testGetResultForMatchWithSubGames(Result.WIN, games);
	}

	@Test
	public void testGetResultForMatchWithSubGamesLose() {
		List<Game> games = new ArrayList<>();
		games.add(getGameWithSubGames(Result.WIN, Result.WIN));
		games.add(getGameWithSubGames(Result.LOSE, Result.LOSE));
		games.add(getGameWithSubGames(Result.LOSE, Result.LOSE));

		testGetResultForMatchWithSubGames(Result.LOSE, games);
	}

	@Test
	public void testGetResultForMatchWithSubGamesDraw() {
		List<Game> games = new ArrayList<>();
		games.add(getGameWithSubGames(Result.WIN, Result.WIN));
		games.add(getGameWithSubGames(Result.LOSE, Result.LOSE));
		games.add(getGameWithSubGames(Result.DRAW));

		testGetResultForMatchWithSubGames(Result.DRAW, games);
	}

	@Test
	public void testSortMatchesOnPosition() {
		List<Game> player1Games = new ArrayList<>();
		player1Games.add(getGameWithSubGames(Result.WIN, Result.LOSE, Result.LOSE));
		Match player1Match = getMatchWithSubGames(player1Games);

		List<Game> player2Games = new ArrayList<>();
		player2Games.add(getGameWithSubGames(Result.LOSE, Result.LOSE, Result.LOSE));
		Match player2Match = getMatchWithSubGames(player2Games);

		List<Game> player3Games = new ArrayList<>();
		player3Games.add(getGameWithSubGames(Result.WIN, Result.LOSE, Result.WIN));
		Match player3Match = getMatchWithSubGames(player3Games);

		List<Game> player4Games = new ArrayList<>();
		player4Games.add(getGameWithSubGames(Result.WIN, Result.WIN, Result.WIN));
		Match player4Match = getMatchWithSubGames(player4Games);

		List<Match> matches = new ArrayList<>();
		matches.add(player1Match);
		matches.add(player2Match);
		matches.add(player3Match);
		matches.add(player4Match);

		List<Match> expected = new ArrayList<>();
		expected.add(player4Match);
		expected.add(player3Match);
		expected.add(player1Match);
		expected.add(player2Match);

		List<Match> sortedMatches = ResultFactory.sortMatchesOnPosition(matches);

		assertEquals(expected, sortedMatches);
	}

	private void testGetResultForMatch(Result expected, Result... played) {
		Match match = getMatchWithSubGames(getGames(played));
		assertEquals(expected, ResultFactory.getResultFor(match));
	}

	private void testGetResultForMatchWithSubGames(Result expected, List<Game> games) {
		Match match = getMatchWithSubGames(games);
		assertEquals(expected, ResultFactory.getResultFor(match));
	}

	private void testGetResultForGame(Result expected, Result... played) {
		Game game = getGameWithSubGames(played);
		assertEquals(expected, ResultFactory.getResultFor(game));
	}

	private Match getMatchWithSubGames(List<Game> games) {
		Match match = new Match();

		for (Game game : games) {
			match.addGame(game);
		}

		return match;
	}

	private Game getGameWithSubGames(Result... played) {
		Game game = new Game();

		for (Result result : played) {
			game.addResult(result);
		}

		return game;
	}

	private List<Game> getGames(Result... results) {
		List<Game> games = new ArrayList<>();

		for (Result result : results) {
			Game game = getGameWithSubGames(result);
			games.add(game);
		}

		return games;
	}

}
