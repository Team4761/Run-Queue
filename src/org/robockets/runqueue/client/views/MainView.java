package org.robockets.runqueue.client.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import org.robockets.runqueue.client.MainWindowListener;
import org.robockets.runqueue.client.models.QueueModel;

public class MainView {
	private final int WIDTH = 500, HEIGHT = 280;
	private final int ROWS = 2, COLUMNS = 1;
	private final int BUTTON_WIDTH = 100;
	private final int SECOND_LINE_OFFSET = 50;
	
	private String[] priorities = {"Critical", "High", "Medium", "Low"};
	
	private JFrame jFrame;
	private Font font = new Font("Arial", Font.PLAIN, 30);
	public JLabel usernameLabel, positionLabel;
	
	private QueueModel queueModel;
		
	public MainView (QueueModel queueModel) {
		this.queueModel = queueModel;
		this.jFrame = this.setupJFrame();
		this.setupWindow();
	}
	
	private JFrame setupJFrame () {
        try { // Nimbus theme
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        	System.out.println(e);
        }
		
		JFrame jFrame = new JFrame("Robo Run-Queue Client");
		jFrame.setSize(WIDTH, HEIGHT);
		jFrame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new GridLayout(ROWS, COLUMNS));
		
		return jFrame;
	}
	
	private void setupWindow () {
		ActionListener actionListener = new MainWindowListener();
		
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
		
	    usernameLabel = new JLabel("USERNAME");
	    font = font.deriveFont(40f);
	    usernameLabel.setFont(font);
		c.gridx = 3;
		c.gridy = 0;
		c.ipadx = 0;
		c.anchor = GridBagConstraints.LINE_END;
	    topPanel.add(usernameLabel, c);
	    
	    JButton enableButton = new JButton("Enable");
	    enableButton.setActionCommand("ENABLE_BUTTON");
	    enableButton.addActionListener(actionListener);
	    c.gridx = 0;
	    c.gridy = 3;
	    c.ipadx = BUTTON_WIDTH / 3;
	    c.anchor = GridBagConstraints.LINE_START;
	    c.insets = new Insets(SECOND_LINE_OFFSET, 0, 0, 0);
	    topPanel.add(enableButton, c);
	    
	    JButton disableButton = new JButton("Disable");
	    disableButton.setActionCommand("DISABLE_BUTTON");
	    disableButton.addActionListener(actionListener);
	    c.gridx = 0;
	    c.gridy = 3;
	    c.ipadx = BUTTON_WIDTH / 3;
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
	    priorityDropdown.setActionCommand("PRIORITY_DROPDOWN");
	    priorityDropdown.addActionListener(actionListener);
	    c.gridx = 3;
		c.gridy = 3;
		c.ipadx = 50;
		c.insets = new Insets(SECOND_LINE_OFFSET, 50, 0, 2);
		c.anchor = GridBagConstraints.LINE_END;
		topPanel.add(priorityDropdown, c);
		
		// Bottom half of the screen
		JTable queueTable = new JTable(this.queueModel);
		queueTable.setCellSelectionEnabled(false);
		queueTable.getTableHeader().setReorderingAllowed(false);
		queueTable.getTableHeader().setResizingAllowed(false);
		
		JScrollPane queuePane = new JScrollPane(queueTable);
		queuePane.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		jFrame.add(topPanel);
		jFrame.add(queuePane);
		jFrame.pack();
	}
	
	public void display () {
		jFrame.setVisible(true);
	}
}