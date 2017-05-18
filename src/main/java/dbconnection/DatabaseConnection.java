package dbconnection;

import java.sql.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import common.UserObject;
import security.SHA256;


public class DatabaseConnection
{

	private static final Logger logger = LogManager.getLogger(DatabaseConnection.class);
	private static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	private static final String DB_Prefix = "jdbc:mysql://";
	private static final String dbHost = "mysql.obde.hu";
	private static final String dbPort = "3306";
	private static final String dbDatabase = "crud";
	private static final String dbUsername = "crud";
	private static final String dbPassword = "Za78euuUDpz5jd6F";
	// user: crud | password : Za78euuUDpz5jd6F | db: crud

	public static UserObject checkLogin(String username, String pwd)
	{
		Connection con = null;
		Statement stmt = null;
		UserObject userObj = null;

		try
		{
			logger.trace("MySQL host connection: host name: " + dbHost + ", Port: " + dbPort);
			Class.forName(JDBC_Driver);

			con = DriverManager.getConnection(DB_Prefix + dbHost + ":" + dbPort + "/" + dbDatabase, dbUsername,
					dbPassword);
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "'");
			
			if (rs.wasNull())
			{
				logger.error("Result set from MySQL was null. Username: " + username);
				return null;
			}

			if (!rs.isBeforeFirst())
			{
				logger.debug("A felhasználó nem található az adatbázisban! Username: " + username);
				return null;
			}

			
			userObj = new UserObject();

			while (rs.next())
			{
				if (SHA256.checkHash(pwd, rs.getString("password")))
				{
					userObj.setId(rs.getInt("id"));
					userObj.setUserName(rs.getString("username"));
					userObj.setFirstName(rs.getString("firstName"));
					userObj.setLastName(rs.getString("lastName"));
					logger.debug("userObj Sikeresen beallitva!");
					break;
				} else
				{
					userObj = null;
				}
			}

			rs.close();

			stmt.close();
			con.close();

		} catch (ClassNotFoundException e)
		{
			logger.error("Hiba a JDBCDriver inicializálása közben: " + e.getMessage());

		} catch (SQLException e)
		{
			logger.error("SQL kivétel! " + e.getMessage());
		} finally
		{
			if (stmt != null)
			{
				try
				{
					stmt.close();
				} catch (SQLException e)
				{
					logger.error("SQL kivétel finally ág stmt.close-nál! " + e.getMessage());
				}
			}
			if (con != null)
			{
				try
				{
					con.close();
				} catch (SQLException e)
				{
					logger.error("SQL kivétel a connection lezárásánál! " + e.getMessage());
				}
			}
		}
		
