package org.robockets.runqueue.client.controllers;

import org.robockets.runqueue.client.models.QueueModel;
import org.robockets.runqueue.client.views.MainView;

public class MainController {
	QueueModel queueModel;
	MainView view;
	
	public MainController () {
		this.queueModel = new QueueModel();
		this.view = new MainView(this.queueModel);
	}
	
	public void show () {
		view.display();
	}
	
	public void updateUsername (String username) {
		view.usernameLabel.setText(username);
	}
	
	public void updateQueueLength (int length) {
		String positionString = view.positionLabel.getText();
		String halfPosition = positionString.split("/")[0] + "/";
		String fullString = halfPosition + Integer.toString(length);
		view.positionLabel.setText(fullString);
	}
	
	public void updateQueuePosition (int position) {
		String positionString = view.positionLabel.getText();
		String[] positionSplit = positionString.split("/");
		String fullString = positionSplit[0].split(" ")[0] + " " + Integer.toString(position) + "/" + positionSplit[1];
		view.positionLabel.setText(fullString);
	}
}
