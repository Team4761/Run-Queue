package org.robockets.runqueue.client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listener that listens for mouse clicks. Bound to {@link Notification#frame}.
 */
public class NotificationClickListener extends MouseAdapter {
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			System.exit(0);
		}
	}
}
