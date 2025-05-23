package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.DataStore;
import Model.IDataStore;
import util.ReferenceFinder;
import view.MyFrame;

public class TakeListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		final Component item = (Component) e.getSource();
		final MyFrame frame = (MyFrame) ReferenceFinder.findFrame(item);

		@SuppressWarnings("unchecked")
		final JList<String> listLocation = frame.getLocationList();
		@SuppressWarnings("unchecked")
		final JList<String> listTime = frame.getTimeList();
		final JTable table = frame.getTable();

		int i = listLocation.getSelectedIndex();
		int j = listTime.getSelectedIndex();

		if (i > -1 && j > -1) {
			String selectedLocation = listLocation.getSelectedValue();
			String selectedTime = listTime.getSelectedValue();

			DataStore dataStore = new DataStore();
			int locationId = dataStore.getLocationId(selectedLocation);
			int timeId = dataStore.getTimeId(selectedTime);

			if (locationId > 0 && timeId > 0) {

				ArrayList<Appointment> appointments = new ArrayList<>();
				appointments.add(new Appointment(locationId, timeId));
				dataStore.saveapp(appointments, IDataStore.table);

				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				tableModel.addRow(new Object[] { selectedLocation, selectedTime });
			}
		}
	}

}
