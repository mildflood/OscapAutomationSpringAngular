package hcqis.cloud.automation.model.business.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import hcqis.cloud.automation.model.business.exception.ServiceLoadException;
import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.services.authentication.IAuthenticationDaoSvc;
import hcqis.cloud.automation.model.services.authentication.ILdapAuthenticationSvc;
import hcqis.cloud.automation.model.services.factory.ServiceFactory;




public class AuthenticationManager { 
	
	

 	 
	 private final static Logger logger = Logger.getLogger(AuthenticationManager.class.getName()); 
	 
	 private static AuthenticationManager   authManagerInstance; 
	 ServiceFactory serviceFactory = ServiceFactory.getInstance();	
		
	 /**
	  * @author Jonas  Okwara
	  * @date 06-24-17
	  * @return  authManagerInstance
	  */
	 public static synchronized AuthenticationManager getInstance(){
	     if ( authManagerInstance == null ){
	    	 authManagerInstance = new  AuthenticationManager();
	         }
	     
	       return authManagerInstance; 
	}


	 
	/**
	* Private constructor to prevent
	* instantiation of class	 
	*/
	private AuthenticationManager(){
	   
	} 
		
	 
	/**
	 * Author Jonas Okwara
	 * Lookup the service implementation names
	 * and call appropriate methods on them
	 * @param LoginUserObj
	 * @return
	 */
	public boolean authenticateUser(User  loginUserObj) { 
		
		boolean isValid = false;
		 //authDaoSvcImpl = null;
		
		try {
			IAuthenticationDaoSvc authDaoSvcImpl = (IAuthenticationDaoSvc) serviceFactory.getService(IAuthenticationDaoSvc.NAME); 
			  isValid	= authDaoSvcImpl.authenticateByUsername(loginUserObj);
			  
				if(isValid){
				    logger.info("......User Successfully  logged in ......");	
				    }
			
		} catch (ServiceLoadException servLoadExcep) {
			
			servLoadExcep.printStackTrace();
		}	
		
		
	
	return isValid;
	}		

	/**
	 * Author Matthew Zhao
	 * Active Directory user authentication
	 * 10/05/2018
	 * @param LoginUserObj
	 * @return boolean
	 */
	public boolean ldapAuthenticateUser(User loginUserObj) {
		boolean isValid = false;
		try {
			ILdapAuthenticationSvc ldapAuthSvcImpl = (ILdapAuthenticationSvc) serviceFactory.getService(ILdapAuthenticationSvc.NAME); 
			  isValid	= ldapAuthSvcImpl.adAuthenticate(loginUserObj);
			  if (isValid) {	  
				  HashMap<String, String> userAttributes = ldapAuthSvcImpl.ldapAuthentication(loginUserObj);
				  loginUserObj.setFirstname(userAttributes.get("First Name"));
				  loginUserObj.setLastname(userAttributes.get("Last Name"));
				  loginUserObj.setPhoneNumber(userAttributes.get("Phone"));
				  loginUserObj.setEmailAddress(userAttributes.get("Email"));
				  
				  List<String> groups = new ArrayList<String>();
				  String groupStr = userAttributes.get("Groups");
				  groups = Arrays.asList(groupStr.split(":"));
				  loginUserObj.setGroups(groups);
				  logger.info("...... User Successfully logged in via Active Directory ......");		
			  } else {
				  logger.info("...... User failed login via Active Directory ......");		
			  }
		} catch (ServiceLoadException servLoadExcep) {
			servLoadExcep.printStackTrace();
		}	
		return isValid;
	} 

	 
	/**
	 * Get user roleName
	 * @param userLoginObj
	 * @return String
	 */
	public String getUserRole(User userLoginObj){
		String roleName = null;
		
		try {
		IAuthenticationDaoSvc authDataAccessObj = (IAuthenticationDaoSvc) serviceFactory.getService(IAuthenticationDaoSvc.NAME); 
	     roleName  = authDataAccessObj.isUserInRole(userLoginObj);
		}catch(ServiceLoadException servLoadExcep) {
			servLoadExcep.printStackTrace();
		}
	    return roleName;
		  
		  
	}

	 
	/**
	 * @param userLoginObj
	 * @return 
	 */
	 
	public User getNames(User  userObj){ 
		
		try {
		IAuthenticationDaoSvc authDataAccessObj = (IAuthenticationDaoSvc) serviceFactory.getService(IAuthenticationDaoSvc.NAME); 
		userObj =  authDataAccessObj.getFirstnameLastname( userObj);    
		
		}catch(ServiceLoadException servLoadExcep) {
			servLoadExcep.printStackTrace();
			
		}
		
		
	     return   userObj;
	} 

}
