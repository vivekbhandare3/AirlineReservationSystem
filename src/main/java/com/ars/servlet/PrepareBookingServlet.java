package com.ars.servlet;

import com.ars.dao.FlightDAO;
import com.ars.model.Flight;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/prepareBooking")
public class PrepareBookingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        try {
            int flightId = Integer.parseInt(req.getParameter("flightId"));
            FlightDAO flightDAO = new FlightDAO();
            // You might need a getFlightById method in FlightDAO
            // For now, we'll assume a simple way to pass the ID.
            // In a real app, fetch the full flight details here.
            
            req.setAttribute("flightId", flightId);
            req.getRequestDispatcher("booking_details.jsp").forward(req, res);
        } catch (NumberFormatException e) {
            res.sendRedirect("index.jsp"); // Or an error page
        }
    }
}