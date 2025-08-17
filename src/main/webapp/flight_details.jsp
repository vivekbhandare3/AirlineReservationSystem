<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Flight Details - MAK Airlines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">MAK Airlines</a>
            <ul class="navbar-nav ms-auto">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li class="nav-item"><a class="nav-link" href="user_profile">Profile</a></li>
                        <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
    <div class="container mt-5">
        <c:choose>
            <c:when test="${not empty flight}">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h3>Flight Details: ${flight.flightNumber}</h3>
                    </div>
                    <div class="card-body">
                        <h4 class="card-title">${flight.origin} to ${flight.destination}</h4>
                        <h5 class="card-subtitle mb-3 text-muted">Operated by: ${flight.airlineName}</h5>
                        <hr>
                        <div class="row">
                            <div class="col-md-6">
                                <p><strong>Departure Date:</strong> ${flight.departureDate}</p>
                                <p><strong>Departure Time:</strong> ${flight.departureTime}</p>
                                <p><strong>Arrival Time:</strong> ${flight.arrivalTime}</p>
                                <p><strong>Status:</strong> <span class="badge bg-info text-dark">${flight.status}</span></p>
                            </div>
                            <div class="col-md-6">
                                <p><strong>Class:</strong> ${flight.flightClass}</p>
                                <p><strong>Seats Available:</strong> ${flight.seatsAvailable}</p>
                                <h4 class="text-success"><strong>Price:</strong> $${flight.price}</h4>
                            </div>
                        </div>
                        <hr>
                        <div class="text-center">
                            <form action="prepareBooking" method="get">
                                <input type="hidden" name="flightId" value="${flight.id}">
                                <c:choose>
                                    <c:when test="${not empty sessionScope.user}">
                                        <button type="submit" class="btn btn-success btn-lg px-5">Proceed to Booking</button>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="login.jsp" class="btn btn-secondary btn-lg">Login to Book</a>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger">
                    The flight you requested could not be found. Please <a href="index.jsp" class="alert-link">try your search again</a>.
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>