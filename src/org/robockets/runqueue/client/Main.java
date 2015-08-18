package org.robockets.runqueue.client;

import javax.swing.SwingUtilities;

import org.robockets.runqueue.client.controllers.Controllers;


public class Main {
	
	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				Controllers.main.show();
			}
		});
	}
}