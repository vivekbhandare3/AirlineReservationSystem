package com.ars.dao;

import com.ars.model.Admin;
import com.ars.util.DBUtil;
import java.sql.*;

public class AdminDAO {
    public Admin login(String username, String password) {
        Admin admin = null;
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setUsername(rs.getString("username"));
                admin.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}