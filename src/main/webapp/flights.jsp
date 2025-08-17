<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Available Flights - MAK Airlines</title>
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
                        <li class="nav-item"><a class="nav-link" href="login.jsp">Login to Book</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Available Flights</h2>
        <c:if test="${not empty requestScope.error}">
            <div class="alert alert-danger">${requestScope.error}</div>
        </c:if>
        <c:if test="${not empty flightsList}">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>Airline</th>
                        <th>Flight #</th>
                        <th>Origin</th>
                        <th>Destination</th>
                        <th>Departure</th>
                        <th>Price</th>
                        <th>Class</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="flight" items="${flightsList}">
                        <tr>
                            <td>${flight.airlineName}</td>
                            <td><strong><a href="flightDetails?id=${flight.id}">${flight.flightNumber}</a></strong></td>
                            <td>${flight.origin}</td>
                            <td>${flight.destination}</td>
                            <td>${flight.departureDate} ${flight.departureTime}</td>
                            <td>$${flight.price}</td>
                            <td>${flight.flightClass}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.user}">
                                        <form action="prepareBooking" method="get" style="margin:0;">
                                            <input type="hidden" name="flightId" value="${flight.id}">
                                            <button type="submit" class="btn btn-success btn-sm">Book</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="login.jsp" class="btn btn-secondary btn-sm">Login to Book</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty flightsList}">
            <div class="alert alert-info text-center">
                <p class="mb-0">No flights were found for the selected criteria.</p>
                <a href="index.jsp" class="alert-link">Try another search</a>.
            </div>
        </c:if>
    </div>
</body>
</html>