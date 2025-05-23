package view;

import javax.swing.*;
import java.awt.*;
import Model.DataStore;
import Model.IDataStore;
import Model.Location;
import ressource.AddConstants;

import java.util.ArrayList;

public class LocationWindow extends JFrame {
	private static final long serialVersionUID = -7491557329413001264L;
	@SuppressWarnings("unused")
	private DefaultListModel<String> listModel;

	public LocationWindow(DefaultListModel<String> listModel) {
		super("Add Location");

		this.listModel = listModel;
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel label = new JLabel(AddConstants.Location_Label);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);

		JTextField textField = new JTextField(15);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(textField, gbc);

		JPanel buttonPanel = new JPanel();

		JButton applyButton = new JButton(AddConstants.Apply);
		applyButton.addActionListener(e -> {
			String location = textField.getText().trim();
			if (!location.isEmpty()) {

				Location newLocation = new Location(location);

				ArrayList<Location> locations = new ArrayList<>();
				locations.add(newLocation);

				DataStore dataStore = new DataStore();
				dataStore.saveloc(locations, IDataStore.locationList);

				listModel.addElement(location);

				dispose();
			} else {
				JOptionPane.showMessageDialog(this, AddConstants.Location_Error, AddConstants.Error,
						JOptionPane.ERROR_MESSAGE);
			}
		});

		buttonPanel.add(applyButton);

		JButton cancelButton = new JButton(AddConstants.Cancel);
		cancelButton.addActionListener(e -> dispose());
		buttonPanel.add(cancelButton);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		add(buttonPanel, gbc);

		setLocationRelativeTo(null);
		setVisible(true);
	}
}
