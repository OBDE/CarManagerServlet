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

import dbconnection.DatabaseConnection;


/**
 * Servlet implementation class login
 */
@WebServlet("/getallcars")
public class getAllCars extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(getAllCars.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllCars() {
        super();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("?site=mycars");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		HttpSession session = request.getSession();	
		
		if (session.getAttribute("userid") != null)
		{ // ha van a sessionbe userid
			
			String userid = session.getAttribute("userid").toString();						
			logger.info("Osszes auto lekerdezese folyamatban... userid: " + userid);	
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(DatabaseConnection.getAllCars());
			logger.info("Osszes auto lekerdezese sikeres! userid: "+ userid);
			
		}		
	}

}
