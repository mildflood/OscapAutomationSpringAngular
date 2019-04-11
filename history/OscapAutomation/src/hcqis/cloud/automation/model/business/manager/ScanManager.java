
/**
 * Author Jonas Okwara
 * Date 09-30-18
 */


package hcqis.cloud.automation.model.business.manager;


import java.util.ArrayList;
import java.util.List;

import hcqis.cloud.automation.model.business.exception.ServiceLoadException;
import hcqis.cloud.automation.model.domain.ScanScheduler;
import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.services.factory.ServiceFactory;
import hcqis.cloud.automation.model.services.scanservice.IScanServiceDaoSvc;
import hcqis.cloud.automation.model.utility.scanutility.ScanUtility;


  public class ScanManager { 
  
	  //private final static Logger logger = Logger.getLogger(ScanManager.class.getName()); 
	  
	  private static ScanManager scanMgrInstance; 
	  
	  ServiceFactory serviceFactory = ServiceFactory.getInstance();
	
	
	/**
	 * Author: Jonas Okwara 
	 * Date: 05-28-18 
	 * Method that creates only one instance
	 * of ScanManager if there is none loaded
	 * @return ScanManager
	 */  
	public static synchronized ScanManager getInstance() {
		if (scanMgrInstance == null) {
			scanMgrInstance = new ScanManager();
		}

		return scanMgrInstance;
     } 
		
	
	/**
	 * Author Jonas Okwara
	 * Lookup service 
	 * @return scanDaoSvcImpl
	 */
	private  IScanServiceDaoSvc getImplementationSvc() { 
		IScanServiceDaoSvc  scanDaoSvcImpl = null;
		
    try {
    	scanDaoSvcImpl   =  (IScanServiceDaoSvc) serviceFactory.getService(IScanServiceDaoSvc.NAME); 
    }catch(ServiceLoadException servLoadExcep) {
 	   servLoadExcep.printStackTrace(); 
    }
    
      return scanDaoSvcImpl;
	}
    
	
	
	/**
	 * Author Jonas Okwara
	 * Date 09 - 24 -2018
	 * @param userObj
	 * @param schedulerObj
	 */
	public long  writeSchdData(User userObj, ScanScheduler schedulerObj) {
		
		IScanServiceDaoSvc  scanDaoSvcImpl = getImplementationSvc(); 
		
		long jobID =  scanDaoSvcImpl.writedata(userObj, schedulerObj); 
		
		return jobID;
	}
	
	
	/**
	 * Author Jonas Okwara
	 * Date 09 - 24 -2018
	 * @param userObj
	 * @param schedulerObj
	 */
	public int  writeMultiSchedData(ScanScheduler schedulerObj) {
		
		IScanServiceDaoSvc  scanDaoSvcImpl = getImplementationSvc(); 
		
       int jobID =  scanDaoSvcImpl.writeMultiScanData(schedulerObj); 
       
       return jobID;
		
		
	}
	
	
	/**
	 * Business Manager Method that calls Ansible
	 * to run scripts against the list of Nodes
	 * @param scanList
	 */
	  public List<String> obtainHostList(List<String>  nodeList){  
		
		   ArrayList<String> statusMessage =  new ArrayList<>();
		  for(String nodeName: nodeList){
			  if(nodeName != null){ 
				  
			 statusMessage.add( new ScanUtility().performHostScan(nodeName.trim()));
		  }
		  }
		return statusMessage;
		      
	  }  
	  
	
	  
	  /**
	   * Call the appropriate database table 
	   * and update it
	   * @param message
	   * @param jobID
	   */
	  public void updateSchedDB(String message, long jobID) {
		  IScanServiceDaoSvc  dbSvcImpl = getImplementationSvc(); 	  		  
		  dbSvcImpl.writeCompletedJobResults(message,   jobID);
		  
	  }

	  
	  /**
	   * Author Jonas Okwara
	   * Get the JobId
	   * @param jobID
	   * @return jobID
	   */
	  public int getJobUniqueID(int jobID) {
		  
		  return jobID;
	  }  
	  
	   
	  
	  /**
	   * Author Jonas  Okwara
	   * Call the database and 
	   * return a list of scheduled data
	   * @return scanschedList
	   */
	  public List<ScanScheduler> readSchedData(){
		  IScanServiceDaoSvc  dbSvcImpl = getImplementationSvc();
		  List<ScanScheduler> scanschedList = dbSvcImpl.readScheduledData();
		 
		  return scanschedList; 
	  }
	  
	  
}
	  

