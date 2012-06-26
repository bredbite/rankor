package factory;

import tournament.Result;

public class PointFactory {

	public static int getPointsForResult(Result result) {

		switch (result) {
			case BYE:
			case WIN:
				return 3;
			case DRAW:
				return 1;
			case LOSE:
				return 0;
			default:
				return 0;
		}
	}
}
