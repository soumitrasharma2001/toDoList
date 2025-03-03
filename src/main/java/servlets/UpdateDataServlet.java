package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daos.TaskDao;
import models.Task;

/**
 * Servlet implementation class UpdateDataServlet
 * 
 * This servlet handles updating an existing task in the database.
 * It retrieves task details from the request, converts the date format,
 * and updates the task in the database.
 */
@WebServlet(name = "updateData", urlPatterns = { "/updateData" })
public class UpdateDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP POST request for updating a task.
	 * 
	 * Retrieves task details from the request parameters, converts the date format,
	 * and updates the corresponding task in the database.
	 *
	 * @param request  the HttpServletRequest containing task update details.
	 * @param response the HttpServletResponse used to redirect after updating the task.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Retrieve task details from request parameters
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			String task = request.getParameter("task");
			String dateString = request.getParameter("taskDate");
			int priority = Integer.parseInt(request.getParameter("priority"));

			// Convert string date to java.sql.Date
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			// Update the task in the database
			new TaskDao().updateData(new Task(id, title, task, sqlDate, priority));

			// Redirect to the home page
			response.sendRedirect("home.jsp");
		} catch (ParseException e) {
			// Log the exception instead of printing stack trace (best practice)
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// Handle invalid number format input
			e.printStackTrace();
		}
	}
}
