package com.sportzd.tirth.sportz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.QueryParam;

import org.codehaus.jettison.json.JSONArray;

import com.sportzd.tirth.util.*;
import com.sportzd.tirth.dao.*;

import com.sportzd.tirth.dao.QueryClass;

@Path("/v1/state/cities.json")
public class V1RequestHandler {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnCityList(@QueryParam("state") String state) throws Exception {
		
		//PreparedStatement query = null; 
		String returnString = null;
		//Connection conn = null ; 
		//Response r = null;
		JSONArray json = new JSONArray();
		
		try {
			QueryClass qc = new QueryClass();
			if(state== null){
				json = qc.queryReturnAllCityList();
				/*// We can specify a proper error method for handling bad requests.
				return Response.status(400).entity("Please specify state of which you need list of cities.").build();
				*/
				
			}else{
				json = qc.queryReturnCityList(state);
			}
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(returnString).build();
		
		}

	@Path("/{state}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnCityListWithURL(@PathParam("state") String state){
		String returnString = null;
		JSONArray json = new JSONArray();
		try {
			QueryClass qc = new QueryClass();
			
			json = qc.queryReturnCityList(state);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok(returnString).build();
	} 
	
	@Path("/{state}/{city}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnCityListWithURL(@PathParam("state") String state,@PathParam("city") String city){
		String returnString = null;
		JSONArray json = new JSONArray();
		try {
			QueryClass qc = new QueryClass();
			
			json = qc.queryReturnList(state,city);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok(returnString).build();
	} 
	
	@Path("/{state}/{city}/{radius}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnNearByCityListWithURL(@PathParam("state") String state,@PathParam("city") String city, @PathParam("radius") int radius){
		String returnString = null;
		JSONArray json = new JSONArray();
		try {
			QueryClass qc = new QueryClass();
			json = qc.queryReturnSorroundingCityList(city,state,radius);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.ok(returnString).build();
	} 
	
	
	
	@Path("/radius")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnSorroundingCityList(@QueryParam("city") String city, @QueryParam("state") String state, @QueryParam("radius") int radius) throws Exception {
		
		//PreparedStatement query = null; 
		String returnString = null;
		//Connection conn = null ; 
		//Response r = null;
		JSONArray json = new JSONArray();
		
		try {
			
			QueryClass qc = new QueryClass();
			json = qc.queryReturnSorroundingCityList(city,state,radius);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(returnString).build();
		
		}

	
}
