package controller;

import java.util.ArrayList;
import java.util.Iterator;

import Model.DataStore;
import Model.IDataStore;
import Model.Location;

public class LocationLoader implements Iterator<Location> {

	private ArrayList<Location> data;
	private int elm;

	public LocationLoader() {
		this.data = new ArrayList<Location>();
		this.elm = 0;

		loadData();
	}

	private void loadData() {
		IDataStore store = new DataStore();
		this.data = store.loadloc(IDataStore.locationList);
	}

	@Override
	public boolean hasNext() {
		return (this.elm < data.size());
	}

	@Override
	public Location next() {
		return data.get(this.elm++);
	}

}
