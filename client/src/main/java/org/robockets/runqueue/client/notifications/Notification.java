package org.robockets.runqueue.client.notifications;


public class Notification {
	private String os = System.getProperty("os.name").toLowerCase();
	private String title, body;
	
	/**
	 * Create instance of Notification
	 * 
	 * @param title Title of the Notification (placed at the top)
	 * @param body Text placed under the title
	 */
	public Notification (String title, String body) {
		this.title = title;
		this.body = body;
	}
	
	/**
	 * Send a notification based on your OS
	 */
	public void send () {
		if (isMac()) {
			sendMac();
		} else {
			sendOther();
		}
	}
	
	/**
	 * Send a Mac notification
	 */
	private void sendMac () {
		OSXNotification notification = new OSXNotification(title, body);
		notification.display();
	}
	
	/**
	 * Send a Windows notification (UNIMPLEMENTED)
	 */
	private void sendWindows () {
		
	}
	
	/**
	 * Send a Swing notification
	 */
	private void sendOther () {
		SwingNotification notification = new SwingNotification(title, body);
		notification.show();
	}
	
	/**
	 * Check if user is running on Windows
	 * @return true/false
	 */
	private boolean isWindows () {
		return (os.indexOf("win") >= 0);
	}
	
	/**
	 * Check if user is running on Mac OS X
	 * @return true/false
	 */
	private boolean isMac () {
		return (os.indexOf("mac") >= 0);
	}
}
