package org.robockets.runqueue.client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notification {
	private String title;
	private String message;
	
	JFrame frame;
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
	public Notification(String title, String message) {
		this.title = title;
		this.message = message;
	}
	
	/**
	 * Set up the GUI.
	 */
	private void prepare() {
		frame = new JFrame("RQC Notification");
		frame.setLayout(new GridLayout(2, 1));
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.addMouseListener(new MouseClickListener());
		//frame.setMinimumSize(new Dimension(150, 10));
		
		titleLabel = new JLabel(title, JLabel.LEFT);
		messageLabel = new JLabel(message, JLabel.LEFT);
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(titleLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(messageLabel, gbc);
		
		frame.add(panel);
		frame.pack();
	}
	
	/**
	 * Show the notification you prepared on the users desktop. This method
	 * calculates where the frame ought to be and then sets the frame to
	 * visible.
	 */
	public void show() {
		prepare();
		Dimension size = frame.getPreferredSize();
		int padding = 15;
		frame.setLocation(getScreenWidth() - padding - size.width, padding);
		frame.setVisible(true);
	}
	
	/**
	 * @return Width of screen in pixels
	 */
	private int getScreenWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	/**
	 * @return Height of screen in pixels
	 */
	private int getScreenHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
