package actions;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import common.UserObject;
import dbconnection.DatabaseConnection;
import templates.Message;
import templates.Message.AlertType;

/**
 * Servlet implementation class login
 */
@WebServlet("/bejelentkezes")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(login.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession();
		 UserObject usr = null;
		 	if ( (usr = DatabaseConnection.checkLogin(request.getParameter("username"), request.getParameter("password")) ) != null )
	        {	
			 	session.setAttribute("loggedin", true);
			 	session.setAttribute("userid", usr.getId().toString());
			 	session.setAttribute("username", usr.getUserName());
			 	session.setAttribute("firstName", usr.getFirstName());
			 	session.setAttribute("lastName",usr.getLastName());
	            response.sendRedirect("");

	            logger.info("Sikeres bejelentkezés: " + request.getParameter("username"));
	        }
	        else
	        {
	            session.setAttribute("loggedin", false);
	            
	            Message msg = new Message("Hibás bejelentkezési adatok!", AlertType.Danger);

	            session.setAttribute("message", msg);
	            
	            response.sendRedirect("");

	            logger.warn("Sikertelen bejelentkezés: " + request.getParameter("username"));
	        }
	}

}
