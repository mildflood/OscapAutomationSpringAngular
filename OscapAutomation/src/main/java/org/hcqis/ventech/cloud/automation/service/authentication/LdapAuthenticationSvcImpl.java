package org.hcqis.ventech.cloud.automation.service.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.hcqis.ventech.cloud.automation.model.User;


public class LdapAuthenticationSvcImpl implements ILdapAuthenticationSvc {

	private final static Logger logger = Logger
			.getLogger(LdapAuthenticationSvcImpl.class.getName());

	/**
	 * Author Matthew Zhao Simple Active Directory authentication
	 * 
	 * @param LoginUserObj
	 * @return boolean
	 */
	public boolean adAuthenticate(User loginUserObj) {

		boolean isValid = false;
		EncryptionUtil encryptUtil = new EncryptionUtil();

		if (loginUserObj == null) {
			logger.warning("login user object is null.");
		} else {
			String principalName = loginUserObj.getUsername() + "@" + AD_DOMAIN;
			String password = loginUserObj.getPassword();
			try {
				Hashtable<String, String> env = new Hashtable<String, String>();
				env.put(Context.INITIAL_CONTEXT_FACTORY,
						"com.sun.jndi.ldap.LdapCtxFactory");
				env.put(Context.PROVIDER_URL, AD_URL);
				env.put(Context.SECURITY_AUTHENTICATION, "simple");
				env.put(Context.SECURITY_PRINCIPAL, principalName);
				env.put(Context.SECURITY_CREDENTIALS,
						encryptUtil.decrypt(password));
				env.put(Context.SECURITY_PROTOCOL, "TLS");

				DirContext ctx = new InitialDirContext(env);
				ctx.close();
				isValid = true;
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return isValid;
	}

	/**
	 * Author Matthew Zhao AD authentication and user attributes return
	 * 
	 * @param LoginUserObj
	 * @return HashMap
	 */
	public HashMap<String, String> ldapAuthentication(User loginUserObj) {
		HashMap<String, String> userAttributes = new HashMap<String, String>();
		if (loginUserObj != null) {
			String username = loginUserObj.getUsername();
			String password = loginUserObj.getPassword();
			DirContext ctx = connectToLDAP(username, password);
			userAttributes = getUserLdapAttributes(username, ctx);
		}
		return userAttributes;
	}

	/**
	 * Author Matthew Zhao Retrieve user attributes from Active Directory
	 * 
	 * @param LoginUserObj
	 * @return HashMap
	 */
	private HashMap<String, String> getUserLdapAttributes(String username,
			DirContext ctx) {
		HashMap<String, String> userAttributes = new HashMap<String, String>();

		try {
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrIDs = { AD_ATTR_NAME_DISTINGUISHED_NAME,
					AD_ATTR_NAME_FIRSTNAME, AD_ATTR_NAME_LASTNAME,
					AD_ATTR_NAME_EMAIL, AD_ATTR_NAME_TELEPHONE,
					AD_ATTR_NAME_MEMBER_OF, AD_ATTR_NAME_FULL_NAME };
			controls.setReturningAttributes(attrIDs);
			String base = "DC=QUALNET,DC=ORG";
			String filter = "(&(&(objectClass=person)(objectCategory=user))(sAMAccountName="
					+ username + "))";

			NamingEnumeration<SearchResult> answer = ctx.search(base, filter,
					controls);

			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				userAttributes.put("Distinguished Name",
						attrs.get(AD_ATTR_NAME_DISTINGUISHED_NAME).get().toString());
				userAttributes.put("Last Name", attrs.get(AD_ATTR_NAME_LASTNAME).get().toString());
				userAttributes.put("First Name", attrs.get(AD_ATTR_NAME_FIRSTNAME).get().toString());
				userAttributes.put("Email", attrs.get(AD_ATTR_NAME_EMAIL).get().toString());
				userAttributes.put("Phone", attrs.get(AD_ATTR_NAME_TELEPHONE).get().toString());
				userAttributes.put("Full Name", attrs.get(AD_ATTR_NAME_FULL_NAME).get().toString());

				StringBuffer groups = new StringBuffer();
				Attribute memberOf = attrs.get(AD_ATTR_NAME_MEMBER_OF);
				if (memberOf != null) {// null if this user belongs to no group
										// at all
					for (int i = 0; i < memberOf.size(); i++) {
						Attributes atts = ctx.getAttributes(memberOf.get(i)
								.toString(), new String[] { "CN" });
						Attribute att = atts.get("CN");
						groups.append(att.get().toString() + ':');
					}
				}
				userAttributes.put("Groups", groups.toString());

				for (String name : userAttributes.keySet()) {
					String key = name.toString();
					String value = userAttributes.get(name).toString();
					logger.info("---------------------------------------");
					logger.info(key + ": " + value);
				}
				logger.info("---------------------------------------");

			} else {
				throw new Exception("Invalid User");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			closeConnectionToLDAP(ctx);
		}

		logger.info(" --------- User " + username
				+ " LDAP attributes have been retrieved. -------------");

		return userAttributes;
	}

	/**
	 * Author Matthew Zhao Active Directory authentication
	 * 
	 * @param LoginUserObj
	 * @return DirContext
	 */
	private DirContext connectToLDAP(String username, String password) {
		EncryptionUtil encryptUtil = new EncryptionUtil();
		DirContext ctx = null;

		String principalName = username + "@" + AD_DOMAIN;

		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, AD_URL);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, principalName);
			env.put(Context.SECURITY_CREDENTIALS, encryptUtil.decrypt(password));
			env.put(Context.SECURITY_PROTOCOL, "TLS");

			ctx = new InitialDirContext(env);
			logger.info(" --------- User " + username
					+ " has successfully login via LDAP. -------------");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ctx;
	}

	private static void closeConnectionToLDAP(DirContext ctx) {
		try {
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		logger.info(" --------- LDAP Context has been closed. -------------");
	}
}
