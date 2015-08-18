package org.robockets.runqueue.client;

import javax.swing.SwingUtilities;

import org.robockets.runqueue.client.controllers.MainController;

public class Main {
	// Controller declarations
	public static MainController mainController = new MainController();
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				mainController.show();
			}
		});
	}
}