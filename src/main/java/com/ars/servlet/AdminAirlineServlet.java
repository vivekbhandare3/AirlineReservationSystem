package com.ars.servlet;

import com.ars.dao.AirlineDAO;
import com.ars.model.Airline;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminAirline")
public class AdminAirlineServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        AirlineDAO airlineDAO = new AirlineDAO();

        if ("add".equals(action)) {
            Airline airline = new Airline();
            airline.setName(req.getParameter("name"));
            airline.setCode(req.getParameter("code"));
            if (airlineDAO.addAirline(airline)) {
                res.sendRedirect("manageAirlines?success=Airline+added");
            } else {
                res.sendRedirect("manageAirlines?error=Failed+to+add");
            }
        } else if ("update".equals(action)) {
            Airline airline = new Airline();
            airline.setId(Integer.parseInt(req.getParameter("id")));
            airline.setName(req.getParameter("name"));
            airline.setCode(req.getParameter("code"));
            if (airlineDAO.updateAirline(airline)) {
                res.sendRedirect("manageAirlines?success=Airline+updated");
            } else {
                res.sendRedirect("manageAirlines?error=Failed+to+update");
            }
        } else if ("delete".equals(action)) {
            int airlineId = Integer.parseInt(req.getParameter("id"));
            if (airlineDAO.deleteAirline(airlineId)) {
                res.sendRedirect("manageAirlines?success=Airline+deleted");
            } else {
                res.sendRedirect("manageAirlines?error=Failed+to+delete");
            }
        }
    }
}