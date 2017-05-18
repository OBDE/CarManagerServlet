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
@WebServlet("/modifycar")
public class modifyCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(modifyCar.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyCar() {
        super();
        // TODO Auto-generated constructor stub
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
		String userid,carVendor,carName,carType,carYear,carHp,carCcm,carId;
		
		if (session.getAttribute("userid") != null )
		{	   	
			 // ha egyik értékünk se null 
				 carId = request.getParameter("modifyCarId");
				 carVendor = request.getParameter("modifyCarVendor");
				 carName = request.getParameter("modifyCarName");
				 carType = request.getParameter("modifyCarType");
				 carYear = request.getParameter("modifyCarYear");
				 carHp = request.getParameter("modifyCarHp");
				 carCcm = request.getParameter("modifyCarCcm");	
				 userid = session.getAttribute("userid").toString();				
			
			
			logger.debug("Bejövő adatok: "+userid+" "+carId+" "+carVendor+" "+carName+" "+carType+" "+carHp+" "+carCcm);				
			logger.info("Autó modosítása folyamatban... userid : " + userid +" carid : "+carId);				
			
			String answer = DatabaseConnection.modifyCarbyId(userid, carId, carVendor, carName, carType, carYear, carHp, carCcm);		
			
			if(answer.indexOf("error") > -1)
				logger.error("Autó modosítása sikertelen ! userid: "+ userid);
			if(answer.indexOf("success") > -1)
				logger.info("Autó modosítása sikeres! userid: "+ userid);
			
			
			response.getWriter().append(answer);
			
		}
		else {
			response.getWriter().append(new JSONObject().put("error","Szerver hiba!").toString());
			logger.error(" NULL USERID !!!");
		}
	}

}
