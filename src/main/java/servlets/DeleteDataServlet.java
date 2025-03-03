package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.TaskDao;

/**
 * Servlet implementation class DeleteDataServlet
 * This servlet handles the deletion of a task from the database.
 */
@WebServlet(name = "deleteData", urlPatterns = { "/deleteData" })
public class DeleteDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP POST request to delete a task.
	 *
	 * @param request  the HttpServletRequest containing the task ID to delete.
	 * @param response the HttpServletResponse to redirect after deletion.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve task ID from the request parameter
		int id = Integer.parseInt(request.getParameter("id"));

		// Call DAO method to delete the task
		new TaskDao().deleteTask(id);

		// Redirect to the home page after deletion
		response.sendRedirect("home.jsp");
	}
}
