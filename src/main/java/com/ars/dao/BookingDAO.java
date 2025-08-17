package com.ars.dao;

import com.ars.model.Booking;
import com.ars.model.BookingDetails;
import com.ars.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    // Updated bookFlight method
    public boolean bookFlight(Booking booking) {
        String query = "INSERT INTO bookings (user_id, flight_id, passenger_name, passenger_age, status, payment_status) VALUES (?, ?, ?, ?, 'confirmed', 'paid')";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, booking.getUserId());
            ps.setInt(2, booking.getFlightId());
            ps.setString(3, booking.getPassengerName());
            ps.setInt(4, booking.getPassengerAge());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // New method to get detailed booking history for a user
    public List<BookingDetails> getBookingDetailsByUserId(int userId) {
        List<BookingDetails> bookingDetailsList = new ArrayList<>();
        String query = "SELECT f.flight_number, f.origin, f.destination, f.departure_date, f.departure_time, " +
                       "b.passenger_name, b.booking_date, b.status, a.name as airline_name " +
                       "FROM bookings b " +
                       "JOIN flights f ON b.flight_id = f.id " +
                       "JOIN airlines a ON f.airline_id = a.id " +
                       "WHERE b.user_id = ? ORDER BY b.booking_date DESC";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookingDetails details = new BookingDetails();
                // ... set all other properties as before
                details.setFlightNumber(rs.getString("flight_number"));
                details.setOrigin(rs.getString("origin"));
                details.setDestination(rs.getString("destination"));
                details.setDepartureDate(rs.getDate("departure_date"));
                details.setDepartureTime(rs.getTime("departure_time"));
                details.setPassengerName(rs.getString("passenger_name"));
                details.setBookingDate(rs.getTimestamp("booking_date"));
                details.setBookingStatus(rs.getString("status"));
                details.setAirlineName(rs.getString("airline_name")); // Set new property
                bookingDetailsList.add(details);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return bookingDetailsList;
    }
}