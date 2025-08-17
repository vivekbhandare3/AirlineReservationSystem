<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${empty sessionScope.adminUser}"><c:redirect url="login.jsp"/></c:if>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Airlines</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-dark bg-dark"><div class="container-fluid"><a class="navbar-brand" href="adminDashboard">Admin Dashboard</a></div></nav>
    <div class="container mt-4">
        <div class="row">
            <div class="col-md-5">
                <h4>Add New Airline</h4>
                <div class="card">
                    <div class="card-body">
                        <form action="manageAirlines" method="post">
                            <input type="hidden" name="action" value="add">
                            <div class="mb-3"><label for="name" class="form-label">Airline Name</label><input type="text" class="form-control" id="name" name="name" required></div>
                            <div class="mb-3"><label for="code" class="form-label">Airline Code (e.g., AI, 6E)</label><input type="text" class="form-control" id="code" name="code" required></div>
                            <button type="submit" class="btn btn-primary">Add Airline</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-7">
                <h4>Existing Airlines</h4>
                <c:if test="${not empty param.success}"><div class="alert alert-success">${param.success}</div></c:if>
                <c:if test="${not empty param.error}"><div class="alert alert-danger">${param.error}</div></c:if>
                <table class="table table-hover">
                    <thead class="table-light">
                        <tr><th>Name</th><th>Code</th><th class="text-end">Actions</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="airline" items="${airlinesList}">
                            <tr>
                                <td>${airline.name}</td>
                                <td>${airline.code}</td>
                                <td class="text-end">
                                    <a href="editAirline?id=${airline.id}" class="btn btn-sm btn-warning">Edit</a>
                                    <form action="manageAirlines" method="post" class="d-inline" onsubmit="return confirm('Delete this airline? All associated flights will also be deleted.');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="${airline.id}">
                                        <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>