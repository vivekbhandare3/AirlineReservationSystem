package com.ars.servlet;

import com.ars.dao.BookingDAO;
import com.ars.dao.FlightDAO;
import com.ars.model.Booking;
import com.ars.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/confirmBooking")
public class ConfirmBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        try {
            User user = (User) session.getAttribute("user");
            int flightId = Integer.parseInt(req.getParameter("flightId"));
            String passengerName = req.getParameter("passengerName");
            int passengerAge = Integer.parseInt(req.getParameter("passengerAge"));

            if (passengerName == null || passengerName.trim().isEmpty() || passengerAge <= 0) {
                req.setAttribute("error", "Please provide valid passenger details.");
                req.setAttribute("flightId", flightId);
                req.getRequestDispatcher("booking_details.jsp").forward(req, res);
                return;
            }

            FlightDAO flightDAO = new FlightDAO();
            if (flightDAO.updateSeats(flightId, 1)) {
                Booking booking = new Booking();
                booking.setUserId(user.getId());
                booking.setFlightId(flightId);
                booking.setPassengerName(passengerName);
                booking.setPassengerAge(passengerAge);
                
                BookingDAO bookingDAO = new BookingDAO();
                if (bookingDAO.bookFlight(booking)) {
                    res.sendRedirect("user_profile?success=Booking successful!");
                } else {
                    // Important: Rollback seat update if booking fails (requires transaction management)
                    req.setAttribute("error", "Booking could not be finalized.");
                    req.setAttribute("flightId", flightId);
                    req.getRequestDispatcher("booking_details.jsp").forward(req, res);
                }
            } else {
                req.setAttribute("error", "Sorry, no seats are available on this flight.");
                req.getRequestDispatcher("flights.jsp").forward(req, res);
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid data provided.");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}