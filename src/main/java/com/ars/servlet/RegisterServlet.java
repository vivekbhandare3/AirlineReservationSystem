package com.ars.servlet;

import com.ars.dao.UserDAO;
import com.ars.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String phoneNumber = req.getParameter("phoneNumber");

        // Server-side validation
        if (username == null || username.trim().isEmpty() ||
            email == null || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") ||
            password == null || password.length() < 6 ||
            !password.equals(confirmPassword)) {
            
            req.setAttribute("error", "Invalid input. Please check all fields.");
            req.getRequestDispatcher("register.jsp").forward(req, res);
            return;
        }
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Remember to hash passwords in a real application!
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);

        UserDAO dao = new UserDAO();
        if (dao.register(user)) {
            res.sendRedirect("login.jsp?success=Registration successful. Please login.");
        } else {
            req.setAttribute("error", "Registration failed. Username or email may already exist.");
            req.getRequestDispatcher("register.jsp").forward(req, res);
        }
    }
}