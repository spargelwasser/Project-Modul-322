package Model;

import java.text.SimpleDateFormat;

public class Time {

	private String time;

	public Time(String time) {
		super();
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFormattedTime() {

		try {

			SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			return outputFormat.format(inputFormat.parse(this.time));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
