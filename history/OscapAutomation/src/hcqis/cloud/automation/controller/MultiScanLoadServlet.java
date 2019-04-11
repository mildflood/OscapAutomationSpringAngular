/**
 * Author  Jonas Okwara
 * Date 11-19-18
 * 
 */

package hcqis.cloud.automation.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hcqis.cloud.automation.model.domain.ScanScheduler;
import hcqis.cloud.automation.model.services.scanservice.ScanServiceDaoSvcImpl;

public class MultiScanLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;  
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		processRequest(request, response);		

	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		processRequest(request, response);	

	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException { 
			
		response.setContentType("application/json"); 
	    PrintWriter outData  = response.getWriter();	   
	
 
	    /**
		 * Author Jonas  Okwara
		 * Date 10-09-2018
		 * Call the business manager Object
		 * to return a list of scheduled objects
		 * then serialize these arrayList Objects into JSON
		 * and write out the JSON data
		 */
		 ScanServiceDaoSvcImpl  daoImpl = new  ScanServiceDaoSvcImpl();
	 	 List<ScanScheduler> multiScanList = daoImpl.readMultScan(); 
	 	
	 	//Serialize into JSON Objects
	 	//==============================================
	 	Gson  gSon = new GsonBuilder().setPrettyPrinting().create();
		    String  jsonMultiData = gSon.toJson(multiScanList);  	
		 
		outData.write(jsonMultiData);
	 
	 } 
		
		

	}


