package org.robockets.runqueue.client.models;

import javax.swing.table.AbstractTableModel;

public class QueueModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L; // Eclipse really wants me to have this
	private String[] queueColumns = {"#", "Username", "Priority", "Run/Wait Time"};
	private String[][] queue = {{"1", "USERNAME", "Medium", "0s"}, {"2", "USERNAME", "Medium", "0s"}};
	
	@Override
	public int getRowCount() {
		return queue.length;
	}

	@Override
	public int getColumnCount() {
		return queueColumns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return queue[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName (int column) {
		return queueColumns[column];
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		queue[rowIndex][columnIndex] = (String) value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
	
	public void setQueue(String[][] queue) {
		this.queue = queue;
	}
}
