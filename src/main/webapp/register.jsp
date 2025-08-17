<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register - MAK Airlines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-dark bg-dark"><div class="container-fluid"><a class="navbar-brand" href="index.jsp">MAK Airlines</a></div></nav>
    <div class="container mt-5"><div class="row justify-content-center"><div class="col-md-5"><div class="card"><div class="card-body">
        <h2 class="card-title text-center mb-4">Register</h2>
        <% if (request.getAttribute("error") != null) { %><div class="alert alert-danger" id="error-alert"><%= request.getAttribute("error") %></div><% } %>
        <form id="registerForm" action="register" method="post" onsubmit="return validateForm()">
            <div class="mb-3"><label for="username" class="form-label">Username</label><input type="text" class="form-control" id="username" name="username" required></div>
            <div class="mb-3"><label for="email" class="form-label">Email</label><input type="email" class="form-control" id="email" name="email" required></div>
            <div class="mb-3"><label for="phoneNumber" class="form-label">Phone Number</label><input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" required></div>
            <div class="mb-3"><label for="password" class="form-label">Password</label><input type="password" class="form-control" id="password" name="password" required></div>
            <div class="mb-3"><label for="confirmPassword" class="form-label">Confirm Password</label><input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required></div>
            <button type="submit" class="btn btn-primary w-100">Register</button>
        </form>
        <p class="text-center mt-3">Already have an account? <a href="login.jsp">Login</a></p>
    </div></div></div></div></div>
    
    <script>
        function validateForm() {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            const email = document.getElementById('email').value;
            const errorAlert = document.getElementById('error-alert');

            if (password.length < 6) {
                alert('Password must be at least 6 characters long.');
                return false;
            }
            if (password !== confirmPassword) {
                alert('Passwords do not match.');
                return false;
            }
            const emailRegex = /^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$/;
            if (!emailRegex.test(email)) {
                alert('Please enter a valid email address.');
                return false;
            }
            return true;
        }
    </script>
</body>
</html>