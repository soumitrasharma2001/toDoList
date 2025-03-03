package models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a task in the system.
 * 
 * A task contains an ID, title, description, date, priority, and an associated user.
 * Lombok annotations are used to reduce boilerplate code.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
	private int id;         // Unique identifier for the task
	private String title;   // Title of the task
	private String task;    // Description of the task
	private Date date;      // Due date of the task
	private int priority;   // Priority level (e.g., 1 = Low, 2 = Medium, 3 = High)
	private int user;       // User ID associated with the task

	/**
	 * Constructor to create a task without an ID.
	 * Used when inserting a new task into the database.
	 *
	 * @param title    The title of the task.
	 * @param task     The description of the task.
	 * @param date     The due date of the task.
	 * @param priority The priority level of the task.
	 */
	public Task(String title, String task, Date date, int priority) {
		this.title = title;
		this.task = task;
		this.date = date;
		this.priority = priority;
	}

	/**
	 * Constructor to create a task with a user ID.
	 * Used when inserting a new task associated with a user.
	 *
	 * @param title    The title of the task.
	 * @param task     The description of the task.
	 * @param date     The due date of the task.
	 * @param priority The priority level of the task.
	 * @param user     The user ID associated with the task.
	 */
	public Task(String title, String task, Date date, int priority, int user) {
		this.title = title;
		this.task = task;
		this.date = date;
		this.priority = priority;
		this.user = user;
	}

	/**
	 * Constructor to create a task with an ID.
	 * Used when updating an existing task in the database.
	 *
	 * @param id       The unique identifier of the task.
	 * @param title    The title of the task.
	 * @param task     The description of the task.
	 * @param date     The due date of the task.
	 * @param priority The priority level of the task.
	 */
	public Task(int id, String title, String task, Date date, int priority) {
		this.id = id;
		this.title = title;
		this.task = task;
		this.date = date;
		this.priority = priority;
	}
}
