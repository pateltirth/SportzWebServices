package com.sportzd.tirth.dao;

import javax.naming.*;
import javax.sql.*;
import java.sql.Connection;

public class SportzDatabase {
	private static DataSource sportzData = null;
	private static Context context = null;
	
	public static DataSource sportzdDatabaseConn() throws Exception {
		
		/*if (sportzData != null) {
			return sportzData;
			}*/
		try{
			if (context == null) {
				context = new InitialContext();
				}
			sportzData = (DataSource) context.lookup("tirthsportzdemo");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return sportzData;
	
	}
	
	protected static Connection spartzDatabaseConnection(){
		Connection conn = null;
		try{
			conn = sportzdDatabaseConn().getConnection();
			return conn;
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
		
	}
	
	

}
