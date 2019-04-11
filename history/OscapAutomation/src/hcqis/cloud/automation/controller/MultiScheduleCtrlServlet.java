
package hcqis.cloud.automation.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
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

public class MultiScheduleCtrlServlet extends HttpServlet implements Serializable {

	private static final long serialVersionUID = 1L;
	private ScanManager scanMgr = null;
	private SchedManager schedMgr = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);

	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		ScanScheduler scanSchedulerObj = new ScanScheduler();   
		
		User userObj = new User();

		HttpSession userSession = (HttpSession) request.getSession(true);
		userObj = (User) userSession.getAttribute("userObj");

		userObj.getFirstname();
		userObj.getLastname();

		/**
		 * Obtain inputs from Gui and process them
		 */
		String[] arrayString = request.getParameterValues("nodename");
		String[] nodesArray = arrayString[0].split("\\r?\\n");
		
		List<String> nodeList = Arrays.asList(nodesArray);

		
		// output The Values
		// ==================================
		for (String eachNode : nodeList) {
			System.out.println(eachNode);
		}

	
		scanSchedulerObj.setScanAdministrator(userObj);
		scanSchedulerObj.setGroup(request.getParameter("group"));
		scanSchedulerObj.setDateOfScan(request.getParameter("dateOfScan"));
		scanSchedulerObj.setTimeOfScan(request.getParameter("timeOfScan"));
		scanSchedulerObj.setEmail(request.getParameter("email"));
		
		String   stringValue =  request.getParameter("repeatScan");
		boolean  booleanValue = Boolean.parseBoolean(stringValue);		
		scanSchedulerObj.setRepeatScan(booleanValue); 

		System.out.println(scanSchedulerObj.getGroup());
		System.out.println(scanSchedulerObj.getDateOfScan());
		System.out.println(scanSchedulerObj.getTimeOfScan());
		System.out.println(scanSchedulerObj.getEmail());
		System.out.println(scanSchedulerObj.isRepeatScan());

		scanMgr = ScanManager.getInstance();
		schedMgr = SchedManager.getInstance();            

		
		//List<ScanScheduler> scheduledScanList = scanMgr.readSchedData();
	

		schedMgr.initializeMultiScan(scanSchedulerObj, nodeList);
		

	}

}
