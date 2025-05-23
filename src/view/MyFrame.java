package view;

import java.awt.*;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.Location;
import Model.Time;
import controller.AddLocationListener;
import controller.AddTimeListener;
import controller.AppointmentLoader;
import controller.LocationLoader;
import controller.TakeListener;
import controller.TimeLoader;
import ressource.FrameConstants;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 2052515348188602777L;

	// menu bar
	private MyMenu myMenuBar;

	// references
	private JList<String> thisLocationList;
	private JList<String> thisTimeList;
	private JTable thisTable;

	// frame
	public MyFrame() {
		super(FrameConstants.Frame_Title);

		thisTable = new JTable(
				new DefaultTableModel(new String[] { FrameConstants.Table_Location, FrameConstants.Table_Time }, 0));
		thisLocationList = new JList<>(new DefaultListModel<>());
		thisTimeList = new JList<>(new DefaultListModel<>());

		this.myMenuBar = new MyMenu(thisTable, thisLocationList, thisTimeList);
		this.setJMenuBar(this.myMenuBar);

		this.add(createContent());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
	}

	private JPanel createContent() {
		final JPanel content = new JPanel(new GridLayout(1, 4, 5, 5));
		content.setBackground(new Color(255, 255, 255));

		content.add(createContentLeft());
		content.add(createContentMiddleLeft());
		content.add(createContentMiddleRight());
		content.add(createContentRight());

		return content;
	}

	// Left Panel
	private JPanel createContentLeft() {
		final JPanel contentLeft = new JPanel(new BorderLayout(5, 5));

		setTable(thisTable);

		// Data
		final DefaultTableModel tableModel = (DefaultTableModel) thisTable.getModel();

		Iterator<Appointment> data = new AppointmentLoader();
		while (data.hasNext()) {
			tableModel.addRow(data.next().toStringArray());
		}

		// Scroll pane
		final JScrollPane scrollTable = new JScrollPane(thisTable);
		scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		contentLeft.add(scrollTable, BorderLayout.CENTER);
		return contentLeft;
	}

	// Middle Left Panel
	private JPanel createContentMiddleLeft() {
		final JPanel contentMiddleLeft = new JPanel(new BorderLayout(5, 5));
		contentMiddleLeft.setBackground(new Color(255, 255, 255));

		// Button
		final JButton button = new JButton(FrameConstants.Take);
		button.addActionListener(new TakeListener());

		contentMiddleLeft.add(button, BorderLayout.NORTH);
		return contentMiddleLeft;
	}

	// Middle Right Panel
	private JPanel createContentMiddleRight() {
		final JPanel contentMiddleRight = new JPanel(new BorderLayout(5, 5));
		contentMiddleRight.setBackground(new Color(255, 255, 255));

		// Create List
		final JList<String> locationList = new JList<String>(new DefaultListModel<String>());

		setLocationList(locationList);

		// Add Data
		final DefaultListModel<String> listModel = (DefaultListModel<String>) locationList.getModel();

		Iterator<Location> data = new LocationLoader();
		while (data.hasNext()) {
			listModel.addElement(data.next().getLocation());
		}

		// Scroll pane
		JScrollPane scrollLocation = new JScrollPane(locationList);
		scrollLocation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollLocation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Add Button
		final JButton btnAddLocation = new JButton(FrameConstants.Add_Location);
		btnAddLocation.addActionListener(new AddLocationListener());

		contentMiddleRight.add(scrollLocation, BorderLayout.CENTER);
		contentMiddleRight.add(btnAddLocation, BorderLayout.SOUTH);

		return contentMiddleRight;
	}

	// Right Panel
	private JPanel createContentRight() {
		final JPanel contentRight = new JPanel(new BorderLayout(5, 5));
		contentRight.setBackground(new Color(255, 255, 255));

		// Create List
		final JList<String> timeList = new JList<String>(new DefaultListModel<String>());

		setTimeList(timeList);

		// Add data
		final DefaultListModel<String> listModel = (DefaultListModel<String>) timeList.getModel();

		Iterator<Time> data = new TimeLoader();
		while (data.hasNext()) {
			listModel.addElement(data.next().getTime());
		}

		// Scroll pane
		JScrollPane scrollTime = new JScrollPane(timeList);
		scrollTime.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTime.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Add Button
		final JButton btnAddTime = new JButton(FrameConstants.Add_Time);
		btnAddTime.addActionListener(e -> {
			JList<String> list = getTimeList();
			DefaultListModel<String> defaultlistModel = (DefaultListModel<String>) list.getModel();
			new TimeWindow(defaultlistModel);
		});

		contentRight.add(scrollTime, BorderLayout.CENTER);
		contentRight.add(btnAddTime, BorderLayout.SOUTH);

		return contentRight;
	}

	public JList getLocationList() {
		return thisLocationList;
	}

	public void setLocationList(JList list) {
		thisLocationList = list;
	}

	public JList getTimeList() {
		return thisTimeList;
	}

	public void setTimeList(JList list) {
		thisTimeList = list;
	}

	public JTable getTable() {
		return thisTable;
	}

	public void setTable(JTable table) {
		thisTable = table;
	}

	public static void main(String[] args) {
		new MyFrame();
	}
}
