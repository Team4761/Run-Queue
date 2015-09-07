package org.robockets.runqueue.client.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private String username, priority;
	
	private Date startTime;
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
	
	public User (String username, String priority, String startTime) {
		this.username = username;
		this.priority = priority;
		try {
			this.startTime = format.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
	
	public static User findByName (String username) {
		User user;
		for (int i = 0; i < Models.queue.getRowCount(); i++) {
			if (Models.queue.getValueAt(i, 1).equals(username)) {
				user = new User((String) Models.queue.getValueAt(i, 1), (String) Models.queue.getValueAt(i, 2), (String) Models.queue.getValueAt(i, 1));
				return user;
			}
		}
		
		return null;
	}
	
	public static User findByIndex (int index) {
		for (int i = 0; i < Models.queue.getRowCount(); i++) {
			if ((Integer) Models.queue.getValueAt(i, 0) == index) {
				String username = (String) Models.queue.getValueAt(i, 1);
				String priority = (String) Models.queue.getValueAt(i, 2);
				String startTime = (String) Models.queue.getValueAt(i, 3);
				
				return new User(username, priority, startTime);
			}
		}
		
		return null;
	}
	
	public String getUsername () {
		return username;
	}
	
	public String getPriority () {
		return priority;
	}
	
	public long getWaitTime () {
		Date currentTime = new Date();
		return (currentTime.getTime() - startTime.getTime()) / 1000;
	}
	
	public String getStringWaitTime () {
		return Long.toString(getWaitTime()) + "s";
	}
	
	public void save (int rowIndex) {
		Models.queue.updateUser(rowIndex, this);
	}
	
	public void saveByName () {
		Models.queue.updateUserByUsername(this);
	}
}
