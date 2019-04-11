/**
 * Author Jonas Okwara
 * Date 09-28-18
 * Controller servlet to cancel out any scheduled tasks
 */

package hcqis.cloud.automation.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hcqis.cloud.automation.model.business.manager.SchedManager;

public class CancelSchedServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;  
	private final static Logger logger = Logger.getLogger(CancelSchedServlet.class.getName()); 	
	
	 /**
	  * 
	  * @param response
	  * @param request
	  * @throws ServletException
	  * @throws IOException
	  */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response); 
		
	}
	
	
	/**
	 * 
	 * @param response
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   processRequest(request, response);
	}
	
	/**
	 * 
	 * @param response
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	  String  data = request.getParameter("cancelTask");
	  if(data != null) {
		  logger.info("..... Cancelling The Scheduled Tasks ....");  
			 SchedManager schedMgr = SchedManager.getInstance();
			 schedMgr.cancelScheduledTasks();
			    
	  }
	 
	   
   }


}
