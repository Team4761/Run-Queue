package org.robockets.runqueue.client.notifications;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SwingNotification {
	private String title;
	private String message;
	
	static JFrame frame;
	JLabel titleLabel;
	JLabel messageLabel;
	JPanel panel;
	GridBagLayout layout;
	GridBagConstraints gbc;
	
	/**
	 * Constructor for notifications. Make an instance of one of these then run
	 * {@link #show()}.
	 * @param title Title for the notification (example: "Run Queue Client")
	 * @param message Message you would like the user to recieve (example: "Something is broken!")
	 */
	public SwingNotification(String title, String message) {
		this.title = title;
		this.message = message;
	}
	
	/**
	 * Set up the GUI.
	 */
	private void prepare () {
		frame = new JFrame("RQC Notification");
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.addMouseListener(new MouseClickListener());
		frame.setPreferredSize(new Dimension(200, 60));
		
		titleLabel = new JLabel(title, JLabel.LEFT);
		messageLabel = new JLabel(message, JLabel.LEFT);
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, -75, 2, 0);
		panel.add(titleLabel, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, -75, 10, 0);
		panel.add(messageLabel, gbc);
		
		frame.add(panel);
		frame.setSize(200, 60);
	}
	
	/**
	 * Show the notification you prepared on the users desktop. This method
	 * calculates where the frame ought to be and then sets the frame to
	 * visible.
	 */
	public void show () {
		prepare();
		Dimension size = frame.getPreferredSize();
		int padding = 15;
		frame.setLocation(getScreenWidth() - padding - size.width, size.height + padding);
		frame.setVisible(true);
	}
	
	/**
	 * Just hides the notification. Used in MouseClickListener to
	 * clear the notification
	 */
	public static void clear () {
		frame.setVisible(false);
	}
	
	/**
	 * @return Width of screen in pixels
	 */
	private int getScreenWidth () {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	/**
	 * @return Height of screen in pixels
	 */
	@SuppressWarnings("unused")
	private int getScreenHeight () {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
