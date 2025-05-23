package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import util.ReferenceFinder;
import view.MyFrame;
import view.LocationWindow;

public class AddLocationListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

		final Component item = (Component) e.getSource();
		final MyFrame frame = (MyFrame) ReferenceFinder.findFrame(item);

		@SuppressWarnings("unchecked")
		JList<String> locationList = frame.getLocationList();
		DefaultListModel<String> listModel = (DefaultListModel<String>) locationList.getModel();

		new LocationWindow(listModel);
	}
}
