package org.robockets.runqueue.client;

import javax.swing.table.AbstractTableModel;

public class QueueTable extends AbstractTableModel {
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
