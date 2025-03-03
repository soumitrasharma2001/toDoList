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
 * Servlet implementation class LoginUserServlet
 * 
 * This servlet handles user authentication by validating the provided credentials
 * and managing session attributes accordingly.
 */
@WebServlet(name = "login", urlPatterns = { "/login" })
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Processes the HTTP POST request for user login.
	 * 
	 * Retrieves user credentials from the request, validates them against the database,
	 * and sets the appropriate session attributes based on authentication success or failure.
	 *
	 * @param request  the HttpServletRequest containing user login details.
	 * @param response the HttpServletResponse used to redirect the user after authentication.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve user email and password from request parameters
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Authenticate user using UserDao
		User user = new UserDao().getUser(email, password);

		if (user != null) {
			// Store user details in session if authentication is successful
			request.getSession().setAttribute("user", user);
			response.sendRedirect("home.jsp");
		} else {
			// Store error message in session if authentication fails
			request.getSession().setAttribute("msg", "Invalid Credentials!");
			response.sendRedirect("index.jsp");
		}
	}
}
