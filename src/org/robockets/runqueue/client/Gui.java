package org.robockets.runqueue.client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.*;

public class Gui {
	private final int WIDTH = 500, HEIGHT = 350;
	private final int ROWS = 2, COLUMNS = 1;
	private final int BUTTON_HEIGHT = 30;
	
	private String[] priorities = {"Critical", "High", "Medium", "Low"};
	public String username = "USERNAME";
	public String queuePosition = "x";
	public String queueSize = "x";
	
	private JComboBox<String> priorityDropdown;
	private JLabel positionLabel, usernameLabel;
	private JButton enableButton, disableButton;
	private JTable queueTable;
	private Font font = new Font("Arial", Font.PLAIN, 30);
		
	private JFrame setupJFrame () {
		JFrame jFrame = new JFrame("Robo Run-Queue Client");
		jFrame.setSize(WIDTH, HEIGHT);
		jFrame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new GridLayout(ROWS, COLUMNS));
		
		return jFrame;
	}
	
	Gui () {
		// Set nice looking Nimbus theme
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            //  If Nimbus is not available, you can set the GUI to another look and feel.
        }
        
		JFrame jFrame = setupJFrame();
		
		JPanel topPanel = new JPanel();
		topPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT / 2));
		topPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		positionLabel = new JLabel("Position");
		positionLabel.setFont(font);
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 100;
		c.anchor = GridBagConstraints.PAGE_START;
	    topPanel.add(positionLabel, c);
		
	    usernameLabel = new JLabel(username);
	    font = font.deriveFont(40f);
	    usernameLabel.setFont(font);
		c.gridx = 3;
		c.gridy = 0;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.PAGE_END;
	    topPanel.add(usernameLabel, c);
	    
	    enableButton = new JButton("Enable");
	    enableButton.setPreferredSize(new Dimension(100, BUTTON_HEIGHT));
	    c.gridx = 0;
	    c.gridy = 3;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.insets = new Insets(50, 0, 0, 0);
	    topPanel.add(enableButton, c);
	    
	    disableButton = new JButton("Disable");
	    disableButton.setPreferredSize(new Dimension(100, BUTTON_HEIGHT));
	    c.gridx = 0;
	    c.gridy = 3;
	    c.insets = new Insets(50, 100, 0, 30);
	    c.anchor = GridBagConstraints.LINE_START;
	    topPanel.add(disableButton, c);

	    JLabel priorityLabel = new JLabel("Priority: ");
	    c.gridx = 3;
	    c.gridy = 3;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.insets = new Insets(50, 30, 0, 0);
	    topPanel.add(priorityLabel, c);
	    
	    priorityDropdown = new JComboBox<String>(priorities);
	    priorityDropdown.setSelectedIndex(2);
	    priorityDropdown.setPreferredSize(new Dimension(150, BUTTON_HEIGHT));
	    c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(50, 50, 0, 0);
		c.anchor = GridBagConstraints.LINE_END;
		topPanel.add(priorityDropdown, c);
		
		queueTable = new JTable(new QueueTable());
		queueTable.setCellSelectionEnabled(false);
		
		JScrollPane queuePane = new JScrollPane(queueTable);
		queuePane.setMaximumSize(new Dimension(WIDTH, HEIGHT / 2));
		
		jFrame.add(topPanel);
		jFrame.add(queuePane);
		jFrame.pack();
		jFrame.setVisible(true);
	}
}