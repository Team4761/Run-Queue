package org.robockets.runqueue.client.notifications;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Listener that listens for mouse1 clicks. Bound to {@link SwingNotification#frame}.
 */
public class MouseClickListener extends MouseAdapter {
	public void mouseClicked (MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			System.exit(0);
		}
	}
}
