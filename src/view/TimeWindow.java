package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Model.DataStore; // Import DataStore to save to the database
import Model.IDataStore;
import Model.Time; // Import Time class for the formatted time
import ressource.AddConstants;

public class TimeWindow extends JFrame {
	private static final long serialVersionUID = 2786535597029950022L;

	private JComboBox<String> yearDropdown;
	private JComboBox<String> monthDropdown;
	private JTextField dayField;
	private JSpinner hourSpinner;
	private JSpinner minuteSpinner;
	private DefaultListModel<String> listModel;

	public TimeWindow(DefaultListModel<String> listModel) {
		super("Add Time/Date");
		this.listModel = listModel;

		setSize(300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Year
		JLabel yearLabel = new JLabel(AddConstants.Year_Label);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(yearLabel, gbc);

		String[] years = { "2024", "2025", "2026", "2027", "2028", "2029", "2030" };
		yearDropdown = new JComboBox<>(years);
		gbc.gridx = 1;
		add(yearDropdown, gbc);

		// Month
		JLabel monthLabel = new JLabel(AddConstants.Month_Label);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(monthLabel, gbc);

		String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		monthDropdown = new JComboBox<>(months);
		gbc.gridx = 1;
		add(monthDropdown, gbc);

		// Day
		JLabel dayLabel = new JLabel(AddConstants.Day_Label);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(dayLabel, gbc);

		dayField = new JTextField("01", 2);
		gbc.gridx = 1;
		add(dayField, gbc);

		// Time
		JLabel timeLabel = new JLabel(AddConstants.Time_Label);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(timeLabel, gbc);

		JPanel timePanel = new JPanel();
		hourSpinner = new JSpinner(new SpinnerNumberModel(12, 0, 23, 1));
		minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		timePanel.add(hourSpinner);
		timePanel.add(new JLabel(":"));
		timePanel.add(minuteSpinner);

		gbc.gridx = 1;
		add(timePanel, gbc);

		// Button
		JPanel buttonPanel = new JPanel();

		JButton applyButton = new JButton(AddConstants.Apply);
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dateTime = getSelectedDateTime();

				try {
					int dayInt = Integer.parseInt(dayField.getText());
					if (dayInt < 1 || dayInt > 31) {
						JOptionPane.showMessageDialog(null, AddConstants.Days_Error);
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, AddConstants.Numeric_Error);
					return;
				}

				Time time = new Time(dateTime);

				// Save to database
				DataStore dataStore = new DataStore();
				ArrayList<Time> timeList = new ArrayList<>();
				timeList.add(time);
				dataStore.savetim(timeList, IDataStore.timeList);

				listModel.addElement(dateTime);

				dispose();
			}
		});
		buttonPanel.add(applyButton);

		// Cancel button
		JButton cancelButton = new JButton(AddConstants.Cancel);
		cancelButton.addActionListener(e -> dispose());
		buttonPanel.add(cancelButton);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		add(buttonPanel, gbc);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public String getSelectedDateTime() {
		String year = (String) yearDropdown.getSelectedItem();
		String month = (String) monthDropdown.getSelectedItem();
		String day = dayField.getText();
		int hour = (Integer) hourSpinner.getValue();
		int minute = (Integer) minuteSpinner.getValue();

		if (day.length() == 1) {
			day = "0" + day;
		}

		return String.format("%s-%s-%s %02d:%02d:00", year, month, day, hour, minute);
	}
}
