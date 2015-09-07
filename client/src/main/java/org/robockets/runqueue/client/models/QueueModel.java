package org.robockets.runqueue.client.models;

import javax.swing.table.AbstractTableModel;

/**
 * The data the will be displayed to the user
 * This might be changed to a queue object
 */
public class QueueModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L; // Eclipse really wants me to have this
	private String[] queueColumns = {"#", "Username", "Priority", "Run/Wait Time"};
	private User[] queue = {new User("USERNAME", "Medium", "2:00:00"), new User("USERNAME", "Medium", "2:05:00")};
	
	/**
	 * Only JTable calls this method while constructing the table
	 */
	@Override
	public int getRowCount() {
		return queue.length;
	}

	/**
	 * Only JTable calls this method while constructing the table
	 */
	@Override
	public int getColumnCount() {
		return queueColumns.length;
	}

	/**
	 * Only JTable calls this method while constructing the table
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return rowIndex + 1;
		} else if (columnIndex == 1) {
			return queue[rowIndex].getUsername();
		} else if (columnIndex == 2) {
			return queue[rowIndex].getPriority();
		} else {
			return queue[rowIndex].getStringWaitTime();
		}
	}
	
	/**
	 * Only JScrollPane calls this method while constructing the table
	 */
	@Override
	public String getColumnName (int column) {
		return queueColumns[column];
	}
	
	/**
	 * Set the entire data in the queue
	 * 
	 * @param queue the new data to be displayed
	 */
	public void setQueue (User[] queue) {
		this.queue = queue;
	}
	
	public void refresh () {
		this.fireTableDataChanged();
	}
	
	public void updateUser (int rowIndex, User user) {
		queue[rowIndex] = user;
	}
	
	public void updateUserByUsername (User user) {
		for (int i = 0; i < queue.length; i++) {
			if (queue[i].getUsername().equals(user.getUsername())) {
				queue[i] = user;
			}
		}
	}
}
