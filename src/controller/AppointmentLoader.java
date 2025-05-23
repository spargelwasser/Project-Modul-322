package controller;

import java.util.ArrayList;
import java.util.Iterator;

import Model.Appointment;
import Model.DataStore;
import Model.IDataStore;

public class AppointmentLoader implements Iterator<Appointment> {

	private ArrayList<Appointment> data;
	private int elm;

	public AppointmentLoader() {
		this.data = new ArrayList<Appointment>();
		this.elm = 0;

		loadData();
	}

	private void loadData() {
		IDataStore store = new DataStore();
		this.data = store.loadapp(IDataStore.table);
	}

	@Override
	public boolean hasNext() {
		return (this.elm < data.size());
	}

	@Override
	public Appointment next() {
		return (data.get(this.elm++));
	}

}
