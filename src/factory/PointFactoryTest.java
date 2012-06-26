package factory;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import tournament.Result;

public class PointFactoryTest {

	@Test
	public void testGetPointsForResult() {
		assertEquals(3, PointFactory.getPointsForResult(Result.WIN));
		assertEquals(3, PointFactory.getPointsForResult(Result.BYE));
		assertEquals(1, PointFactory.getPointsForResult(Result.DRAW));
		assertEquals(0, PointFactory.getPointsForResult(Result.LOSE));
	}

}
