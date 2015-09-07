package org.robockets.runqueue.client;

import org.robockets.runqueue.client.models.Models;

public class LogicLoop extends Thread {
	
	public void run () {
		while (true) {
			// TODO: Implement is running notification logic (waiting on Jared)
			
			Models.queue.refresh();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
