package com.ars.model;
import java.sql.Date;
import java.sql.Time;
public class Flight {
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date departureDate;
    private Time departureTime;
    private Time arrivalTime;
    private int seatsAvailable;
    private double price;
    private String flightClass;
    private String status;
    private int airlineId;     
    private String airlineName;
    // Getters and Setters...
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
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
    public Time getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Time arrivalTime) { this.arrivalTime = arrivalTime; }
    public int getSeatsAvailable() { return seatsAvailable; }
    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getFlightClass() { return flightClass; }
    public void setFlightClass(String flightClass) { this.flightClass = flightClass; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getAirlineId() { return airlineId; }
    public void setAirlineId(int airlineId) { this.airlineId = airlineId; }
    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }
}
