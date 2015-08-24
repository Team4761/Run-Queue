package org.robockets.runqueue.client.controllers;

import org.robockets.runqueue.client.views.UsernamePopupView;

public class UsernamePopupController {
	private UsernamePopupView view;
	
	public UsernamePopupController () {
		this.view = new UsernamePopupView();
	}
	
	/**
	 * Called to display the username popup view
	 */
	public void show () {
		view.display();
	}
	
	/**
	 * Called to hide the username popup view
	 */
	public void hide () {
		view.hide();
	}
	
	public void submit () {
		Controllers.main.updateUsername(view.usernameField.getText());
		hide();
		Controllers.main.show();
	}
}
