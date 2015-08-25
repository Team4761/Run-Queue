package org.robockets.runqueue.client.views;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.robockets.runqueue.client.controllers.Controllers;

public class UsernamePopupView implements ActionListener {
	private final int WIDTH = 300, HEIGHT = 100;

	public JTextField usernameField;
	private JFrame jFrame;
	
	public UsernamePopupView () {
		this.jFrame = this.setupJFrame();
		this.setupWindow();
	}
	
	/**
	 * Create the JFrame to be used for this view
	 * 
	 * @return the JFrame
	 */
	private JFrame setupJFrame () {
		JFrame jFrame = new JFrame("Run-Queue Client");
		jFrame.setBounds(new Rectangle((getScreenWidth() / 2) - (WIDTH / 2), (getScreenHeight() / 2) - (HEIGHT / 2), WIDTH, HEIGHT));
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new GridBagLayout());
		
		return jFrame;
	}
	
	/**
	 * Setup all the components to be displayed in this view
	 */
	private void setupWindow () {
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel promptLabel = new JLabel("Enter a username:");
		Font font = new Font("Arial", Font.PLAIN, 14);
		promptLabel.setFont(font);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 5, 0);
		jFrame.add(promptLabel, c);
		
		usernameField = new JTextField(20);
		usernameField.setActionCommand("ENTER_TEXTFIELD");
		usernameField.addActionListener(this);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipadx = 175;
		c.insets = new Insets(0, 0, 0, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		jFrame.add(usernameField, c);
		
		JButton usernameButton = new JButton("Continue");
		usernameButton.setActionCommand("ENTER_BUTTON");
		usernameButton.addActionListener(this);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.ipadx = 15;
		c.fill = GridBagConstraints.HORIZONTAL;
		jFrame.add(usernameButton, c);
	}
	
	/**
	 * Display the JFrame
	 */
	public void display () {
		jFrame.setVisible(true);
	}
	
	/**
	 * Hide the JFrame
	 */
	public void hide () {
		jFrame.setVisible(false);
	}
	
	/**
	 * Gets called whenever the state of a component changes. Ex. button presses
	 */
	public void actionPerformed (ActionEvent e) {
		if (e.getActionCommand().equals("ENTER_BUTTON")) {
			Controllers.usernamePopup.submit();
		} else if (e.getActionCommand().equals("ENTER_TEXTFIELD")) {
			Controllers.usernamePopup.submit();
		}
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
	private int getScreenHeight () {
	    return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
	}
}
