package util;

import static java.lang.Math.pow;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

import tournament.Result;
import actors.Player;

public class RatingCalculator {

	public Integer getUpdatedRatingsForMainPlayer(Player playerA, Map<Player, Result> matches) {

		BigDecimal playerAActualScore = BigDecimal.ZERO;
		BigDecimal expectedPlayerAScore = BigDecimal.ZERO;

		for (Player playerB : matches.keySet()) {
			Result result = matches.get(playerB);

			expectedPlayerAScore = expectedPlayerAScore.add(getExpectedScore(playerA, playerB));
			playerAActualScore = playerAActualScore.add(getActualPlayerAScore(result));
		}

		return getUpdatedRanking(playerA, playerAActualScore, expectedPlayerAScore);
	}

	private BigDecimal getActualPlayerAScore(Result playerAResult) {
		switch (playerAResult) {
			case BYE:
			case WIN:
				return BigDecimal.ONE;
			case LOSE:
				return BigDecimal.ZERO;
			default:
				return BigDecimal.valueOf(0.5);
		}
	}

	private BigDecimal getExpectedScore(Player playerA, Player playerB) {
		BigDecimal qA = getQFactor(playerA.getRating());
		BigDecimal qB = getQFactor(playerB.getRating());
		BigDecimal divisor = qA.add(qB);
		return qA.divide(divisor, RoundingMode.HALF_UP);
	}

	private BigDecimal getQFactor(double ranking) {
		BigDecimal DEFAULT_DIVISOR = BigDecimal.valueOf(400);
		BigDecimal exponent = BigDecimal.valueOf(ranking).divide(DEFAULT_DIVISOR);
		return BigDecimal.valueOf(pow(10, exponent.doubleValue()));
	}

	private Integer getUpdatedRanking(Player player, BigDecimal actualScore, BigDecimal expectedScore) {
		BigDecimal currentRating = BigDecimal.valueOf(player.getRating());
		BigDecimal kfactor = BigDecimal.valueOf(player.getKFactor());
		BigDecimal difference = actualScore.subtract(expectedScore);

		BigDecimal result = kfactor.multiply(difference).add(currentRating);

		return result.intValue();
	}

}
