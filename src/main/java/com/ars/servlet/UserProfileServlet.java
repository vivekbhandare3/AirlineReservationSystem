package com.ars.servlet;

import com.ars.dao.BookingDAO;
import com.ars.model.BookingDetails;
import com.ars.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user_profile")
public class UserProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        BookingDAO bookingDAO = new BookingDAO();
        List<BookingDetails> bookings = bookingDAO.getBookingDetailsByUserId(user.getId());

        req.setAttribute("bookingsList", bookings);
        req.getRequestDispatcher("user_profile.jsp").forward(req, res);
    }
}