package org.robockets.runqueue.client.controllers;

import javax.swing.JTable;

import org.robockets.runqueue.client.models.Models;
import org.robockets.runqueue.client.views.MainView;

/**
 * Controller for the MainView view
 */
public class MainController {
	private MainView view;
	
	public MainController () {
		this.view = new MainView(Models.queue);
	}
	
	/**
	 * Called to display the main view
	 */
	public void show () {
		view.display();
	}
	
	public void updateTable () {
		view.queueTable = new JTable(view.queueModel);
	}
	
	/**
	 * Used to update the usernameLabel on the top right of the main view
	 * 
	 * @param username the user's username
	 */
	public void updateUsername (String username) {
		view.usernameLabel.setText(username);
	}
	
	/**
	 * Used to update the length of the queue that is displayed by the positionLabel
	 * 
	 * @param length the length of the queue
	 */
	public void updateQueueLength (int length) {
		String positionString = view.positionLabel.getText();
		String halfPosition = positionString.split("/")[0] + "/";
		String fullString = halfPosition + Integer.toString(length);
		view.positionLabel.setText(fullString);
	}
	
	/**
	 * Used to update the position of the user in the queue that is displayed
	 * by the positionLabel
	 * 
	 * @param position the position of the user in the queue
	 */
	public void updateQueuePosition (int position) {
		String positionString = view.positionLabel.getText();
		String[] positionSplit = positionString.split("/");
		String fullString = positionSplit[0].split(" ")[0] + " " + Integer.toString(position) + "/" + positionSplit[1];
		view.positionLabel.setText(fullString);
	}
	
	public void onEnableButtonPress () {
		view.enableButton.setEnabled(false);
		view.disableButton.setEnabled(true);
	}
	
	public void onDisableButtonPress () {
		view.enableButton.setEnabled(true);
		view.disableButton.setEnabled(false);
	}
	
	public void onDropdownSelect () {
		System.out.println("Dropdown set to " + view.priorityDropdown.getSelectedItem());
	}
}
