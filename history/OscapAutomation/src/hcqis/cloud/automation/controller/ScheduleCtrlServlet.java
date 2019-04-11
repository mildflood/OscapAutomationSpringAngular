/**
 * Author Jonas Okwara
 * 
 */

package hcqis.cloud.automation.controller;


import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hcqis.cloud.automation.model.business.manager.ScanManager;
import hcqis.cloud.automation.model.business.manager.SchedManager;
import hcqis.cloud.automation.model.domain.ScanScheduler;
import hcqis.cloud.automation.model.domain.User;


public class ScheduleCtrlServlet extends  HttpServlet implements Serializable{

	private static final long serialVersionUID = 1L; 
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	   
	   ScanScheduler scanSchedulerObj = new ScanScheduler();
	   User userObj = new User(); 
	   
	   HttpSession  userSession = (HttpSession) request.getSession(true);
	 	userObj =  (User) userSession.getAttribute("userObj"); 
	 	
	 	userObj.getFirstname();
	 	userObj.getLastname();
	     
	 	
	 	/**
	 	 * Obtain inputs from
	 	 * Gui and process them
	 	 */
	 	
	   scanSchedulerObj.setNodename(request.getParameter("nodename")); 
	   
	   //scanSchedulerObj.setUser(userObj);
	   //scanSchedulerObj.setScanInitiator(userObj);
	   scanSchedulerObj.setScanAdministrator(userObj);
	   scanSchedulerObj.setGroup(request.getParameter("group"));
	   scanSchedulerObj.setDateOfScan(request.getParameter("dateOfScan"));	
	   scanSchedulerObj.setTimeOfScan(request.getParameter("timeOfScan"));
	   scanSchedulerObj.setStatus("ready");
	   scanSchedulerObj.setEmail(request.getParameter("email"));
	  
	   
	   //output The Values
	   //==================
	   System.out.println(scanSchedulerObj.getNodename());
	   System.out.println(scanSchedulerObj.getGroup());
	   System.out.println(scanSchedulerObj.getDateOfScan());
	   System.out.println(scanSchedulerObj.getTimeOfScan());
	   System.out.println(scanSchedulerObj.getEmail()); 
	   
	   
	   ScanManager   scanMgr = ScanManager.getInstance();
	   SchedManager  schedMgr = SchedManager.getInstance(); 
	   
	 //Clean-up un-necessary DB access
	  // long  jobID = scanMgr.writeSchdData(userObj, scanSchedulerObj); 
	  // scanSchedulerObj.setJobID(jobID);
	   
	   //Clean-up un-necessary DB access
	   // List<ScanScheduler>  scheduledScanList =   scanMgr.readSchedData();
	   
	   schedMgr.initializeSingleScan(scanSchedulerObj);
	   System.out.println(scanSchedulerObj.getJobID());
	   
	   
	}   
}
	



