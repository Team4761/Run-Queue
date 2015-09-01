package org.robockets.runqueue.server;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Queue {
	private static Comparator<User> comparator = new UserPriorityComparator();
	public static PriorityQueue<User> userQueue = new PriorityQueue<User>(10, comparator);
	
	/**
	 * Add a user to the queue. This method is only here for the sake of
	 * consistency.
	 * @param user User to add to the queue
	 * @param priority Priority the user joins the queue in
	 */
	public static void add (User user, Priority priority) {
		user.joinQueue(priority);
	}
	
	/**
	 * @return Next user in the queue.
	 */
	public static User next () {
		boolean foundUser = false;
		User u;
		while(foundUser == false) {
			u = userQueue.remove();
			if(u.isInQueue()) {
				foundUser = true;
				return u;
			}
		}
		return null;
	}
	
	/**
	 * @return {@link java.util.ArrayList} of every user that is in the queue
	 */
	public static ArrayList<User> getAllInQueue() {
		Iterator<User> iterator = userQueue.iterator();
		ArrayList<User> ret = new ArrayList<User>();
		while(iterator.hasNext()) {
			System.out.println("Has next!");
			User iteratorValue = (User) iterator.next();
			System.out.println(iteratorValue.isInQueue());
			if(iteratorValue.isInQueue()) {
				ret.add(iteratorValue);
			}
		}
		return ret;
	}
	
	/**
	 * Remove all users in the queue
	 */
	public static void clear() {
		Iterator<User> iterator = userQueue.iterator();
		while(iterator.hasNext()) {
			userQueue.remove();
		}
	}
}
