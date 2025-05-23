package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import view.TimeWindow;

public class AddTimeListener implements ActionListener {

	private TimeWindow timeWindow;
	private DefaultListModel<String> listModel;

	public AddTimeListener(TimeWindow timeWindow, DefaultListModel<String> listModel) {
		this.timeWindow = timeWindow;
		this.listModel = listModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String dateTime = timeWindow.getSelectedDateTime();

		listModel.addElement(dateTime);

		timeWindow.dispose();
	}
}
