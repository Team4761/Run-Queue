package org.robockets.runqueue.server;

import java.util.Calendar;
import java.util.Iterator;

/**
 * Represents a user of the Run Queue Server.
 */
public class User {
	private String name;
	private Calendar timeLastJoined;
	
	/**
	 * Constructor for creating new users.
	 * @param name Username for the user
	 */
	public User(String name) {
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
	 * @return The last time the user entered the queue
	 */
	public Calendar getTimeLastJoined () {
		return timeLastJoined;
	}
	
	/**
	 * @return Is the user currently in the queue or not?
	 */
	public boolean isInQueue () {
		Iterator<User> iterator = Server.userQueue.iterator();
		while(iterator.hasNext()) {
			User iteratorValue = (User) iterator.next();
			if(iteratorValue == this) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Puts the user in the last place in the queue.
	 */
	public void joinQueue () {
		timeLastJoined = Calendar.getInstance();
		Server.userQueue.add(this);
	}
	
	/**
	 * Remove the user from the queue. <b>Not completed yet.</b>
	 */
	public void leaveQueue () {
		timeLastJoined = null;
		//TODO: drop user from queue
	}
}
