package org.robockets.runqueue.server;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PriorityTest {

	/**
	 * Makes sure that the priorities in Priority are in the correct order.
	 */
	@Test
	public void testItemsInOrder () {
		assertEquals(Priority.LOW.ordinal(), 0);
		assertEquals(Priority.NORMAL.ordinal(), 1);
		assertEquals(Priority.HIGH.ordinal(), 2);
		assertEquals(Priority.CRITICAL.ordinal(), 3);
	}

}
