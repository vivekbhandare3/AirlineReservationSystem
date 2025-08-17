package com.ars.servlet;

import com.ars.dao.FlightDAO;
import com.ars.model.Flight;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/flightDetails")
public class FlightDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int flightId = Integer.parseInt(req.getParameter("id"));
            FlightDAO flightDAO = new FlightDAO();
            Flight flight = flightDAO.getFlightById(flightId);

            if (flight != null) {
                req.setAttribute("flight", flight);
                req.getRequestDispatcher("flight_details.jsp").forward(req, res);
            } else {
                // If flight not found, redirect to the homepage or an error page
                req.setAttribute("error", "Flight details could not be found.");
                req.getRequestDispatcher("index.jsp").forward(req, res);
            }
        } catch (NumberFormatException e) {
            // If the ID is not a valid number
            req.setAttribute("error", "Invalid flight ID.");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}