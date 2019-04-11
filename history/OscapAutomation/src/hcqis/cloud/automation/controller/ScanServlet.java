/**
 * The Servlet Controller that mediates relationship with
 * domain and business manager classes
 * Author: Jonas  Okwara
 * Date:  05-11-18
 */

package hcqis.cloud.automation.controller; 

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hcqis.cloud.automation.model.business.manager.ScanDataManager;
import hcqis.cloud.automation.model.business.manager.ScanManager;
import hcqis.cloud.automation.model.domain.ScanHost;
import hcqis.cloud.automation.model.domain.ScanSession;
import hcqis.cloud.automation.model.domain.User;


public class ScanServlet extends HttpServlet {
	
	/**
	 * No Arg Constructor
	 */
	public ScanServlet(){
		
	 }
	
	private static final long serialVersionUID = 1L; 
	private static Logger logger = Logger.getLogger("ScanServlet"); 
	String log4jConfigFile =  "config/log4j.properties";
	private ScanManager   scanManager; 
	private ScanDataManager scanDataMgr;
	private ScanSession scanSessionObj;
	private ScanHost scanHostObj;
	
       	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		processRequest(request, response);

	}    
	
	/**
	 * Process host inputs from user and call the necessary business classes
	 * 
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {  
		
    	List<String> returnedList = null;
				
		/**
		 * Get the date and time of
		 * this scan session
		 */ 
	
		Date theDate = new Date();        
        SimpleDateFormat  dateFormat = new SimpleDateFormat("EEE MMM d, yyyy");  
        SimpleDateFormat  timeFormat = new SimpleDateFormat("h:mm a");
        String   scanDate = dateFormat.format(theDate);
        String   scanTime  = timeFormat.format(theDate); 
        
        //Get the User session for this scan
        //===================================
        
        HttpSession  userSession = (HttpSession) request.getSession(true);  
        userSession.setMaxInactiveInterval(-1);
        User userObj =  (User) userSession.getAttribute("userObj"); 
        
        scanSessionObj = new ScanSession();
        scanSessionObj.setLoginUser(userObj);
        scanSessionObj.setScanDate(scanDate);
        scanSessionObj.setScanTime(scanTime); 
        
        scanHostObj = new ScanHost();
        scanHostObj.setEntryDate(scanDate);
        scanHostObj.setEntryTime(scanTime);
        
        
		/**
		 * Get the data from the JSP
		 * Client and process
		 */
		String[]  nodesArray  = request.getParameterValues("nodes");      
		List<String> nodeList = Arrays.asList(nodesArray);
				
		logger.info("Calling Business Manager and hand Over the host List");
		scanManager =  ScanManager.getInstance(); 
		scanDataMgr = ScanDataManager.getInstance(); 
		
		returnedList = scanManager.obtainHostList(nodeList);   
		
		//Clean-up un-necessary DB access
		//scanDataMgr.writeToDb(scanHostObj, returnedList, userObj);   
				
		request.setAttribute("scanSessionObj", scanSessionObj);   
		request.setAttribute("returnedList", returnedList); 
		String returnPage = "/scanGui.jsp";
		request.getServletContext().getRequestDispatcher(returnPage).forward(request, response);
	
		
		  
	    } 
	}
	



