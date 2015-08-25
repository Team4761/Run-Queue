package org.robockets.runqueue.client;

import javax.swing.SwingUtilities;

import org.robockets.runqueue.client.controllers.Controllers;

import com.alee.laf.WebLookAndFeel;

public class Main {
	
	public static void main(String args[]) {
		WebLookAndFeel.install();
		
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				Controllers.usernamePopup.show();
			}
		});
	}
}