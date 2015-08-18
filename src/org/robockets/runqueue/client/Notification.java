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
	
	public Notification(String title, String message) {
		this.title = title;
		this.message = message;
	}
	
	private void prepare() {
		frame = new JFrame("RQC Notification");
		frame.setLayout(new GridLayout(2, 1));
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.addMouseListener(new NotificationClickListener());
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
	
	public void show() {
		prepare();
		Dimension size = frame.getPreferredSize();
		int padding = 15;
		frame.setLocation(getScreenWidth() - padding - size.width, padding);
		frame.setVisible(true);
	}
	
	private int getScreenWidth() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
	}

	private int getScreenHeight() {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
