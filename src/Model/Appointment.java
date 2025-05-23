package Model;

public class Appointment {
	private String location;
	private String time;
	private int locationId;
	private int timeId;

	public Appointment(String location, String time) {
		this.location = location;
		this.time = time;
	}

	public Appointment(int locationId, int timeId) {
		this.locationId = locationId;
		this.timeId = timeId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getTimeId() {
		return timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}

	public String[] toStringArray() {
		return new String[] { location, time };
	}

	@Override
	public String toString() {
		return "Appointment{" + "location='" + location + '\'' + ", time='" + time + '\'' + ", locationId=" + locationId
				+ ", timeId=" + timeId + '}';
	}
}
