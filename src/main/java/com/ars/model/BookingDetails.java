package com.ars.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class BookingDetails {
    // Fields from Flight
    private String flightNumber;
    private String origin;
    private String destination;
    private Date departureDate;
    private Time departureTime;

    // Fields from Booking
    private String passengerName;
    private Timestamp bookingDate;
    private String bookingStatus;
    private String airlineName; // New field
    // Getters and Setters
    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }
    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public Date getDepartureDate() { return departureDate; }
    public void setDepartureDate(Date departureDate) { this.departureDate = departureDate; }
    public Time getDepartureTime() { return departureTime; }
    public void setDepartureTime(Time departureTime) { this.departureTime = departureTime; }
    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
    public Timestamp getBookingDate() { return bookingDate; }
    public void setBookingDate(Timestamp bookingDate) { this.bookingDate = bookingDate; }
    public String getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }
    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
}