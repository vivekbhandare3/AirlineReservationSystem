package com.ars.dao;

import com.ars.model.Airline;
import com.ars.util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirlineDAO {

    public List<Airline> getAllAirlines() {
        List<Airline> airlines = new ArrayList<>();
        String query = "SELECT * FROM airlines ORDER BY name";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Airline airline = new Airline();
                airline.setId(rs.getInt("id"));
                airline.setName(rs.getString("name"));
                airline.setCode(rs.getString("code"));
                airlines.add(airline);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlines;
    }
    
    public boolean addAirline(Airline airline) {
        String query = "INSERT INTO airlines (name, code) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, airline.getName());
            ps.setString(2, airline.getCode());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateAirline(Airline airline) {
        String query = "UPDATE airlines SET name=?, code=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, airline.getName());
            ps.setString(2, airline.getCode());
            ps.setInt(3, airline.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAirline(int airlineId) {
        String query = "DELETE FROM airlines WHERE id=?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, airlineId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Airline getAirlineById(int airlineId) {
        Airline airline = null;
        String query = "SELECT * FROM airlines WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, airlineId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                airline = new Airline();
                airline.setId(rs.getInt("id"));
                airline.setName(rs.getString("name"));
                airline.setCode(rs.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airline;
    }
}