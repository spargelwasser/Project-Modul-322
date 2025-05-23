package controller;

import java.util.ArrayList;
import java.util.Iterator;

import Model.DataStore;
import Model.IDataStore;
import Model.Time;

public class TimeLoader implements Iterator<Time> {

	private ArrayList<Time> data;
	private int elm;

	public TimeLoader() {
		this.data = new ArrayList<Time>();
		this.elm = 0;

		loadData();
	}

	private void loadData() {
		IDataStore store = new DataStore();
		this.data = store.loadtim(IDataStore.timeList);
	}

	@Override
	public boolean hasNext() {
		return (this.elm < data.size());
	}

	@Override
	public Time next() {
		return data.get(this.elm++);
	}

}
