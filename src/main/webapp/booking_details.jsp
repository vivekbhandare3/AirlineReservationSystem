<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ars.model.User" %>
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
    <title>Booking Details - MAK Airlines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark"><div class="container-fluid"><a class="navbar-brand" href="index.jsp">MAK Airlines</a><ul class="navbar-nav ms-auto"><li class="nav-item"><a class="nav-link" href="user_profile">Profile</a></li><li class="nav-item"><a class="nav-link" href="logout">Logout</a></li></ul></div></nav>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h4>Passenger Information</h4>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Please enter the details for the passenger.</p>
                        <% if (request.getAttribute("error") != null) { %><div class="alert alert-danger"><%= request.getAttribute("error") %></div><% } %>
                        <form action="confirmBooking" method="post">
                            <input type="hidden" name="flightId" value="${requestScope.flightId}">
                            <div class="mb-3">
                                <label for="passengerName" class="form-label">Full Name</label>
                                <input type="text" class="form-control" id="passengerName" name="passengerName" required>
                            </div>
                            <div class="mb-3">
                                <label for="passengerAge" class="form-label">Age</label>
                                <input type="number" class="form-control" id="passengerAge" name="passengerAge" min="1" required>
                            </div>
                            <hr>
                            <p>You will be charged upon confirmation. By clicking 'Confirm Booking', you agree to our terms and conditions.</p>
                            <button type="submit" class="btn btn-primary w-100">Confirm Booking</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>