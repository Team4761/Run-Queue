package org.robockets.runqueue.client;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.robockets.runqueue.client.controllers.Controllers;


public class Main {
	
	public static void main(String args[]) {
        try { // Nimbus theme
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		SwingUtilities.invokeLater(new Runnable () {
			public void run () {
				Controllers.main.show();
			}
		});
	}
}