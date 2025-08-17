<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.adminUser}"><c:redirect url="login.jsp"/></c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Airline</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-dark bg-dark"><div class="container-fluid"><a class="navbar-brand" href="adminDashboard">Admin Dashboard</a></div></nav>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h3>Edit Airline</h3>
            <form action="manageAirlines" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="${airline.id}">
                <div class="mb-3">
                    <label for="name" class="form-label">Airline Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${airline.name}" required>
                </div>
                <div class="mb-3">
                    <label for="code" class="form-label">Airline Code</label>
                    <input type="text" class="form-control" id="code" name="code" value="${airline.code}" required>
                </div>
                <button type="submit" class="btn btn-primary">Update Airline</button>
                <a href="manageAirlines" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>