		return userObj;
	}

	public static String getCarsById(String userid)
	{		
		Connection con = null;
		Statement stmt = null;
		JSONObject jsonCarsObject = new JSONObject();		
		JSONArray jsonCarsArray = new JSONArray();
					
		try
		{
			logger.trace("MySQL host connection: host name: " + dbHost + ", Port: " + dbPort);
			Class.forName(JDBC_Driver);

			con = DriverManager.getConnection(DB_Prefix + dbHost + ":" + dbPort + "/" + dbDatabase, dbUsername,dbPassword);
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM cars WHERE userid = '" + userid + "'");
		
			if (rs.wasNull())
			{
				logger.error("Result set from MySQL was null. Username: " + userid);
			}

			if (!rs.isBeforeFirst())
			{
				logger.debug("A felhasználónak nincsenek autói! userid: " + userid);
				return new JSONObject().put("error", "Nincsenek autóid! Miért nem adsz hozzá egyet?:)").toString();
			}

			while (rs.next())
			{	
				JSONObject jsonCarObject = new JSONObject();
				jsonCarObject.put("carID", rs.getInt("carID"));
				jsonCarObject.put("carVendor", rs.getString("carVendor"));
				jsonCarObject.put("carName", rs.getString("carName"));
				jsonCarObject.put("carType", rs.getString("carType"));
				jsonCarObject.put("carYear", rs.getInt("carYear"));
				jsonCarObject.put("carHp", rs.getInt("carHp"));
				jsonCarObject.put("carCcm", rs.getInt("carCcm"));
				jsonCarsArray.put(jsonCarObject);
				
			}			
			
			jsonCarsObject.put("mycars", jsonCarsArray);	
			
			rs.close();

			stmt.close();
			con.close();

		} 
		catch (ClassNotFoundException e)
		{
			
			logger.error("Hiba a JDBCDriver inicializálása közben: " + e.getMessage());

		} 
		catch (SQLException e)
		{
			logger.error("SQL kivétel! " + e.getMessage());
		} 
		finally
		{
			if (stmt != null)
			{
				try
				{
					stmt.close();
				} catch (SQLException e)
				{
					logger.error("SQL kivétel finally ág stmt.close-nál! " + e.getMessage());
				}
			}
			if (con != null)
			{
				try
				{
					con.close();
				} catch (SQLException e)
				{
					logger.error("SQL kivétel a connection lezárásánál! " + e.getMessage());
					
				}
			}
		}
		return jsonCarsObject.toString();
	}
	
	public static String getAllCars()
	{

		Connection con = null;
		Statement stmt = null;
		JSONObject jsonCarsObject = new JSONObject();		
		JSONArray jsonCarsArray = new JSONArray();
					
		try
		{
			logger.trace("MySQL host connection: host name: " + dbHost + ", Port: " + dbPort);
			Class.forName(JDBC_Driver);

			con = DriverManager.getConnection(DB_Prefix + dbHost + ":" + dbPort + "/" + dbDatabase, dbUsername,
					dbPassword);
			stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT carID,fname,lname,carVendor,carName,carType,carYear,carHp,carCCM FROM cars,(select id as uid,firstName as fname,lastName as lname from users) as x where userid=uid");
		
			if (rs.wasNull())
			{
				logger.error("Result set from MySQL was null, while selected all car");
			}

			if (!rs.isBeforeFirst())
			{
				logger.debug("Üres a tábla? Nincs benne autó.");
			}

			while (rs.next())
			{	
				JSONObject jsonCarObject = new JSONObject();
				jsonCarObject.put("carID", rs.getInt("carID"));
				jsonCarObject.put("carOwnerFirstName", rs.getString("fname"));
				jsonCarObject.put("carOwnerLastName", rs.getString("lname"));
				jsonCarObject.put("carVendor", rs.getString("carVendor"));
				jsonCarObject.put("carName", rs.getString("carName"));
				jsonCarObject.put("carType", rs.getString("carType"));
				jsonCarObject.put("carYear", rs.getInt("carYear"));
				jsonCarObject.put("carHp", rs.getInt("carHp"));
				jsonCarObject.put("carCcm", rs.getInt("carCcm"));
				jsonCarsArray.put(jsonCarObject);
				
			}
			
			
			jsonCarsObject.put("allcars", jsonCarsArray);
			
			
			rs.close();

			stmt.close();
			con.close();

		} 
		catch (ClassNotFoundException e)
		{
			
			logger.error("Hiba a JDBCDriver inicializálása közben: " + e.getMessage());

		} 
		catch (SQLException e)
		{
			logger.error("SQL kivétel! " + e.getMessage());
		} 
		finally
		{
			if (stmt != null)
			{
				try
				{
					stmt.close();
				} catch (SQLException e)
				{
					logger.error("SQL kivétel finally ág stmt.close-nál! " + e.getMessage());
				}
			}
			if (con != null)
			{
				try
				{
					con.close();
				} catch (SQLException e)
				{
					logger.error("SQL kivétel a connection lezárásánál! " + e.getMessage());
				}
			}
		}
		//return jsonCarsObject.toString();
		return jsonCarsObject.toString();
	}
	
	public static String deleteCarByCarId(String userid,String carId){
		String RegexNumbers = "\\d+";
		if(userid.matches(RegexNumbers) && carId.matches(RegexNumbers))
		{
			Integer carIdInt = Integer.parseInt(carId);
			Integer useridInt = Integer.parseInt(userid);
			
		
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try
			{
				Class.forName(JDBC_Driver);
				con = DriverManager.getConnection(DB_Prefix + dbHost + ":" + dbPort + "/" + dbDatabase, dbUsername,	dbPassword);
				
				pstmt = con.prepareStatement("DELETE FROM cars WHERE carID=? and userid=?");
							
				pstmt.setInt(1, carIdInt);
				pstmt.setInt(2, useridInt);
				
				if(pstmt.executeUpdate() > 0)
				{
					return new JSONObject().put("success", "Sikeres törlés!").toString();
				}
				else{
					return new JSONObject().put("error", "Nincs ilyen autó, vagy ez nem is a tied...").toString();
				}
			
			
			} 
			catch (SQLException e)
			{			
				e.printStackTrace();
				return new JSONObject().put("error", "SQL Exception!").toString();
			}
			catch(Exception e){
				return new JSONObject().put("error", "Other Exception").toString();
			}
		}
		return new JSONObject().put("error", "Az évjárat, lóerő, köbcenti csak szám lehet!").toString();
	}
	
	public static String modifyCarbyId(String userid,String carId,String carVendor,String carName,String carType, String carYear, String carHp,String carCcm){
		String RegexNumbers = "\\d+";
		if(userid.matches(RegexNumbers) && carId.matches(RegexNumbers) && carYear.matches(RegexNumbers) && carHp.matches(RegexNumbers) && carCcm.matches(RegexNumbers))
		{
			Integer carIdInt = Integer.parseInt(carId);
			Integer useridInt = Integer.parseInt(userid);
			Integer carYearInt = Integer.parseInt(carYear);
			Integer carHpInt = Integer.parseInt(carHp);
			Integer carCcmInt = Integer.parseInt(carCcm);
			
		
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try
			{
				Class.forName(JDBC_Driver);
				con = DriverManager.getConnection(DB_Prefix + dbHost + ":" + dbPort + "/" + dbDatabase, dbUsername,	dbPassword);
				
				pstmt = con.prepareStatement("UPDATE cars SET carVendor=?,carName=?,carType=?,carYear=?,carHp=?,carCcm=? WHERE carID=? and userid=?");
				
				pstmt.setString(1, carVendor.toUpperCase());
				pstmt.setString(2, carName.toUpperCase());
				pstmt.setString(3, carType.toUpperCase());
				pstmt.setInt(4, carYearInt);
				pstmt.setInt(5, carHpInt);
				pstmt.setInt(6, carCcmInt);
				pstmt.setInt(7, carIdInt);
				pstmt.setInt(8, useridInt);
				
				if(pstmt.executeUpdate() > 0)
				{
					return new JSONObject().put("success", "Sikeres módosítás!").toString();
				}
				else{
					return new JSONObject().put("error", "Nincs ilyen autó, vagy ez nem is a tied...").toString();
				}
			
			
			} 
			catch (SQLException e)
			{			
				e.printStackTrace();
				return new JSONObject().put("error", "SQL Exception!").toString();
			}
			catch(Exception e){
				return new JSONObject().put("error", "Other Exception").toString();
			}
		}
		return new JSONObject().put("error", "Az évjárat, lóerő, köbcenti csak szám lehet!").toString();
	}
	
	public static String addNewCar(String userid,String carVendor,String carName,String carType, String carYear, String carHp,String carCcm)
	{
		String RegexNumbers = "\\d+";
		if(userid.matches(RegexNumbers) && carYear.matches(RegexNumbers) && carHp.matches(RegexNumbers) && carCcm.matches(RegexNumbers))
		{
			Integer useridInt = Integer.parseInt(userid);
			Integer carYearInt = Integer.parseInt(carYear);
			Integer carHpInt = Integer.parseInt(carHp);
			Integer carCcmInt = Integer.parseInt(carCcm);
			
		
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try
			{
				Class.forName(JDBC_Driver);
				con = DriverManager.getConnection(DB_Prefix + dbHost + ":" + dbPort + "/" + dbDatabase, dbUsername,	dbPassword);
				
				pstmt = con.prepareStatement("INSERT INTO cars (userid,carVendor,carName,carType,carYear,carHp,carCcm) values(?,?,?,?,?,?,?)");
				
				pstmt.setInt(1,useridInt);
				pstmt.setString(2, carVendor.toUpperCase());
				pstmt.setString(3, carName.toUpperCase());
				pstmt.setString(4, carType.toUpperCase());
				pstmt.setInt(5, carYearInt);
				pstmt.setInt(6, carHpInt);
				pstmt.setInt(7, carCcmInt);
								
				
				if(pstmt.executeUpdate() > 0)
				{
					return new JSONObject().put("success", "Autó Sikeresen hozzáadva!").toString();
				}
				else{
					return new JSONObject().put("error", "Hiba az autó hozzáadása során...").toString();
				}
			
			
			} 
			catch (SQLException e)
			{			
				e.printStackTrace();
				return new JSONObject().put("error", "SQL Exception!").toString();
			}
			catch(Exception e){
				return new JSONObject().put("error", "Other Exception").toString();
			}
		}
		return new JSONObject().put("error", "Az évjárat, lóerő, köbcenti csak szám lehet!").toString();
		
	}
}
