package org.hcqis.ventech.cloud.automation.model.manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import org.hcqis.ventech.cloud.automation.exception.ServiceLoadException;
import org.hcqis.ventech.cloud.automation.model.User;
import org.hcqis.ventech.cloud.automation.service.authentication.ILdapAuthenticationSvc;
import org.hcqis.ventech.cloud.automation.service.factory.ServiceFactory;

public class AuthenticationManager {

	private final static Logger logger = Logger.getLogger(AuthenticationManager.class.getName());

	ServiceFactory serviceFactory = ServiceFactory.getInstance();

	/**
	 * Private constructor to prevent instantiation of class
	 */
	public AuthenticationManager() {

	}

	/**
	 * Author Matthew Zhao Active Directory user authentication 10/05/2018
	 * 
	 * @param LoginUserObj
	 * @return boolean
	 */
	public boolean ldapAuthenticateUser(User loginUserObj) {
		boolean isValid = false;
		try {
			ILdapAuthenticationSvc ldapAuthSvcImpl = (ILdapAuthenticationSvc) serviceFactory
					.getService(ILdapAuthenticationSvc.NAME);
			isValid = ldapAuthSvcImpl.adAuthenticate(loginUserObj);
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

}
