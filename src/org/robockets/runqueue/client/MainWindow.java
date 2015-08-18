package org.robockets.runqueue.client;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow {
	private final int WIDTH = 500, HEIGHT = 280;
	private final int ROWS = 2, COLUMNS = 1;
	private final int BUTTON_WIDTH = 100, BUTTON_HEIGHT = 30;
	private final int SECOND_LINE_OFFSET = 50;
	
	private String[] priorities = {"Critical", "High", "Medium", "Low"};
	public String username = "USERNAME";
	public String queuePosition = "x";
	public String queueSize = "x";
	
	private Font font = new Font("Arial", Font.PLAIN, 30);
		
	private JFrame setupJFrame () {
        try { // Nimbus theme
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
		
		JFrame jFrame = new JFrame("Robo Run-Queue Client");
		jFrame.setSize(WIDTH, HEIGHT);
		jFrame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new GridLayout(ROWS, COLUMNS));
		
		return jFrame;
	}
	
	MainWindow () {
		JFrame jFrame = setupJFrame();
		ActionListener actionListener = new MainWindowListener();
		
		// Top half of the screen
		JPanel topPanel = new JPanel();
		topPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		topPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel positionLabel = new JLabel("Position");
		positionLabel.setFont(font);
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 100;
		c.anchor = GridBagConstraints.LINE_START;
	    topPanel.add(positionLabel, c);
		
	    JLabel usernameLabel = new JLabel(username);
	    font = font.deriveFont(40f);
	    usernameLabel.setFont(font);
		c.gridx = 3;
		c.gridy = 0;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.LINE_END;
	    topPanel.add(usernameLabel, c);
	    
	    JButton enableButton = new JButton("Enable");
	    enableButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
	    enableButton.setActionCommand("ENABLE_BUTTON");
	    enableButton.addActionListener(actionListener);
	    c.gridx = 0;
	    c.gridy = 3;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.insets = new Insets(SECOND_LINE_OFFSET, 0, 0, 0);
	    topPanel.add(enableButton, c);
	    
	    JButton disableButton = new JButton("Disable");
	    disableButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
	    disableButton.setActionCommand("DISABLE_BUTTON");
	    disableButton.addActionListener(actionListener);
	    c.gridx = 0;
	    c.gridy = 3;
	    c.insets = new Insets(SECOND_LINE_OFFSET, BUTTON_WIDTH, 0, 30);
	    c.anchor = GridBagConstraints.LINE_START;
	    topPanel.add(disableButton, c);

	    JLabel priorityLabel = new JLabel("Priority: ");
	    c.gridx = 3;
	    c.gridy = 3;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.insets = new Insets(SECOND_LINE_OFFSET, 30, 0, 0);
	    topPanel.add(priorityLabel, c);
	    
	    JComboBox<String> priorityDropdown = new JComboBox<String>(priorities);
	    priorityDropdown.setSelectedIndex(2);
	    priorityDropdown.setPreferredSize(new Dimension(150, BUTTON_HEIGHT));
	    priorityDropdown.setActionCommand("PRIORITY_DROPDOWN");
	    priorityDropdown.addActionListener(actionListener);
	    c.gridx = 3;
		c.gridy = 3;
		c.insets = new Insets(SECOND_LINE_OFFSET, 50, 0, 0);
		c.anchor = GridBagConstraints.LINE_END;
		topPanel.add(priorityDropdown, c);
		
		// Bottom half of the screen
		JTable queueTable = new JTable(new QueueTable());
		queueTable.setCellSelectionEnabled(false);
		
		JScrollPane queuePane = new JScrollPane(queueTable);
		queuePane.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		jFrame.add(topPanel);
		jFrame.add(queuePane);
		jFrame.pack();
		jFrame.setVisible(true);
	}
}