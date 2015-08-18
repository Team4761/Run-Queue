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
}
