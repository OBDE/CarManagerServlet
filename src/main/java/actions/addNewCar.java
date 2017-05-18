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
@WebServlet("/addnewcar")
public class addNewCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(addNewCar.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addNewCar() {
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
		String carVendor,carName,carType,carYear,carHp,carCcm;
		
		if (session.getAttribute("userid") != null )
		{	   	
			 // ha egyik értékünk se null 
			
				 carVendor = request.getParameter("addCarVendor");
				 carName = request.getParameter("addCarName");
				 carType = request.getParameter("addCarType");
				 carYear = request.getParameter("addCarYear");
				 carHp = request.getParameter("addCarHp");
				 carCcm = request.getParameter("addCarCcm");				
				
			String userid = session.getAttribute("userid").toString();				
			
			
			logger.debug("Bejövő adatok: "+userid+" "+carVendor+" "+carName+" "+carType+" "+carHp+" "+carCcm);
							
				
			logger.info("Új autó hozzáadása folyamatban... userid : " + userid);	
			
			String answer = DatabaseConnection.addNewCar(userid, carVendor, carName, carType, carYear, carHp, carCcm);
			
			response.getWriter().append(answer);
			
			if(answer.indexOf("error") > -1)
				logger.warn("Új autó hozzáadássa sikertelen ! userid: "+ userid);
			if(answer.indexOf("success") > -1)
				logger.info("Új autó hozzáadása sikeres! userid: "+ userid);
		}
		else {
			response.getWriter().append(new JSONObject().put("error","Szerver hiba!").toString());
		}
	}

}
