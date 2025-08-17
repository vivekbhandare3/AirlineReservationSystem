<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.adminUser}"><c:redirect url="login.jsp"/></c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Flight</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark bg-dark"><div class="container-fluid"><a class="navbar-brand" href="adminDashboard">Admin Dashboard</a></div></nav>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <h3>Edit Flight Details</h3>
            <div class="card">
                <div class="card-body">
                    <form action="adminFlight" method="post" class="row g-3">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${flight.id}">
                        
                        <div class="col-md-6">
                            <label for="airlineId" class="form-label">Airline</label>
                            <select id="airlineId" name="airlineId" class="form-select" required>
                                <c:forEach var="airline" items="${airlinesList}">
                                    <option value="${airline.id}" ${airline.id == flight.airlineId ? 'selected' : ''}>${airline.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="flightNumber" class="form-label">Flight Number</label>
                            <input type="text" id="flightNumber" name="flightNumber" class="form-control" value="${flight.flightNumber}" required>
                        </div>
                        <div class="col-md-6">
                            <label for="origin" class="form-label">Origin</label>
                            <input type="text" id="origin" name="origin" class="form-control" value="${flight.origin}" required>
                        </div>
                        <div class="col-md-6">
                            <label for="destination" class="form-label">Destination</label>
                            <input type="text" id="destination" name="destination" class="form-control" value="${flight.destination}" required>
                        </div>
                        <div class="col-md-4">
                            <label for="departureDate" class="form-label">Departure Date</label>
                            <input type="date" id="departureDate" name="departureDate" class="form-control" value="${flight.departureDate}" required>
                        </div>
                        <div class="col-md-4">
                            <label for="departureTime" class="form-label">Departure Time</label>
                            <input type="time" id="departureTime" name="departureTime" class="form-control" value="${flight.departureTime}" required>
                        </div>
                        <div class="col-md-4">
                            <label for="arrivalTime" class="form-label">Arrival Time</label>
                            <input type="time" id="arrivalTime" name="arrivalTime" class="form-control" value="${flight.arrivalTime}" required>
                        </div>
                        <div class="col-md-4">
                            <label for="seatsAvailable" class="form-label">Seats</label>
                            <input type="number" id="seatsAvailable" name="seatsAvailable" class="form-control" value="${flight.seatsAvailable}" required>
                        </div>
                        <div class="col-md-4">
                            <label for="price" class="form-label">Price</label>
                            <input type="number" step="0.01" id="price" name="price" class="form-control" value="${flight.price}" required>
                        </div>
                        <div class="col-md-4">
                            <label for="class" class="form-label">Class</label>
                            <select id="class" name="class" class="form-select" required>
                                <option value="economy" ${flight.flightClass == 'economy' ? 'selected' : ''}>Economy</option>
                                <option value="business" ${flight.flightClass == 'business' ? 'selected' : ''}>Business</option>
                            </select>
                        </div>
                        <div class="col-md-12">
                            <label for="status" class="form-label">Status</label>
                            <select id="status" name="status" class="form-select" required>
                                <option value="on-time" ${flight.status == 'on-time' ? 'selected' : ''}>On-Time</option>
                                <option value="delayed" ${flight.status == 'delayed' ? 'selected' : ''}>Delayed</option>
                                <option value="cancelled" ${flight.status == 'cancelled' ? 'selected' : ''}>Cancelled</option>
                            </select>
                        </div>
                        <div class="col-12 mt-3">
                            <button type="submit" class="btn btn-primary">Update Flight</button>
                            <a href="adminDashboard" class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>