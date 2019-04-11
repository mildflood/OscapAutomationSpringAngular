package hcqis.cloud.automation.model.services.authentication;

import java.util.HashMap;

import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.services.IService;

public interface ILdapAuthenticationSvc extends IService {
	public final String NAME = "ILdapAuthenticationSvc";
	
    public static final String AD_ATTR_NAME_OBJECT_CLASS = "objectClass";
    public static final String AD_ATTR_NAME_OBJECT_CATEGORY = "objectCategory";
    public static final String AD_ATTR_NAME_SAMACCOUNTNAME = "sAMAccountName";
    public static final String AD_ATTR_NAME_MEMBER_OF = "memberOf";
    public static final String AD_ATTR_NAME_DISTINGUISHED_NAME = "distinguishedName";
    public static final String AD_ATTR_NAME_LASTNAME = "sn";
    public static final String AD_ATTR_NAME_FIRSTNAME = "givenname";
    public static final String AD_ATTR_NAME_FULL_NAME = "name";
    public static final String AD_ATTR_NAME_EMAIL = "mail";
    public static final String AD_ATTR_NAME_SAM_ACCOUNT_TYPE = "sAMAccountType";
    public static final String AD_ATTR_NAME_TELEPHONE = "telephonenumber";

	public static final String AD_URL = "ldap://c3qn102.qualnet.org:3268";
	public static final String AD_DOMAIN = "qnet";
	
	public boolean adAuthenticate(User loginUserObj);
	public HashMap<String, String> ldapAuthentication(User loginUserObj);
}
