package org.robockets.runqueue.client.notifications;

import java.io.*;

/**
 * Modified from https://github.com/n9Mtq4/MacOSX-Notification-Library-Java
 */
public class MacNotification {
	
	private String title;
	private String text;
	private String subtitle;
	private String soundName;
	
	/**
	 * Initializes a new Notification with a title
	 * and text content.
	 * These can be overridden later with {@link org.robockets.runqueue.client.notifications.n9mtq4.demo.notifcationmac.Notification#setTitle(String)}
	 * and {@link org.robockets.runqueue.client.notifications.n9mtq4.demo.notifcationmac.Notification#setText(String)}
	 * @param title The title the notification will have.
	 * @param text The text content the notification will have.
	 * */
	public MacNotification(String title, String text) {
		
		this.title = title;
		this.text = text;
		
	}
	
	/**
	 * Initializes a new Notification.
	 * */
	public MacNotification() {
//		dummy
	}
	
	/**
	 * Displays the notification with the current values.
	 * */
	public boolean display() {
		
		if (!(System.getProperty("os.name").toLowerCase().contains("mac"))) return false;
		
		if (sc(text) && sc(title)) {
			
			if (sc(subtitle) && sc(soundName)) {
				return displayTitleTextSubtitleSoundname(title, text, subtitle, soundName);
			}else if (sc(subtitle)) {
				return displayTitleTextSubtitle(title, text, subtitle);
			}else if (sc(soundName)) {
				return displayTitleTextSoundname(title, text, soundName);
			}else {
				return displayTitleText(title, text);
			}
			
		}else {
			return false;
		}
		
	}
	
	private boolean displayTitleText(String title, String text) {
		sendShellCommand("echo \"display notification \\\"" + text + "\\\" with title \\\"" + title + "\\\"\" | osascript");
		return true;
	}
	
	private boolean displayTitleTextSubtitle(String title, String text, String subtitle) {
		sendShellCommand("echo \"display notification \\\"" + text + "\\\" with title \\\"" + title + "\\\" subtitle \\\"" + subtitle + "\\\"\" | osascript");
		return true;
	}
	
	private boolean displayTitleTextSoundname(String title, String text, String soundName) {
		sendShellCommand("echo \"display notification \\\"" + text + "\\\" with title \\\"" + title + "\\\" sound name \\\"" + soundName + "\\\"\" | osascript");
		return true;
	}
	
	private boolean displayTitleTextSubtitleSoundname(String title, String text, String subtitle, String soundName) {
		sendShellCommand("echo \"display notification \\\"" + text + "\\\" with title \\\"" + title + "\\\" subtitle \\\"" + subtitle + "\\\" sound name \\\"" + soundName + "\\\"\" | osascript");
		return true;
	}

	private String sendShellCommand(String command) {
		String output = "";
		saveFile("./.t.command", command);
		run("chmod +x ./.t.command");
		run("./.t.command");
		deleteFile("./.t.command");
		return output;
	}
	
	private boolean deleteFile(String path) {
		File f = new File(path);
		return f.delete();
	}
	
	private void saveFile(String path, String text) {
		String[] tokens_String = text.split("\n");
		PrintStream ps;
		try {
			ps = new PrintStream(path);
			for (String i : tokens_String) {
				ps.println(i);
			}
			ps.close();
		} catch (IOException e) {
		}
	}
	
	private String run(String command) {
		Runtime cmd_Runtime = Runtime.getRuntime();
		try {
			Process cmdReturn_Process = cmd_Runtime.exec(command);
			InputStream inputStream = cmdReturn_Process.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			String out = "";
			while ((line = bufferedReader.readLine()) != null) {
				out += line + "\n";
			}
			return out;
		} catch (IOException e1) {
			return "error";
		}
	}
	
	private boolean sc(String text) {
		return text != null && !(text.trim().equalsIgnoreCase(""));
	}
	
	/**
	 * Gets the notification's currently set title.
	 * @return The notification's title
	 * @see Notification#setTitle(String)
	 * */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the notification's title.
	 * @param title The title to set the notification to
	 * @see Notification#getTitle() 
	 * */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the notification's currently set text.
	 * @return The notification's text
	 * @see Notification#setText(String)
	 * */
	public String getText() {
		return text;
	}
	
	/**
	 * Sets the notification's text content.
	 * @param text The text to set the notification to
	 * @see org.robockets.runqueue.client.notifications.n9mtq4.demo.notifcationmac.Notification#getText() 
	 * */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Gets the notification's currently set subtitle.
	 * @return The notification's subtitle
	 * @see Notification#setSubtitle(String) 
	 * */
	public String getSubtitle() {
		return subtitle;
	}
	
	/**
	 * Sets the notification's subtitle.
	 * @param subtitle The subtitle to set the notification to
	 * @see org.robockets.runqueue.client.notifications.n9mtq4.demo.notifcationmac.Notification#getSubtitle()
	 * */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	
	/**
	 * Gets the notification's currently set sound's name.
	 * @return The name of the sound that will play when the notification displays
	 * @see Notification#setSoundName(String) 
	 * */
	public String getSoundName() {
		return soundName;
	}
	
	/**
	 * Sets the notification's sound.
	 * @param soundName The sound's name that will play when the notification displays
	 * @see org.robockets.runqueue.client.notifications.n9mtq4.demo.notifcationmac.Notification#getSoundName()
	 * */
	public void setSoundName(String soundName) {
		this.soundName = soundName;
	}
	
}
