package util;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import tournament.Result;
import actors.Player;

public class RatingCalculatorTest {

	/**
	 * <p>
	 * Calculates new rating for player A according to the example given on <a href="http://en.wikipedia.org/wiki/Elo_rating_system#Mathematical_details">wikipedia</a>.
	 * </p>
	 * 
	 * <h2>Details</h2>
	 * <p>
	 * <blockquote>Suppose Player A has a rating of 1613, and plays in a five-round tournament. He loses to a player rated 1609, draws with a player rated 1477, defeats a player rated 1388, defeats a player rated 1586, and loses to a player rated 1720. His actual score is (0 + 0.5 + 1 + 1 + 0) = 2.5. His expected
	 * score, calculated according to the formula above, was (0.506 + 0.686 + 0.785 + 0.539 + 0.351) = 2.867. Therefore his new rating is (1613 + 32· (2.5 − 2.867)) = 1601, assuming that a K factor of 32 is used.</blockquote>
	 * </p>
	 * */
	@Test
	public void testGetUpdatedRatingsForMainPlayer() {
		Player playerA = new Player(1613);
		playerA.setMatchesPlayed(0);

		Map<Player, Result> results = new LinkedHashMap<>();

		results.put(new Player(1609), Result.LOSE);
		results.put(new Player(1477), Result.DRAW);
		results.put(new Player(1388), Result.WIN);
		results.put(new Player(1586), Result.WIN);
		results.put(new Player(1720), Result.LOSE);

		RatingCalculator ratingCalculator = new RatingCalculator();

		Integer actual = ratingCalculator.getUpdatedRatingsForMainPlayer(playerA, results);

		assertEquals(new Integer(1601), actual);

	}
}
