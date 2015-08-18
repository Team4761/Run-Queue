package org.robockets.runqueue.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class MainWindowListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ENABLE_BUTTON")) {
			JButton button = (JButton) e.getSource();			
			System.out.println("Enable button pressed!");
		} else if (e.getActionCommand().equals("DISABLE_BUTTON")) {
			JButton button = (JButton) e.getSource();
			System.out.println("Disable button pressed!");
		} else if (e.getActionCommand().equals("PRIORITY_DROPDOWN")) {
			JComboBox<String> dropdown = (JComboBox<String>) e.getSource();
			System.out.println(dropdown.getSelectedItem());
		}
	}
}
