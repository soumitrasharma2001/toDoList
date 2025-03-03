package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 * 
 * This servlet handles user logout by removing the user session attribute 
 * and redirecting to the login page.
 */
@WebServlet(name = "logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP GET request for user logout.
	 * 
	 * Removes the "user" attribute from the session and redirects the user to the login page.
	 *
	 * @param request  the HttpServletRequest that contains the logout request.
	 * @param response the HttpServletResponse used to redirect the user after logout.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Remove the user session attribute to log out the user
		request.getSession().removeAttribute("user");

		// Redirect to the login page
		response.sendRedirect("index.jsp");
	}
}
