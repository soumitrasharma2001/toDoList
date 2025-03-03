package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user in the system.
 * 
 * The User class contains user-related information such as ID, name, email, 
 * password, and account status.
 * Lombok annotations are used to reduce boilerplate code.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer uid;      // Unique identifier for the user
	private String name;      // User's full name
	private String email;     // User's email address
	private String password;  // User's password (should be stored securely)
	private Boolean status;   // Account status (e.g., true for active, false for inactive)

	/**
	 * Constructor to create a new user without an ID.
	 * Typically used when registering a new user before assigning an ID.
	 *
	 * @param name     The full name of the user.
	 * @param email    The email address of the user.
	 * @param password The password for the user account.
	 * @param status   The status of the user (true for active, false for inactive).
	 */
	public User(String name, String email, String password, Boolean status) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.status = status;
	}
}
