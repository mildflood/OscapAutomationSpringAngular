/**
 * Author Jonas Okwara
 * Date 02-14-19
 * Populate the drop down list
 * and selections within the list 
 */

package hcqis.cloud.automation.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hcqis.cloud.automation.model.business.manager.DropDownListManager;
import hcqis.cloud.automation.model.business.manager.ScanDataManager;
import hcqis.cloud.automation.model.domain.ScanHost;
import hcqis.cloud.automation.model.domain.ScanSession;

public class PopulateDropDownServlet extends HttpServlet implements Serializable{

	
	private static final long serialVersionUID = 1L;
	 ScanDataManager  scanDataMgr; 

	 
	    /**
	     * The doGet method
	     */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			processRequest(request, response);
		}
		
		    
	 
		 /**
	     * Author Jonas Okwara
	     * @param request
	     * @param response
	     * @throws ServletException
	     * @throws IOException
	     */
	    private  void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 		
		DropDownListManager  dropDownListMgr = DropDownListManager.getInstance();
		List<ScanHost> nodeList = dropDownListMgr.getHostList();
		
		request.setAttribute("nodeList", nodeList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/reportGui.jsp");
	    dispatcher.forward(request, response); 
	     
	   }

	 
	/** 
	 * process all requests objects
	 * and forward same to the doPost
	 * and doGet Objects
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
		
	     String errorMessage = null;
	    
		ScanSession  scanSessionObj = new ScanSession();
      	scanSessionObj.setServerName(request.getParameter("eachItem"));
      	String  nodename = scanSessionObj.getServerName();
      	
      	System.out.println("The value is :" + scanSessionObj.getServerName()); 
      	
      	
      	/**
      	 * Query the request and 
      	 * respond accordingly
      	 */
      	if(nodename == null) {
      		errorMessage = "Please Confirm The Server Exists";
      		request.setAttribute("errorMessage", errorMessage);
      		
      	}else {
      		
     
      	scanDataMgr = ScanDataManager.getInstance();  	    		
      	List<String> returnedFileList = scanDataMgr.getFiles(scanSessionObj);
      
      	String   nodeName = scanSessionObj.getServerName();
      	String    formattedNameString = nodeName.substring(0, 1).toUpperCase() + nodeName.substring(1);  
    	
      	request.setAttribute("eachItem", nodename);
      	request.setAttribute("returnedFileList", returnedFileList);
      	request.setAttribute("formattedNameString", formattedNameString); 
      	}
      	
      	processRequest(request, response); 
      	
    
	 
	}
	
	
	
   
	
	   
	

}
