package com.sportzd.tirth.dao;


import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.sportzd.tirth.util.ToJSON;


public class QueryClass extends SportzDatabase  {

	public JSONArray queryReturnCityList(String state) throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		try {
			conn =  spartzDatabaseConnection();
			query = conn.prepareStatement("select NAME,STATE,STATUS,LATITUDE,LONGITUDE " +
											"from CITIES " +
											"where UPPER(STATE) = ? ");
			query.setString(1, state.toUpperCase()); //protect against sql injection
			ResultSet rs = query.executeQuery();
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
		return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		return json;
		}

	
	public JSONArray queryReturnAllCityList() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		try {
			conn =  spartzDatabaseConnection();
			query = conn.prepareStatement("select NAME,STATE,STATUS,LATITUDE,LONGITUDE " +
											"from CITIES ");
			ResultSet rs = query.executeQuery();
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
		return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		return json;
		}

	/*Distance calculation formula*/
	
	static double PI = 3.141592653589793;
    static double RADIUS = 6378.16;
    
    public static double Radians(double x)
    {
        return x * PI / 180;
    }
    
    public static double DistanceBetweenPlaces(
            double lon1,
            double lat1,
            double lon2,
            double lat2)
        {
            double dlon = Radians(lon2 - lon1);
            double dlat = Radians(lat2 - lat1);

            double a = (Math.sin(dlat / 2) * Math.sin(dlat / 2)) + Math.cos(Radians(lat1)) * Math.cos(Radians(lat2)) * (Math.sin(dlon / 2) * Math.sin(dlon / 2));
            double angle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return angle * RADIUS;
        }
    
	public JSONArray queryReturnSorroundingCityList(String city, String state, int radius) {
		// TODO Auto-generated method stub
		PreparedStatement query = null;
		PreparedStatement queryForParameters = null;
		Connection conn = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn =  spartzDatabaseConnection();
			queryForParameters = conn.prepareStatement("select to_char(LATITUDE) LATITUDE, to_char(LONGITUDE) LONGITUDE from CITIES where UPPER(NAME) = ? and UPPER(STATE) = ? "); 
			queryForParameters.setString(1, city.toUpperCase());
			queryForParameters.setString(2, state);
			
			String latitude = null;
			String longitude=null;
			ResultSet rsInitial = queryForParameters.executeQuery();
			while (rsInitial.next()) {
				 latitude = rsInitial.getString("LATITUDE");
				 longitude = rsInitial.getString("LONGITUDE");
			}
			queryForParameters.close();
			System.out.println(latitude +"     "+ longitude);
			
			query = conn.prepareStatement(" select NAME, STATE, LATITUDE, LONGITUDE, DISTANCE from "+ 
					"( SELECT id,name, state, longitude, latitude, (((acos(sin((? *3.1415/180)) *"+ 
					"sin((Latitude*3.1415/180))+cos((? *3.1415/180)) *"+ 
					"cos((Latitude*3.1415/180)) * cos(((? - Longitude)*"+ 
					"3.1415/180))))*180/3.1415)*60*1.1515* 1.609344) as DISTANCE" + 
					" FROM cities) where DISTANCE < ? "); 
											//+ "where UPPER(STATE) = ? ");
			query.setString(1, latitude);//protect against sql injection
			query.setString(2, latitude);
			query.setString(3, longitude);
			query.setInt(4, radius);
			ResultSet rs = query.executeQuery();
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
		return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return json;
	}


	public JSONArray queryReturnUserVisitsList(String userID) {
		PreparedStatement query = null;
		Connection conn = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		try {
			conn =  spartzDatabaseConnection();
			query = conn.prepareStatement("select A.NAME, A.STATE, B.FIRST_NAME, B.LAST_NAME from VISITS C, CITIES A , USERS B where C.USER_ID = B.ID and C.CITY_ID = A. ID AND C.USER_ID = ? ");
			query.setString(1, userID); //protect against sql injection
			ResultSet rs = query.executeQuery();
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
		return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return json;
	}


	public JSONArray queryReturnAllUserVisitsList() {
		PreparedStatement query = null;
		Connection conn = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		try {
			conn =  spartzDatabaseConnection();
			query = conn.prepareStatement("select A.NAME, A.STATE, B.FIRST_NAME, B.LAST_NAME from VISITS C, CITIES A , USERS B where C.USER_ID = B.ID and C.CITY_ID = A. ID");
			ResultSet rs = query.executeQuery();
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
		return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return json;
	}


	public JSONArray queryReturnList(String state, String city) {
		PreparedStatement query = null;
		Connection conn = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		try {
			conn =  spartzDatabaseConnection();
			query = conn.prepareStatement("select NAME,STATE,STATUS,LATITUDE,LONGITUDE " +
											"from CITIES " +
											"where UPPER(STATE) = ? " + " and UPPER(NAME) = ? ");
			query.setString(1, state.toUpperCase()); //protect against sql injection
			query.setString(2, city.toUpperCase()); 
			ResultSet rs = query.executeQuery();
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
		return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return json;
	}
	
	
	public int insertIntoVISITS(String userId,
								  String city,
								  String state)
			throws Exception {
		
		System.out.println(userId +"    "+ city +"    "+ state);
				PreparedStatement query = null;
				PreparedStatement queryForParameters = null;
				PreparedStatement queryForParameters2 = null;
				Connection conn = null;
			try {
				conn = spartzDatabaseConnection();
				/*Check if userId is correct*/
				
				queryForParameters = conn.prepareStatement("select ID from users where ID = ? "); 
				queryForParameters.setInt(1, Integer.parseInt(userId));
				
				String varificationID = null;
				//String longitude=null;
				ResultSet rsInitial1 = queryForParameters.executeQuery();
				while (rsInitial1.next()) {
					varificationID = rsInitial1.getString("ID");
				}
				queryForParameters.close();
				System.out.println("User varification ID : " + varificationID);
				if(varificationID==null){
					return 500; // Something is not correct: Error
				}

				/*Check if userId is state and city combination is correct*/
				queryForParameters2 = conn.prepareStatement("select ID from CITIES where UPPER(NAME) = ? and UPPER(STATE) = ? "); 
				queryForParameters2.setString(1, city.toUpperCase());
				queryForParameters2.setString(2, state);
				
				String cityID = null;
				//String longitude=null;
				ResultSet rsInitial2 = queryForParameters2.executeQuery();
				while (rsInitial2.next()) {
					//cityID = Integer.toString(rsInitial2.getInt("ID"));
					cityID = rsInitial2.getString("ID");
				}
				queryForParameters2.close();
				System.out.println("City Id : " + cityID);
				if(cityID==null){
					System.out.println("Error , still City Id : NULL");
					return 500; // Something is not correct: Error
				}
				
			query = conn.prepareStatement("insert into visits " +
											"(ID, USER_ID, CITY_ID) " +
											"VALUES ( visits_sequence.nextval, ?, ? ) ");
			query.setInt(1, Integer.parseInt(userId));
			query.setInt(2, Integer.parseInt(cityID));
			

			query.executeUpdate(); 
			} catch(Exception e) {
				e.printStackTrace();
				return 500; // Something is not correct: Error
							//if a error occurs, return a 500
				
			}
			finally {
				if (conn != null) conn.close();
			}
			return 200; //All is well
		}
}
