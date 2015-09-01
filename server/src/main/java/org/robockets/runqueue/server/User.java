package org.robockets.runqueue.server;

import java.util.Calendar;
//import java.util.Iterator;

/**
 * Represents a user of the Run Queue Server.
 */
public class User {
	private String name;
	private Calendar timeJoined;
	private Priority priority = Priority.NORMAL; //Default
	private boolean isInQueue = false;

	/**
	 * Constructor for creating new users.
	 * @param name Username for the user
	 */
	public User (String name) {
		this.name = name;
	}
	
	/**
	 * @return User's username
	 */
	public String getName () {
		return name;
	}
	
	/**
	 * Updates the name of the user
	 * @param newName The new name for the user
	 */
	public void setName (String newName) {
		name = newName;
	}
	
	/**
	 * @return {@link org.robockets.runqueue.server.Priority Priority} of the user in the queue
	 */
	public Priority getPriority() {
		return priority;
	}
	
	/**
	 * @return The last time the user entered the queue
	 */
	public Calendar getTimeJoined () {
		return timeJoined;
	}
	
	/**
	 * @return Is the user currently in the queue or not?
	 */
	public boolean isInQueue () {
		/*
		Iterator<User> iterator = Server.userQueue.iterator();
		while(iterator.hasNext()) {
			User iteratorValue = (User) iterator.next();
			if(iteratorValue == this) {
				return true;
			}
		}
		return false;
		*/
		return isInQueue;
	}
	
	/**
	 * Puts the user in the last place in the queue.
	 * @param priority Priority of this user in the queue
	 */
	public void joinQueue (Priority priority) {
		timeJoined = Calendar.getInstance();
		this.priority = priority;
		isInQueue = true;
		Queue.userQueue.add(this);
	}
	
	/**
	 * Remove the user from the queue.
	 */
	public void leaveQueue () {
		isInQueue = false;
	}
}
