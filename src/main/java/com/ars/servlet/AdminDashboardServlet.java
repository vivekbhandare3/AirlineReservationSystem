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

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 1. Fetch the list of airlines (for the dropdown in the modal)
        AirlineDAO airlineDAO = new AirlineDAO();
        List<Airline> airlines = airlineDAO.getAllAirlines();
        req.setAttribute("airlinesList", airlines);

        // 2. Fetch the list of all flights (to display in the table)
        FlightDAO flightDAO = new FlightDAO();
        List<Flight> flights = flightDAO.getAllFlights();
        req.setAttribute("flightsList", flights);

        // 3. Forward to the JSP
        req.getRequestDispatcher("admin_dashboard.jsp").forward(req, res);
    }
}