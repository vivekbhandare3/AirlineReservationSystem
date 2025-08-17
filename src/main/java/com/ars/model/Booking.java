package com.ars.model;

import java.sql.Timestamp;

public class Booking {
    private int id;
    private int userId;
    private int flightId;
    private String passengerName; // New field
    private int passengerAge;     // New field
    private Timestamp bookingDate;
    private String status;
    private String paymentStatus;

    // Getters and Setters for all fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getFlightId() { return flightId; }
    public void setFlightId(int flightId) { this.flightId = flightId; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    public int getPassengerAge() { return passengerAge; }
    public void setPassengerAge(int passengerAge) { this.passengerAge = passengerAge; }
    public Timestamp getBookingDate() { return bookingDate; }
    public void setBookingDate(Timestamp bookingDate) { this.bookingDate = bookingDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
}