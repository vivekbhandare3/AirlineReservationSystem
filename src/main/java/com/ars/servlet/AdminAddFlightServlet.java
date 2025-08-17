package com.ars.servlet;
import com.ars.dao.AirlineDAO;
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
@WebServlet("/addFlight")
public class AdminAddFlightServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Flight flight = new Flight();
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
        flight.setAirlineId(Integer.parseInt(req.getParameter("airlineId"))); 
        FlightDAO dao = new FlightDAO();
        if (dao.addFlight(flight)) {
            // Correct: Redirect to the servlet's URL
            res.sendRedirect("adminDashboard?success=Flight+added");
        } else {
            AirlineDAO airlineDAO = new AirlineDAO();
            req.setAttribute("airlinesList", airlineDAO.getAllAirlines());
            req.getRequestDispatcher("admin_dashboard.jsp").forward(req, res);
        }
    }
}