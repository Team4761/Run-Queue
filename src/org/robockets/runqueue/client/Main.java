package org.robockets.runqueue.client;

import javax.swing.SwingUtilities;

import org.robockets.runqueue.client.models.QueueModel;
import org.robockets.runqueue.client.views.MainView;

public class Main {

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				new MainView(new QueueModel()).display();
			}
		});
	}
}