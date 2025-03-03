package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;

/**
 * Data Access Object (DAO) for managing user-related database operations.
 */
public class UserDao {

    /**
     * Saves a new user into the database.
     *
     * @param user The user object to be saved.
     * @return True if the user is successfully saved, otherwise false.
     */
    public Boolean save(User user) {
        try (Connection cnn = DB.getConnection()) {
            PreparedStatement ps = cnn.prepareStatement("INSERT INTO users(name, email, password, status) VALUES (?, ?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setBoolean(4, user.getStatus());
            ps.executeUpdate();
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Consider using logging instead of printStackTrace
            return false;
        }
    }

    /**
     * Retrieves a user by their email and password.
     * Ensures that only active users (status=1) can log in.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return The User object if authentication is successful, otherwise null.
     */
    public User getUser(String email, String password) {
        try (Connection cnn = DB.getConnection()) {
            PreparedStatement ps = cnn.prepareStatement("SELECT * FROM users WHERE email=? AND password=? AND status=1");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int uid = rs.getInt("uid");
                String name = rs.getString("name");
                Boolean status = rs.getBoolean("status");
                return new User(uid, name, email, password, status);
            }
            return null;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
