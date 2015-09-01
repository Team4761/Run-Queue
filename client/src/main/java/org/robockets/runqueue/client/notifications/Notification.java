package org.robockets.runqueue.client.notifications;


public class Notification {
	private String os = System.getProperty("os.name").toLowerCase();
	private String title, body;
	
	public Notification (String title, String body) {
		this.title = title;
		this.body = body;
	}
	
	public void send () {
		if (isMac()) {
			sendMac();
		} else if (isWindows()) {
			sendWindows();
		} else {
			sendOther();
		}
	}
	
	private void sendMac () {
		MacNotification notification = new MacNotification(title, body);
		notification.display();
	}
	
	private void sendWindows () {
		
	}
	
	private void sendOther () {
		SwingNotification notification = new SwingNotification(title, body);
		notification.show();
	}
	
	private boolean isWindows () {
		return (os.indexOf("win") >= 0);
	}
	
	private boolean isMac () {
		return (os.indexOf("mac") >= 0);
	}
}
