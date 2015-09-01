package org.robockets.runqueue.server;

/**
 * Priorities deployers can have. Values <b>MUST</b> be in order from lowest
 * to highest. If you wish to add new values, you must edit the test over at
 * {@link org.robockets.runqueue.server.PriorityTest#testItemsInOrder()}
 */
public enum Priority {
	/**
	 * Just sort of messing around or playing with a new idea.
	 */
	LOW,
	
	/**
	 * Normal, every day project. Should always be the default option.
	 */
	NORMAL,
	
	/**
	 * Whatever the user wants to work on is <i>probably</i> more important
	 * than what the other people in the queue want to work on.
	 */
	HIGH,
	
	/**
	 * The user <b>must</b> have the robot <b>now!</b>
	 */
	CRITICAL,
}
