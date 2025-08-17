package com.ars.servlet;

import com.ars.dao.AirlineDAO;
import com.ars.model.Airline; // You might need to create this simple model class
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editAirline")
public class EditAirlineServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        AirlineDAO airlineDAO = new AirlineDAO();
        // You need a getAirlineById method in AirlineDAO
        Airline airline = airlineDAO.getAirlineById(id); 
        req.setAttribute("airline", airline);
        req.getRequestDispatcher("edit_airline.jsp").forward(req, res);
    }
}