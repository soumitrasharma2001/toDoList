package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Task;

/**
 * Data Access Object (DAO) for managing task-related database operations.
 */
public class TaskDao {

    /**
     * Saves a new task in the database.
     *
     * @param task The task object to be saved.
     */
    public void save(Task task) {
        try (Connection cnn = DB.getConnection()) {
            PreparedStatement ps = cnn.prepareStatement("INSERT INTO Task(title, task, date, priority, user) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getTask());
            ps.setDate(3, task.getDate());
            ps.setInt(4, task.getPriority());
            ps.setInt(5, task.getUser());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Consider using logging instead of printStackTrace
        }
    }

    /**
     * Retrieves a list of tasks associated with a specific user.
     *
     * @param uid The user ID.
     * @return A list of tasks belonging to the specified user.
     */
    public List<Task> listTasks(int uid) {
        List<Task> tasks = new ArrayList<>();
        try (Connection cnn = DB.getConnection()) {
            PreparedStatement ps = cnn.prepareStatement("SELECT * FROM Task WHERE user=?");
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String task = rs.getString("task");
                Date date = rs.getDate("date");
                int priority = rs.getInt("priority");
                tasks.add(new Task(id, title, task, date, priority));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task.
     * @return The Task object if found, otherwise null.
     */
    public Task getTaskById(int id) {
        try (Connection cnn = DB.getConnection()) {
            PreparedStatement ps = cnn.prepareStatement("SELECT * FROM Task WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int tid = rs.getInt("id");
                String title = rs.getString("title");
                String task = rs.getString("task");
                Date date = rs.getDate("date");
                int priority = rs.getInt("priority");
                return new Task(tid, title, task, date, priority);
            }
            return null;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a task from the database.
     *
     * @param id The ID of the task to be deleted.
     */
    public void deleteTask(int id) {
        try (Connection cnn = DB.getConnection()) {
            PreparedStatement ps = cnn.prepareStatement("DELETE FROM Task WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing task in the database.
     *
     * @param task The Task object containing updated values.
     */
    public void updateData(Task task) {
        try (Connection cnn = DB.getConnection()) {
            PreparedStatement ps = cnn.prepareStatement("UPDATE Task SET title=?, task=?, date=?, priority=? WHERE id=?");
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getTask());
            ps.setDate(3, task.getDate());
            ps.setInt(4, task.getPriority());
            ps.setInt(5, task.getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
