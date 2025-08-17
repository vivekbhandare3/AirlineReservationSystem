package com.ars.servlet;

import com.ars.dao.AirlineDAO;
import com.ars.dao.FlightDAO;
import com.ars.model.Airline;
import com.ars.model.Flight;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/editFlight")
public class EditFlightServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            
            FlightDAO flightDAO = new FlightDAO();
            Flight flight = flightDAO.getFlightById(id);
            
            AirlineDAO airlineDAO = new AirlineDAO();
            List<Airline> airlines = airlineDAO.getAllAirlines();
            
            req.setAttribute("flight", flight);
            req.setAttribute("airlinesList", airlines);
            
            req.getRequestDispatcher("edit_flight.jsp").forward(req, res);
        } catch (Exception e) {
            // Redirect with an error if the flight ID is invalid or not found
            res.sendRedirect("adminDashboard?error=Could+not+find+flight+to+edit");
        }
    }
}