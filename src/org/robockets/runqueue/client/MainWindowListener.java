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
			System.out.println(button.getText() + " button pressed!");
		} else if (e.getActionCommand().equals("DISABLE_BUTTON")) {
			JButton button = (JButton) e.getSource();
			System.out.println(button.getText() + " button pressed!");
		} else if (e.getActionCommand().equals("PRIORITY_DROPDOWN")) {
			@SuppressWarnings("unchecked") // The dropdown must be a JComboBox<String>
			JComboBox<String> dropdown = (JComboBox<String>) e.getSource();
			System.out.println("Dropdown set to " + dropdown.getSelectedItem());
		}
	}
}
