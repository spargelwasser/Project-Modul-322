package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import Model.DataStore;

public class DeleteSelectedListener implements ActionListener {

	private JTable table;

	public DeleteSelectedListener(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int[] selectedRows = table.getSelectedRows();
		if (selectedRows.length > 0) {
			DataStore dataStore = new DataStore();
			javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) table.getModel();

			for (int i = selectedRows.length - 1; i >= 0; i--) {
				int selectedRow = selectedRows[i];
				String location = table.getValueAt(selectedRow, 0).toString();
				String time = table.getValueAt(selectedRow, 1).toString();

				dataStore.deleteSelectedAppointment(location, time);
				model.removeRow(selectedRow);
			}
		}
	}
}
