package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This filter manages user authentication and redirects users based on their session status.
 * 
 * - If a user is not logged in, they are restricted from accessing protected pages.
 * - If a user is already logged in, they are redirected away from login-related pages.
 */
@WebFilter(urlPatterns = "/*") // Apply filter to all URLs
public class MainFilter implements Filter {

    /**
     * Filters requests to manage session-based access control.
     * 
     * @param request  The servlet request object.
     * @param response The servlet response object.
     * @param chain    The filter chain to continue processing requests.
     * @throws IOException      If an input or output error occurs.
     * @throws ServletException If a servlet-specific error occurs.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        // Retrieve the user session if it exists
        HttpSession session = req.getSession(false);
        Object user = (session != null) ? session.getAttribute("user") : null;

        // Restrict unauthenticated users from accessing protected pages
        if (user == null) {
            if (uri.endsWith("home.jsp") || uri.endsWith("update.jsp")) {
                res.sendRedirect("index.jsp"); // Redirect to login page
                return;
            }
        }
        // Redirect authenticated users away from login-related pages
        else if (uri.endsWith("index.jsp") || uri.endsWith("login.jsp") || uri.endsWith("/")) {
            res.sendRedirect("home.jsp"); // Redirect to home page
            return;
        }

        // Allow request to proceed
        chain.doFilter(request, response);
    }
}
