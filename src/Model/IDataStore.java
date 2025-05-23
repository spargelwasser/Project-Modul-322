package Model;

import java.util.ArrayList;

public interface IDataStore {

	public static final int table = 1;
	public static final int locationList = 2;
	public static final int timeList = 3;

	public ArrayList<Appointment> loadapp(int type);

	public void saveapp(ArrayList<Appointment> data, int type);

	public ArrayList<Location> loadloc(int type);

	public void saveloc(ArrayList<Location> data, int type);

	public ArrayList<Time> loadtim(int type);

	public void savetim(ArrayList<Time> data, int type);

}
