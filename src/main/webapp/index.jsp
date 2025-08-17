<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>MAK Airlines - Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">MAK Airlines</a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto">
                    <% if (session.getAttribute("user") == null) { %>
                        <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="register.jsp">Register</a></li>
                    <% } else { %>
                        <li class="nav-item"><a class="nav-link" href="user_profile.jsp">Profile</a></li>
                        <li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container mt-5">
        <div class="p-5 mb-4 bg-light rounded-3">
            <div class="container-fluid py-5">
                <h1 class="display-5 fw-bold">Welcome to MAK Airlines</h1>
                <p class="col-md-8 fs-4">Your journey begins here. Search for flights and book your next adventure.</p>
            </div>
        </div>
        <h2 class="text-center mb-4">Search for Flights</h2>
        <form action="searchFlights" method="post" class="row g-3 justify-content-center">
            <div class="col-md-2"><label for="origin" class="form-label">Origin</label><input type="text" class="form-control" id="origin" name="origin" required></div>
            <div class="col-md-2"><label for="destination" class="form-label">Destination</label><input type="text" class="form-control" id="destination" name="destination" required></div>
            <div class="col-md-2"><label for="departureDate" class="form-label">Departure Date</label><input type="date" class="form-control" id="departureDate" name="departureDate" required></div>
            <div class="col-md-2"><label for="class" class="form-label">Class</label><select class="form-select" id="class" name="class"><option value="economy">Economy</option><option value="business">Business</option></select></div>
            <div class="col-md-2 align-self-end"><button type="submit" class="btn btn-primary w-100">Search</button></div>
        </form>
    </div>
</body>
</html>