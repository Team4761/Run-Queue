package org.robockets.runqueue.server;

import org.junit.Test;

public class PriorityTest {

	/**
	 * Makes sure that the priorities in Priority are in the correct order.
	 */
	@Test
	public void testItemsInOrder() {
		assert(Priority.LOW.ordinal() == 0);
		assert(Priority.NORMAL.ordinal() == 1);
		assert(Priority.HIGH.ordinal() == 2);
		assert(Priority.CRITICAL.ordinal() == 3);
	}

}
