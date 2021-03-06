package org.robockets.runqueue.client.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.robockets.runqueue.client.controllers.Controllers;
import org.robockets.runqueue.client.models.QueueModel;

public class MainView implements ActionListener {
	private final int WIDTH = 500, HEIGHT = 280;
	private final int ROWS = 2, COLUMNS = 1;
	private final int BUTTON_WIDTH = 100;
	private final int SECOND_LINE_OFFSET = 50;
	
	private String[] priorities = {"Critical", "High", "Medium", "Low"};
	
	private JFrame jFrame;
	public JButton enableButton, disableButton;
	public JComboBox<String> priorityDropdown;
	private Font font = new Font("Arial", Font.PLAIN, 30);
	public JLabel usernameLabel, positionLabel;
	public JTextField usernameField;
	public JTable queueTable;
	
	public QueueModel queueModel;
		
	public MainView (QueueModel queueModel) {
		this.queueModel = queueModel;
		this.jFrame = this.setupJFrame();
		this.setupWindow();
	}
	
	/**
	 * Create the JFrame to be used for this view
	 * 
	 * @return the JFrame
	 */
	private JFrame setupJFrame () {
		JFrame jFrame = new JFrame("Run-Queue Client");
		jFrame.setResizable(false);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new GridLayout(ROWS, COLUMNS));
		
		return jFrame;
	}
	
	/**
	 * Setup all the components to be displayed in this view
	 */
	private void setupWindow () {		
		// Top half of the screen
		JPanel topPanel = new JPanel();
		topPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		topPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		positionLabel = new JLabel("Position: 1/1");
		font = font.deriveFont(30f);
		positionLabel.setFont(font);
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 100;
		c.anchor = GridBagConstraints.LINE_START;
	    topPanel.add(positionLabel, c);
		
	    usernameField = new JTextField("USERNAME", 20);
	    font = font.deriveFont(40f);
	    usernameField.setFont(font);
	    usernameField.setMinimumSize(new Dimension(225, 47));
	    usernameField.setVisible(false);
	    usernameField.addMouseListener(new MouseAdapter () {
	    	public final void mouseExited (MouseEvent e) {
	    		String text = usernameField.getText();
	    		usernameLabel.setText(text);
	    		usernameLabel.setVisible(true);
	    		usernameField.setVisible(false);
	    		Controllers.main.updateUsername(text);
	    	}
	    });
	    c.gridx = 3;
		c.gridy = 0;
		c.ipadx = 2;
		c.anchor = GridBagConstraints.LINE_END;
	    topPanel.add(usernameField, c);
	    
	    usernameLabel = new JLabel("USERNAME");
	    font = font.deriveFont(40f);
	    usernameLabel.setFont(font);
	    usernameLabel.addMouseListener(new MouseAdapter () {
	    	public final void mouseEntered (MouseEvent e) {
	    		usernameField.setText(usernameLabel.getText());
	    		usernameLabel.setVisible(false);
	    		usernameField.setVisible(true);
	    	}
	    });
		c.gridx = 3;
		c.gridy = 0;
		c.ipadx = 2;
		c.anchor = GridBagConstraints.LINE_END;
	    topPanel.add(usernameLabel, c);
	    
	    enableButton = new JButton("Enable");
	    enableButton.setEnabled(true);
	    enableButton.setActionCommand("ENABLE_BUTTON");
	    enableButton.addActionListener(this);
	    c.gridx = 0;
	    c.gridy = 3;
	    c.ipadx = BUTTON_WIDTH / 3;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.insets = new Insets(SECOND_LINE_OFFSET, 0, 0, 0);
	    topPanel.add(enableButton, c);
	    
	    disableButton = new JButton("Disable");
	    disableButton.setEnabled(false);
	    disableButton.setActionCommand("DISABLE_BUTTON");
	    disableButton.addActionListener(this);
	    c.gridx = 0;
	    c.gridy = 3;
	    c.ipadx = BUTTON_WIDTH / 3;
	    c.insets = new Insets(SECOND_LINE_OFFSET, BUTTON_WIDTH, 0, 30);
	    c.anchor = GridBagConstraints.LINE_START;
	    topPanel.add(disableButton, c);

	    JLabel priorityLabel = new JLabel("Priority:");
	    c.gridx = 3;
	    c.gridy = 3;
	    c.ipadx = 0;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.insets = new Insets(SECOND_LINE_OFFSET, 50, 0, 0);
	    topPanel.add(priorityLabel, c);
	    
	    priorityDropdown = new JComboBox<String>(priorities);
	    priorityDropdown.setSelectedIndex(2);
	    priorityDropdown.setActionCommand("PRIORITY_DROPDOWN");
	    priorityDropdown.addActionListener(this);
	    c.gridx = 3;
		c.gridy = 3;
		c.ipadx = 50;
		c.insets = new Insets(SECOND_LINE_OFFSET, 105, 0, 0);
		c.anchor = GridBagConstraints.LINE_START;
		topPanel.add(priorityDropdown, c);
		
		// Bottom half of the screen
		queueTable = new JTable(this.queueModel);
		queueTable.setCellSelectionEnabled(false);
		queueTable.getTableHeader().setReorderingAllowed(false);
		queueTable.getTableHeader().setResizingAllowed(false);
		queueTable.setFocusable(false);
		
		JScrollPane queuePane = new JScrollPane(queueTable);
		queuePane.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		jFrame.add(topPanel);
		jFrame.add(queuePane);
		jFrame.pack();
		
		jFrame.setSize(WIDTH, HEIGHT);
	}
	
	/**
	 * Display the JFrame
	 */
	public void display () {
		jFrame.setVisible(true);
	}
	
	/**
	 * Gets called whenever the state of a component changes. Ex. button presses
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ENABLE_BUTTON")) {
			Controllers.main.onEnableButtonPress();
		} else if (e.getActionCommand().equals("DISABLE_BUTTON")) {
			Controllers.main.onDisableButtonPress();
		} else if (e.getActionCommand().equals("PRIORITY_DROPDOWN")) {
			Controllers.main.onDropdownSelect();
		}
	}
}