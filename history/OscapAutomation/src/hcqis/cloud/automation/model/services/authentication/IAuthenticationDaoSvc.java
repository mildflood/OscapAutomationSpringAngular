 package hcqis.cloud.automation.model.services.authentication;

 import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.services.IService;

 public interface IAuthenticationDaoSvc  extends IService{   
	
  public final String NAME = "IAuthenticationDaoSvc";
  
  public boolean authenticateByUsername(User   loginUserObj);
  public String  isUserInRole(User loginUserObj); 
  public User getFirstnameLastname(User loginUserObj);

  }
