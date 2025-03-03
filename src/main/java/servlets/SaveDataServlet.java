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
 * Servlet implementation class SaveDataServlet
 * 
 * This servlet handles the creation and saving of new tasks.
 * It retrieves task details from the request, converts the date format,
 * and stores the task in the database.
 */
@WebServlet(name = "saveData", urlPatterns = { "/saveData" })
public class SaveDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles the HTTP POST request for saving a new task.
	 * 
	 * Retrieves task details from the request parameters, converts the date format,
	 * and saves the task in the database. If an error occurs while parsing the date,
	 * it prints the stack trace.
	 *
	 * @param request  the HttpServletRequest containing task details.
	 * @param response the HttpServletResponse used to redirect after saving the task.
	 * @throws ServletException if a servlet-specific error occurs.
	 * @throws IOException      if an I/O error occurs.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve task details from request parameters
		String title = request.getParameter("title");
		String task = request.getParameter("task");
		String dateString = request.getParameter("taskDate");
		int priority = Integer.parseInt(request.getParameter("priority"));
		int uid = Integer.parseInt(request.getParameter("uid"));

		try {
			// Convert string date to java.sql.Date
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			// Save the task in the database
			new TaskDao().save(new Task(title, task, sqlDate, priority, uid));
		} catch (ParseException e) {
			// Log the exception instead of printing stack trace (best practice)
			e.printStackTrace();
		}

		// Redirect to the home page
		response.sendRedirect("home.jsp");
	}
}
