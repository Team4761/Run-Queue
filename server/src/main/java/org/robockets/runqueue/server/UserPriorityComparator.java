package org.robockets.runqueue.server;

import java.util.Comparator;

/**
 * Comparator for comparing the values of integers. Used by
 * {@link org.robockets.runqueue.server.Server#comparator}
 */
public class UserPriorityComparator implements Comparator<User> {

	/**
	 * Comparator for checking if the priority of u1 is less than, greater than
	 * or equal to the priority of user two.
	 * @param u1 First user
	 * @param u2 Second user
	 */
	@Override
	public int compare (User u1, User u2) {
		if (u1.getPriority().ordinal() < u2.getPriority().ordinal()) {
			return -1;
		}
		if (u1.getPriority().ordinal() > u2.getPriority().ordinal()) {
			return 1;
		}
		return 0;
	}

}
