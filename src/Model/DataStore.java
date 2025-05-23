package Model;

import java.sql.*;
import java.util.ArrayList;

public class DataStore implements IDataStore {

	private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3307/appointments?useSSL=false";
	private static final String CONNECTION_USER = "root";
	private static final String CONNECTION_PASSWORD = "test05";

	@Override
	public ArrayList<Appointment> loadapp(int type) {
		ArrayList<Appointment> data = new ArrayList<>();
		String query = "SELECT location.location AS location, time.time AS time, appointment.idlo AS idlo, appointment.idti AS idti "
				+ "FROM appointment " + "JOIN location ON appointment.idlo = location.id "
				+ "JOIN time ON appointment.idti = time.id";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				String location = rs.getString("location");
				String time = rs.getString("time");

				data.add(new Appointment(location, time));
			}

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public void saveapp(ArrayList<Appointment> data, int type) {
		String query = "INSERT INTO appointment (idlo, idti) VALUES (?, ?)";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			conn.setAutoCommit(false);

			for (Appointment appointment : data) {
				int locationId = appointment.getLocationId();
				int timeId = appointment.getTimeId();

				if (locationId == -1 || timeId == -1) {
					System.err.println("Invalid location or time: " + locationId + ", " + timeId);
					continue;
				}

				stmt.setInt(1, locationId);
				stmt.setInt(2, timeId);
				stmt.addBatch();
			}

			stmt.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Location> loadloc(int type) {
		ArrayList<Location> data = new ArrayList<>();
		String query = "SELECT location FROM location";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				data.add(new Location(rs.getString("location")));
			}

		} catch (Exception e) {
			data.add(new Location("ERROR"));
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void saveloc(ArrayList<Location> data, int type) {
		String query = "INSERT INTO location (location) VALUES (?)";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			conn.setAutoCommit(false);

			for (Location location : data) {
				stmt.setString(1, location.getLocation());
				stmt.addBatch();
			}

			stmt.executeBatch();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Time> loadtim(int type) {
		ArrayList<Time> data = new ArrayList<>();
		String query = "SELECT time FROM time";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				data.add(new Time(rs.getString("time")));
			}

		} catch (Exception e) {
			data.add(new Time("ERROR"));
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public void savetim(ArrayList<Time> data, int type) {
		String query = "INSERT INTO time (time) VALUES (?)";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			conn.setAutoCommit(false);

			for (Time time : data) {
				stmt.setString(1, time.getFormattedTime());
				stmt.addBatch();
			}

			stmt.executeBatch();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getLocationId(String locationName) {
		String query = "SELECT id FROM location WHERE location = ?";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, locationName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int getTimeId(String timeValue) {
		String query = "SELECT id FROM time WHERE time = ?";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, timeValue);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void clearAllData() {
		String query = "DELETE FROM appointment";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD)) {
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteSelectedAppointment(String location, String time) {
		String query = "DELETE FROM appointment WHERE idlo = (SELECT id FROM location WHERE location = ?) "
				+ "AND idti = (SELECT id FROM time WHERE time = ?)";

		try (Connection conn = DriverManager.getConnection(CONNECTION_STRING, CONNECTION_USER, CONNECTION_PASSWORD);
				PreparedStatement stmt = conn.prepareStatement(query)) {

			stmt.setString(1, location);
			stmt.setString(2, time);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
