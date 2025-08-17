<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ars.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Profile - MAK Airlines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">MAK Airlines</a>
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link active" href="user_profile">Profile</a></li>
                <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
            </ul>
        </div>
    </nav>
    <div class="container mt-5">
        <h2 class="mb-4">Welcome, ${sessionScope.user.username}!</h2>
        <c:if test="${not empty param.success}">
            <div class="alert alert-success">${param.success}</div>
        </c:if>
        <div class="card mb-5">
            <div class="card-header">Your Profile Information</div>
            <div class="card-body">
                <p><strong>Username:</strong> ${sessionScope.user.username}</p>
                <p><strong>Email:</strong> ${sessionScope.user.email}</p>
                <p><strong>Phone:</strong> ${not empty sessionScope.user.phoneNumber ? sessionScope.user.phoneNumber : "Not Provided"}</p>
            </div>
        </div>
        
        <h4>My Bookings</h4>
        <c:choose>
            <c:when test="${not empty bookingsList}">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Airline</th>
                                <th>Flight #</th>
                                <th>Route</th>
                                <th>Departure</th>
                                <th>Passenger</th>
                                <th>Booked On</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="booking" items="${bookingsList}">
                                <tr>
                                    <td>${booking.airlineName}</td>
                                    <td>${booking.flightNumber}</td>
                                    <td>${booking.origin} to ${booking.destination}</td>
                                    <td>${booking.departureDate} at ${booking.departureTime}</td>
                                    <td>${booking.passengerName}</td>
                                    <td>${booking.bookingDate}</td>
                                    <td><span class="badge bg-success">${booking.bookingStatus}</span></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <p class="alert alert-info">You have no flight bookings yet.</p>
            </c:otherwise>
        </c:choose>
        <a href="index.jsp" class="btn btn-primary mt-4">Search for New Flights</a>
    </div>
</body>
</html>