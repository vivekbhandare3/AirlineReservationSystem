package com.ars.dao;
import com.ars.model.Flight;
import com.ars.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class FlightDAO {
	public List<Flight> searchFlights(String origin, String destination, Date departureDate, String flightClass) {
	    List<Flight> flights = new ArrayList<>();
	    String query = "SELECT f.*, a.name as airline_name FROM flights f JOIN airlines a ON f.airline_id = a.id " +
	                   "WHERE f.origin = ? AND f.destination = ? AND f.departure_date = ? AND f.class = ? AND f.seats_available > 0";
	    try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
	        // ... set parameters as before
	        ps.setString(1, origin);
	        ps.setString(2, destination);
	        ps.setDate(3, departureDate);
	        ps.setString(4, flightClass);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Flight flight = new Flight();
	            // ... set all flight properties as before
	            flight.setId(rs.getInt("id"));
	            flight.setFlightNumber(rs.getString("flight_number"));
	            flight.setOrigin(rs.getString("origin"));
	            flight.setDestination(rs.getString("destination"));
	            flight.setDepartureDate(rs.getDate("departure_date"));
	            flight.setDepartureTime(rs.getTime("departure_time"));
	            flight.setArrivalTime(rs.getTime("arrival_time"));
	            flight.setSeatsAvailable(rs.getInt("seats_available"));
	            flight.setPrice(rs.getDouble("price"));
	            flight.setFlightClass(rs.getString("class"));
	            flight.setStatus(rs.getString("status"));
	            flight.setAirlineId(rs.getInt("airline_id"));
	            flight.setAirlineName(rs.getString("airline_name")); // Set joined property
	            flights.add(flight);
	        }
	    } catch (SQLException e) { e.printStackTrace(); }
	    return flights;
	}
 // Add this method inside your existing FlightDAO class

	public Flight getFlightById(int flightId) {
	    Flight flight = null;
	    String query = "SELECT f.*, a.name as airline_name FROM flights f JOIN airlines a ON f.airline_id = a.id WHERE f.id = ?";
	    try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
	        ps.setInt(1, flightId);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            flight = new Flight();
	            // ... set all flight properties as before
	            flight.setId(rs.getInt("id"));
	            flight.setFlightNumber(rs.getString("flight_number"));
	            flight.setOrigin(rs.getString("origin"));
	            flight.setDestination(rs.getString("destination"));
	            flight.setDepartureDate(rs.getDate("departure_date"));
	            flight.setDepartureTime(rs.getTime("departure_time"));
	            flight.setArrivalTime(rs.getTime("arrival_time"));
	            flight.setSeatsAvailable(rs.getInt("seats_available"));
	            flight.setPrice(rs.getDouble("price"));
	            flight.setFlightClass(rs.getString("class"));
	            flight.setStatus(rs.getString("status"));
	            flight.setAirlineId(rs.getInt("airline_id"));
	            flight.setAirlineName(rs.getString("airline_name")); // Set joined property
	        }
	    } catch (SQLException e) { e.printStackTrace(); }
	    return flight;
	}
    public boolean addFlight(Flight flight) {
        String query = "INSERT INTO flights (flight_number, origin, destination, departure_date, departure_time, arrival_time, seats_available, price, class, status, airline_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            // ... set parameters 1 through 10 as before
            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getOrigin());
            ps.setString(3, flight.getDestination());
            ps.setDate(4, flight.getDepartureDate());
            ps.setTime(5, flight.getDepartureTime());
            ps.setTime(6, flight.getArrivalTime());
            ps.setInt(7, flight.getSeatsAvailable());
            ps.setDouble(8, flight.getPrice());
            ps.setString(9, flight.getFlightClass());
            ps.setString(10, flight.getStatus());
            ps.setInt(11, flight.getAirlineId()); // Add new parameter
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
    public boolean updateSeats(int flightId, int seats) {
        String query = "UPDATE flights SET seats_available = seats_available - ? WHERE id = ? AND seats_available >= ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, seats);
            ps.setInt(2, flightId);
            ps.setInt(3, seats);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
 // In FlightDAO.java
    public boolean updateFlight(Flight flight) {
        String query = "UPDATE flights SET flight_number=?, origin=?, destination=?, departure_date=?, " +
                       "departure_time=?, arrival_time=?, seats_available=?, price=?, class=?, status=?, airline_id=? " +
                       "WHERE id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, flight.getFlightNumber());
            ps.setString(2, flight.getOrigin());
            ps.setString(3, flight.getDestination());
            ps.setDate(4, flight.getDepartureDate());
            ps.setTime(5, flight.getDepartureTime());
            ps.setTime(6, flight.getArrivalTime());
            ps.setInt(7, flight.getSeatsAvailable());
            ps.setDouble(8, flight.getPrice());
            ps.setString(9, flight.getFlightClass());
            ps.setString(10, flight.getStatus());
            ps.setInt(11, flight.getAirlineId());
            ps.setInt(12, flight.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteFlight(int flightId) {
        String query = "DELETE FROM flights WHERE id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, flightId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
 // Add this new method inside your FlightDAO.java class

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        // This query joins with the airlines table to also get the airline's name
        String query = "SELECT f.*, a.name as airline_name FROM flights f JOIN airlines a ON f.airline_id = a.id ORDER BY f.departure_date DESC";
        
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setFlightNumber(rs.getString("flight_number"));
                flight.setOrigin(rs.getString("origin"));
                flight.setDestination(rs.getString("destination"));
                flight.setDepartureDate(rs.getDate("departure_date"));
                flight.setSeatsAvailable(rs.getInt("seats_available"));
                flight.setPrice(rs.getDouble("price"));
                flight.setAirlineName(rs.getString("airline_name"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }
}