package org.robockets.runqueue.client.models;

import javax.swing.table.AbstractTableModel;

/**
 * The data the will be displayed to the user
 * This might be changed to a queue object
 */
public class QueueModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L; // Eclipse really wants me to have this
	private String[] queueColumns = {"#", "Username", "Priority", "Run/Wait Time"};
	private String[][] queue = {{"1", "USERNAME", "Medium", "0s"}, {"2", "USERNAME", "Medium", "0s"}};
	
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
		return queue[rowIndex][columnIndex];
	}
	
	/**
	 * Only JScrollPane calls this method while constructing the table
	 */
	@Override
	public String getColumnName (int column) {
		return queueColumns[column];
	}

	/**
	 * Set a specific value to be displayed in the table
	 * 
	 * @param value the string to be put into the table
	 * @param rowIndex the row that 'value' will be placed
	 * @param columnIndex the column that 'value' will be placed
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		queue[rowIndex][columnIndex] = (String) value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
	
	/**
	 * Set the entire data in the queue
	 * 
	 * @param queue the new data to be displayed
	 */
	public void setQueue(String[][] queue) {
		this.queue = queue;
	}
}
