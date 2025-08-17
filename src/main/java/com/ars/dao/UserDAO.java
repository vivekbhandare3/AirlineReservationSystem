// UserDAO.java
package com.ars.dao;
import com.ars.model.User;
import com.ars.util.DBUtil;
import java.sql.*;

public class UserDAO {
    // login method remains the same...
    public User login(String username, String password) {
        User user = null;
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhoneNumber(rs.getString("phone_number"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return user;
    }
    
    // Updated register method
    public boolean register(User user) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (username, password, email, phone_number, role) VALUES (?, ?, ?, ?, 'user')");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber()); // Add phone number
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}