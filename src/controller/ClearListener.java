package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.DataStore;

public class ClearListener implements ActionListener {

	private JTable table;

	public ClearListener(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DataStore dataStore = new DataStore();
		dataStore.clearAllData();

		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		table.repaint();
	}
}
