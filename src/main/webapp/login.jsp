<%-- login.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - MAK Airlines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-dark bg-dark"><div class="container-fluid"><a class="navbar-brand" href="index.jsp">MAK Airlines</a></div></nav>
    <div class="container mt-5"><div class="row justify-content-center"><div class="col-md-5"><div class="card"><div class="card-body">
        <h2 class="card-title text-center mb-4">Login</h2>
        <% if (request.getAttribute("error") != null) { %><div class="alert alert-danger"><%= request.getAttribute("error") %></div><% } %>
        <% if (request.getParameter("success") != null) { %><div class="alert alert-success"><%= request.getParameter("success") %></div><% } %>
        <form action="login" method="post">
            <div class="mb-3"><label for="username" class="form-label">Username</label><input type="text" class="form-control" id="username" name="username" required></div>
            <div class="mb-3"><label for="password" class="form-label">Password</label><input type="password" class="form-control" id="password" name="password" required></div>
            <button type="submit" class="btn btn-primary w-100">Login</button>
        </form>
        <p class="text-center mt-3">Don't have an account? <a href="register.jsp">Register</a></p>
    </div></div></div></div></div>
</body>
</html>