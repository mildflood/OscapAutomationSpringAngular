/**
 * Author Jonas  Okwara
 * Date 10-24-18 
 * A controller that accepts the list
 * of hosts, populates the database
 * then reads the list back to the 
 * JSP
 */

package hcqis.cloud.automation.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hcqis.cloud.automation.model.business.manager.SchedManager;

public class HostListCtrlServlet extends HttpServlet implements Serializable{

	
	private static final long serialVersionUID = 1L;  
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		   SchedManager  schedMgr = null;
		
		
		String[] nodeArray = request.getParameterValues("nodelist"); 
		List<String> nodeList = Arrays.asList(nodeArray);
		schedMgr = SchedManager.getInstance();
		schedMgr.obtainHostList(nodeList);
		
	}
	
	
	
	

}
