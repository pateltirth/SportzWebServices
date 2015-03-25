package com.sportzd.tirth.sportz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import weblogic.j2ee.descriptor.wl.QuotaBean;

import com.sportzd.tirth.util.*;
import com.sportzd.tirth.dao.*;

@Path("/v1/users/visits")
public class V1userRequest {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnUserList() throws Exception {
	return Response.status(400).entity("Please specify your user ID like .../users/visits/xxx ").build();
	}
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnUserList(@QueryParam("userId") String userID) throws Exception {
		
		//PreparedStatement query = null; 
		String returnString = null;
		//Connection conn = null ; 
		//Response r = null;
		JSONArray json = new JSONArray();
		
		try {
			QueryClass qc = new QueryClass();
			if(userID== null){
				json = qc.queryReturnAllUserVisitsList();
			}else{
				json = qc.queryReturnUserVisitsList(userID);
			}
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(returnString).build();
		
		}
	*/
	
	
	@Path("/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnUserListWithURL(@PathParam("userId") String userId){
		String returnString = null;
		JSONArray json = new JSONArray();
		try {
			QueryClass qc = new QueryClass();
			
			json = qc.queryReturnUserVisitsList(userId);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok(returnString).build();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserVisits(String incomingData) throws Exception {
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		QueryClass dao = new QueryClass();
	try {
			System.out.println("incomingData: " + incomingData);
	/*
	* ObjectMapper is from Jackson Processor framework
	* http://jackson.codehaus.org/
	*
	* Using the readValue method, you can parse the json from the http request
	* and data bind it to a Java Class.
	*/
			ObjectMapper mapper = new ObjectMapper();
			ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
			int http_code = dao.insertIntoVISITS(itemEntry.userId,
												itemEntry.city,
												itemEntry.state
											);
		if( http_code == 200 ) {
		//returnString = jsonArray.toString();
			returnString = "Item inserted";
		} else {
			return Response.status(500).entity("Unable to process Item").build();
		}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		return Response.ok(returnString).build();
	}

}


class ItemEntry{
	public String userId;
	public String city;
	public String state;
	
}
