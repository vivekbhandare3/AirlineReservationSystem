<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.adminUser}"><c:redirect url="login.jsp"/></c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="adminDashboard">MAK Airlines [Admin]</a>
            <span class="navbar-text text-white me-3">Welcome, ${sessionScope.adminUser.username}</span>
            <ul class="navbar-nav"><li class="nav-item"><a class="nav-link" href="logout">Logout</a></li></ul>
        </div>
    </nav>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2>Flight Management</h2>
            <div>
                <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addFlightModal">Add New Flight</button>
                <a href="manageAirlines" class="btn btn-secondary">Manage Airlines</a>
            </div>
        </div>

        <c:if test="${not empty param.success}"><div class="alert alert-success">${param.success}</div></c:if>
        <c:if test="${not empty param.error}"><div class="alert alert-danger">${param.error}</div></c:if>

        <div class="table-responsive">
            <table class="table table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>Airline</th>
                        <th>Flight #</th>
                        <th>Route</th>
                        <th>Date</th>
                        <th>Departure</th>
                        <th>Arrival</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="flight" items="${flightsList}">
                        <tr>
                            <td>${flight.airlineName}</td>
                            <td>${flight.flightNumber}</td>
                            <td>${flight.origin} to ${flight.destination}</td>
                            <td>${flight.departureDate}</td>
                            <td>${flight.departureTime}</td>
                            <td>${flight.arrivalTime}</td>
                            <td><span class="badge bg-info text-dark">${flight.status}</span></td>
                            <td>
                                <a href="editFlight?id=${flight.id}" class="btn btn-sm btn-warning">Edit</a>
                                <form action="adminFlight" method="post" class="d-inline" onsubmit="return confirm('Delete this flight? This cannot be undone.');">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="${flight.id}">
                                    <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="modal fade" id="addFlightModal" tabindex="-1">
            <div class="modal-dialog modal-lg modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header"><h5 class="modal-title">Add New Flight</h5><button type="button" class="btn-close" data-bs-dismiss="modal"></button></div>
                    <div class="modal-body">
                        <form action="adminFlight" method="post" class="row g-3">
                            <input type="hidden" name="action" value="add">
                            <div class="col-md-6"><label class="form-label">Airline</label><select name="airlineId" class="form-select" required><option value="" disabled selected>Choose...</option><c:forEach var="airline" items="${airlinesList}"><option value="${airline.id}">${airline.name}</option></c:forEach></select></div>
                            <div class="col-md-6"><label class="form-label">Flight Number</label><input type="text" name="flightNumber" class="form-control" required></div>
                            <div class="col-md-6"><label class="form-label">Origin</label><input type="text" name="origin" class="form-control" required></div>
                            <div class="col-md-6"><label class="form-label">Destination</label><input type="text" name="destination" class="form-control" required></div>
                            <div class="col-md-4"><label class="form-label">Departure Date</label><input type="date" name="departureDate" class="form-control" required></div>
                            <div class="col-md-4"><label class="form-label">Departure Time</label><input type="time" name="departureTime" class="form-control" required></div>
                            <div class="col-md-4"><label class="form-label">Arrival Time</label><input type="time" name="arrivalTime" class="form-control" required></div>
                            <div class="col-md-4"><label class="form-label">Seats</label><input type="number" name="seatsAvailable" class="form-control" required></div>
                            <div class="col-md-4"><label class="form-label">Price</label><input type="number" step="0.01" name="price" class="form-control" required></div>
                            <div class="col-md-4"><label class="form-label">Class</label><select name="class" class="form-select"><option value="economy">Economy</option><option value="business">Business</option></select></div>
                            <div class="col-md-12"><label class="form-label">Status</label><select name="status" class="form-select"><option value="on-time">On-Time</option><option value="delayed">Delayed</option><option value="cancelled">Cancelled</option></select></div>
                            <div class="col-12 mt-4 text-end"><button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button><button type="submit" class="btn btn-primary">Save Flight</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>