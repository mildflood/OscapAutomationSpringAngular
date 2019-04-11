

package hcqis.cloud.automation.model.services.factory;




import javax.naming.Context;
import javax.naming.InitialContext;

import hcqis.cloud.automation.model.business.exception.ServiceLoadException;
import hcqis.cloud.automation.model.services.IService;



public class ServiceFactory {  
	
	
    /**
     * 
     */
	private ServiceFactory() {  
		
	}
      
	
	
    private static ServiceFactory serviceFactoryInstance;   
    

    public static ServiceFactory getInstance() {
    	
    
        serviceFactoryInstance = new ServiceFactory();

        return serviceFactoryInstance;
    }

    
    
    /**
     * @param serviceName
     * @return
     * @throws ServiceLoadException
     */
    public IService getService(String serviceName) throws ServiceLoadException {

        try {

            Class <?> c = Class.forName(getImplName(serviceName)); 
            
            return (IService) c.newInstance();
            
        } catch (Exception exception) {
        	
            serviceName = serviceName + " " + "not Loaded";
            
            throw new ServiceLoadException(serviceName, exception);
        }

    }

    
   
	
	 /**
     * @author Jonas Okwara
     * a lookup method to return 
     * the service    
     * @param serviceName
     * @return implementation service
     * @throws Exception
     */

	private String getImplName(String serviceName)throws Exception {
  	  Context initContext =  new InitialContext();
  	  Context envContext = (Context) initContext.lookup("java:/comp/env");
  	  	  return (String) envContext.lookup(serviceName);
  	     	  
    }
  
	
  
	
}
    
  