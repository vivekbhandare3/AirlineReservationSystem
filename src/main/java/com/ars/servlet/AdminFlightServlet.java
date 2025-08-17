package com.ars.servlet;

import com.ars.dao.FlightDAO;
import com.ars.model.Flight;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/adminFlight")
public class AdminFlightServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        FlightDAO flightDAO = new FlightDAO();

        try {
            if ("add".equals(action)) {
                Flight flight = new Flight();
                setFlightPropertiesFromRequest(flight, req);
                if (flightDAO.addFlight(flight)) {
                    res.sendRedirect("adminDashboard?success=Flight+added");
                } else {
                    res.sendRedirect("adminDashboard?error=Failed+to+add+flight");
                }

            } else if ("update".equals(action)) {
                Flight flight = new Flight();
                flight.setId(Integer.parseInt(req.getParameter("id")));
                setFlightPropertiesFromRequest(flight, req); // Use helper method
                if (flightDAO.updateFlight(flight)) {
                    res.sendRedirect("adminDashboard?success=Flight+updated");
                } else {
                    res.sendRedirect("adminDashboard?error=Failed+to+update+flight");
                }

            } else if ("delete".equals(action)) {
                int flightId = Integer.parseInt(req.getParameter("id"));
                if (flightDAO.deleteFlight(flightId)) {
                    res.sendRedirect("adminDashboard?success=Flight+deleted");
                } else {
                    res.sendRedirect("adminDashboard?error=Failed+to+delete+flight");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.sendRedirect("adminDashboard?error=An+unexpected+error+occurred");
        }
    }

    // Helper method to avoid repeating code
    private void setFlightPropertiesFromRequest(Flight flight, HttpServletRequest req) {
        flight.setAirlineId(Integer.parseInt(req.getParameter("airlineId")));
        flight.setFlightNumber(req.getParameter("flightNumber"));
        flight.setOrigin(req.getParameter("origin"));
        flight.setDestination(req.getParameter("destination"));
        flight.setDepartureDate(Date.valueOf(req.getParameter("departureDate")));
        flight.setDepartureTime(Time.valueOf(req.getParameter("departureTime") + ":00"));
        flight.setArrivalTime(Time.valueOf(req.getParameter("arrivalTime") + ":00"));
        flight.setSeatsAvailable(Integer.parseInt(req.getParameter("seatsAvailable")));
        flight.setPrice(Double.parseDouble(req.getParameter("price")));
        flight.setFlightClass(req.getParameter("class"));
        flight.setStatus(req.getParameter("status"));
    }
}