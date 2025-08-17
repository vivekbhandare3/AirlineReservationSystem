package com.ars.servlet;

import com.ars.dao.AirlineDAO;
import com.ars.model.Airline;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manageAirlines")
public class ManageAirlinesServlet extends HttpServlet {

    // Handles loading the page with the list of airlines
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        AirlineDAO airlineDAO = new AirlineDAO();
        List<Airline> airlines = airlineDAO.getAllAirlines();
        req.setAttribute("airlinesList", airlines);
        req.getRequestDispatcher("manage_airlines.jsp").forward(req, res);
    }

    // Handles add, update, and delete form submissions
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        AirlineDAO airlineDAO = new AirlineDAO();

        if ("add".equals(action)) {
            Airline airline = new Airline();
            airline.setName(req.getParameter("name"));
            airline.setCode(req.getParameter("code"));
            airlineDAO.addAirline(airline);
        } else if ("update".equals(action)) {
            Airline airline = new Airline();
            airline.setId(Integer.parseInt(req.getParameter("id")));
            airline.setName(req.getParameter("name"));
            airline.setCode(req.getParameter("code"));
            airlineDAO.updateAirline(airline);
        } else if ("delete".equals(action)) {
            int airlineId = Integer.parseInt(req.getParameter("id"));
            airlineDAO.deleteAirline(airlineId);
        }
        res.sendRedirect("manageAirlines");
    }
}