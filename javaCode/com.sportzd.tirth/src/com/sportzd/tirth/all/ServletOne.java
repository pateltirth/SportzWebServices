package com.sportzd.tirth.all;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;

import com.sportzd.tirth.dao.*;

@Path("/v1/test/")
public class ServletOne{

	@Path("/cities/")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service for SPORTZ DEMO</p>";
	}
	
	
	
	@Path("/database/")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseObjects() throws Exception {
		
		PreparedStatement query = null; 
		String myString = null;
		String returnString = null;
		Connection conn = null ; 
		//JSONArray json = new JSONArray();
		
		try {
			//conn = SportzDatabase.sportzdemoConn().getConnection("jdbc:mysql://localhost:3306/sportzdemo", "root");
			conn = SportzDatabase.sportzdDatabaseConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME from sys.dual");
			//query = conn.prepareStatement("select name from test");
			ResultSet rs = query.executeQuery();
			
			while(rs.next()){
				myString = rs.getString("DATETIME");
			}
			
			query.close();			
			returnString = "<p>Database DateTime is: " + myString + "</p>";

		}
		catch (Exception e) {
				e.printStackTrace();
		}
		finally{
			if(conn != null )  conn.close();
		}
		return returnString;
	}
}
