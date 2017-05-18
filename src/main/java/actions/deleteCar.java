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
import org.json.JSONObject;

import dbconnection.DatabaseConnection;


/**
 * Servlet implementation class login
 */
@WebServlet("/deletecar")
public class deleteCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(deleteCar.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteCar() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
		HttpSession session = request.getSession();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String carId;
		
		if (session.getAttribute("userid") != null )
		{
			carId = request.getParameter("delcarId");
			String userid = session.getAttribute("userid").toString();			
			
			
			logger.debug("Bejövő adatok: "+userid+" "+carId);
			logger.info("Autó törlése folyamatban... userid: " + userid+" carid: "+carId);	
					
			String answer = DatabaseConnection.deleteCarByCarId(userid, carId);
			
			response.getWriter().append(answer);
			
			if(answer.indexOf("error") > -1)
				logger.warn("Autó törlése sikertelen ! userid: " + userid + " carid: " + carId);
			if(answer.indexOf("success") > -1)
				logger.info("Autó törlése sikeres! userid: " + userid + " carid: " + carId);
			
		}
		else 
		{
			response.getWriter().append(new JSONObject().put("error","Szerver hiba!").toString());
		}
	}

}
