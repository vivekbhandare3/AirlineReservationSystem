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
import java.util.List;
@WebServlet("/searchFlights")
public class FlightSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        Date departureDate = Date.valueOf(req.getParameter("departureDate"));
        String flightClass = req.getParameter("class");
        FlightDAO dao = new FlightDAO();
        List<Flight> flights = dao.searchFlights(origin, destination, departureDate, flightClass);
        req.setAttribute("flightsList", flights);
        req.getRequestDispatcher("flights.jsp").forward(req, res);
    }
}