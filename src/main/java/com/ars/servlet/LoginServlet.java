package com.ars.servlet;

import com.ars.dao.AdminDAO;
import com.ars.dao.UserDAO;
import com.ars.model.Admin;
import com.ars.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        // Try to log in as an admin first
        AdminDAO adminDAO = new AdminDAO();
        Admin admin = adminDAO.login(username, password);

        if (admin != null) {
            session.setAttribute("adminUser", admin); // Use a different session attribute for admin
            res.sendRedirect("adminDashboard");
            return;
        }

        // If not an admin, try to log in as a regular user
        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(username, password);

        if (user != null) {
            session.setAttribute("user", user);
            res.sendRedirect("user_profile");
        } else {
            req.setAttribute("error", "Invalid credentials");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }
}