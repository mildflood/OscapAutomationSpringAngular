

package hcqis.cloud.automation.controller;


import hcqis.cloud.automation.model.business.manager.AuthenticationManager;
import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.services.authentication.AuthenticationDaoSvcImpl;
import hcqis.cloud.automation.model.services.authentication.EncryptionUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginCtrlServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	
	private final static Logger logger = Logger.getLogger(LoginCtrlServlet.class.getName()); 	
	   private AuthenticationManager loginManager;	
	 
	
	   private User loginUserObj;
	   private String returnPage = "null.jsp";
	   private String sessionMessage = null; 

	   /**
	     * Post method that accepts requests and responds
	     * @param request
	     * @param response
	     * @throws java.io.IOException
	     * @throws javax.servlet.ServletException
	     */
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	        processRequest(request, response);
	       
	      }
	   
		    
	      /**
	       *  
	       */
	      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	 
	           
	       }
	    
	
	    /**
	     * @author Jonas Okwara 
	     * method that processes user form inputs and calls
	     * Login Manager to validate credentials
	     * and forwards appropriate response to view Servlet
	     * @param request
	     * @param response
	     * @throws IOException
	     * @throws ServletException
	     */
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    	   	   		    	
			/**
	         * set domain and business 
	         * manager objects
	         */
	    	logger.info("------Processing for user Login Object Begins ------");
	    	
	    	EncryptionUtil encryptUtil = new EncryptionUtil();
	    	String userName = request.getParameter("username");
	    	String password = encryptUtil.encrypt(request.getParameter("password"));
	 	    String errorMessage = null;
	    	//String adlogin = request.getParameter("adlogin");
	    	
	    	loginUserObj = new User();
	    	loginUserObj.setUsername(userName);
	    	loginUserObj.setPassword(password);
	        loginManager =  AuthenticationManager.getInstance();	    
	        
	        boolean isValid = false;
	        String userRoleName = null;
	        String userGroupName = null;
	        
//	        if (adlogin == null){ //original DB login
//	        	isValid = loginManager.authenticateUser(loginUserObj);
//	        	userRoleName = loginManager.getUserRole(loginUserObj);
//	        } else { //AD login
//	        	isValid = loginManager.ldapAuthenticateUser(loginUserObj);
//	        	userRoleName = "admin"; //need to be removed after userGroupName is ready
//	        }
	        
	        isValid = loginManager.ldapAuthenticateUser(loginUserObj);
        	userRoleName = "admin"; //need to be removed after userGroupName is ready
        	
			Date theDate = new Date();        
	        SimpleDateFormat  dateFormat = new SimpleDateFormat("EEE MMM d, yyyy");  
	        SimpleDateFormat  timeFormat = new SimpleDateFormat("h:mm a");
	        String   loginDate = dateFormat.format(theDate);
	        String   loginTime  = timeFormat.format(theDate);
	        
	       // loginUserObj = loginManager.getNames(loginUserObj);
	        HttpSession  userSession = request.getSession(true);  
	        userSession.setMaxInactiveInterval(-1);
	        Integer accessCount = (Integer) userSession.getAttribute("accessCount");
	        userSession.setAttribute("errorMessage", errorMessage);

	        if(accessCount == null){     
	          accessCount = new Integer(0);
	          sessionMessage = "Welcome";
	        }else{
	        	sessionMessage = "Welcome Back";
	        	accessCount = new Integer(accessCount.intValue() + 1);
	          }
	        
	    //	AuthenticationDaoSvcImpl loginImpl = new AuthenticationDaoSvcImpl(); 
	     //   loginUserObj = loginImpl.getFirstnameLastname(loginUserObj);
	          	        
	       
	           /**
	            * Validate the logins and 
	            * forward user and data list objects
	            * to appropriate views
	            */
	          if ((isValid) &&  (userRoleName.equalsIgnoreCase("admin"))) {           
	        	userSession.setAttribute("loginDate", loginDate);
	        	userSession.setAttribute("loginTime", loginTime);
	        	userSession.setAttribute("userObj", loginUserObj);   
	        	userSession.setAttribute("userRoleName", userRoleName);
	        	userSession.setAttribute("sessionMessage", sessionMessage);
	        	returnPage = "/homeGui.jsp";
	        	request.getServletContext().getRequestDispatcher(returnPage).forward(request,response);
	        	//request.getRequestDispatcher(returnPage).forward(request,response);
	        	return;
	      	
	        } else if ((isValid)  &&  (userRoleName.equalsIgnoreCase("user"))){	  
	        	userSession.setAttribute("loginDate", loginDate);
	        	userSession.setAttribute("loginTime", loginTime);
	        	userSession.setAttribute("userObj", loginUserObj);   
	        	userSession.setAttribute("sessionMessage", sessionMessage);
	        	userSession.setAttribute("userRoleName", userRoleName);
	        	returnPage = "/userGui.jsp"; 
	        	
	        	request.getServletContext().getRequestDispatcher(returnPage).forward(request, response);
	        	//request.getRequestDispatcher(returnPage).forward(request,response);
	        	return;
	        	
	        } else{
	        	 errorMessage = "There is an Error with Your Login !!!";
	        	 userSession.setAttribute("errorMessage", errorMessage);
	        	 returnPage = "/loginPage.jsp";
	        }
	          request.getServletContext().getRequestDispatcher(returnPage).forward(request, response);
	          //request.getRequestDispatcher(returnPage).forward(request,response);
	        } 
}
	 
	