package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.UserDao;
import models.User;

/**
 * Servlet implementation class RegisterUserServlet
 * 
 * This servlet handles user registration by receiving user details,
 * saving them to the database, and redirecting to the login page.
 */
@WebServlet(name = "register", urlPatterns = { "/register" })
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP POST request for user registration.
	 * 
	 * Retrieves user details from the request, creates a new User object, 
	 * and saves it in the database. The result of registration is stored 
	 * in the session before redirecting to the login page.
	 *
	 * @param request  the HttpServletRequest containing user registration details.
	 * @param response the HttpServletResponse used to redirect after registration.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve user details from request parameters
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Save the user to the database
		Boolean registered = new UserDao().save(new User(name, email, password, true));

		// Store registration status in session
		request.getSession().setAttribute("registered", registered);

		// Redirect to the login page
		response.sendRedirect("index.jsp");
	}
}
