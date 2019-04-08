
/**
 * Author Jonas  Okwara
 * Date 09-28-18
 * business manager that handles
 * scheduler implementation using 
 * service factory class
 */

package org.hcqis.ventech.cloud.automation.model.manager;

import java.util.List;

import javax.mail.MessagingException;

import org.hcqis.ventech.cloud.automation.exception.ServiceLoadException;
import org.hcqis.ventech.cloud.automation.model.ScanScheduler;
import org.hcqis.ventech.cloud.automation.service.dataservice.HostDataDaoSvcImpl;
import org.hcqis.ventech.cloud.automation.service.factory.ServiceFactory;
import org.hcqis.ventech.cloud.automation.service.scheduledtaskservice.IScheduledTaskSvc;
import org.hcqis.ventech.cloud.automation.service.scheduledtaskservice.ScheduledTaskSvcImpl;
import org.hcqis.ventech.cloud.automation.util.fileutility.FileTransferUtility;
import org.hcqis.ventech.cloud.automation.util.mailutility.MailCommunication;
import org.hcqis.ventech.cloud.automation.util.serialization.JSONSerializationUtility;


public class SchedManager { 	
	
	 //private final static Logger logger = Logger.getLogger(SchedManager.class.getName()); 
	  
	  private static SchedManager schedMgrInstance; 
	  
	  ServiceFactory serviceFactory = ServiceFactory.getInstance();
	
	
	/**
	 * Author: Jonas Okwara 
	 * Date: 05-28-18 
	 * Method that creates only one instance
	 * of DbActivityManager if there is none loaded
	 * @return schedMgrInstance
	 */  
	public static synchronized SchedManager getInstance() {
		if (schedMgrInstance == null) {
			schedMgrInstance = new SchedManager();
		}

		return schedMgrInstance;
    } 
		
	
	
	/**
	 * Author Jonas Okwara
	 * Lookup service interface
	 * @return scanDaoSvcImpl
	 */
	private  IScheduledTaskSvc getImplementationSvc() { 
		IScheduledTaskSvc  schedSvcImplObj = null;
    try {
    	schedSvcImplObj   =  (IScheduledTaskSvc) serviceFactory.getService(IScheduledTaskSvc.NAME); 
    }catch(ServiceLoadException servLoadExcep) {
 	   servLoadExcep.printStackTrace(); 
    }
    
      return schedSvcImplObj;
	}
    
	
	
	 /**
	  * 
	  * @param scanSchedulerObj
	  */
	 public void initializeSingleScan(ScanScheduler scanSchedulerObj) {			 
		
			 IScheduledTaskSvc  schedSvcImplObj = getImplementationSvc();
			 
			 new ScheduledTaskSvcImpl(scanSchedulerObj); 
			
			 schedSvcImplObj.executeScheduledJob(scanSchedulerObj); 
	 } 
			 
	
	
	/**
	 * Author Jonas  Okwara
	 * Date 11-08-18 
	 * serialize the nodeList arrayList into 
	 * json then pass the schedule object to
	 * the scheduler
	 * @param scanSchedulerObj
	 * @param nodeList
	 */
	public void initializeMultiScan(ScanScheduler scanSchedulerObj, List<String> nodeList) {  
		
		serializeListObjecToJsonDB(nodeList); 
		
		serializeObjectToJsonDB(scanSchedulerObj);
		
		
		IScheduledTaskSvc  schedSvcImplObj = getImplementationSvc();
		 
		 new ScheduledTaskSvcImpl(scanSchedulerObj); 
		
		 schedSvcImplObj.executeScheduledJob(scanSchedulerObj); 
		
	}
	 
	 
	 
	 
	 /**
		 * Business Manager Method that calls database
		 * service method to write node list to table
		 * @param boolean
		 */
		  public boolean obtainHostList(List<String>  nodeList){  
			   boolean statusMessage = false; 
			   
			  for(String nodeName: nodeList){
				  if(nodeName != null){
					  
			     statusMessage =  new HostDataDaoSvcImpl().populateHostTb(nodeName);
			  }
			  }
			return statusMessage;
			      
		  }  
	 
	

	 /**
	  * call method to cancel tasks
	  */
	  public void cancelScheduledTasks() {
		  IScheduledTaskSvc  schedSvcImplObj = getImplementationSvc();
		  schedSvcImplObj.cancelTimer();
		  
	  } 
	  
	  /**
	   * Call method to serialize Java 
	   * arrayList Object
	   * to json Object
	   * @param hostList
	   */
	  private void serializeListObjecToJsonDB(List<String> hostList) {
		  new JSONSerializationUtility().convertJavaListToJSON(hostList);
	  }    
	
	  
	  
	  /**
	   * Call method to serialize Java 
	   * arrayList Object
	   * to json Object
	   * @param hostList
	   */
	  private void serializeObjectToJsonDB(ScanScheduler scanSchedObj) {
		  new JSONSerializationUtility().convertJavaObjToJSON(scanSchedObj);
	  }    
	
	  
	  
	  /**
	   * Call the serialization utility
	   * to convert the json object to 
	   * java arrayList object
	   * and 
	   * @return
	   */
	  public List<String> getJavaListObjectFromJson() {  
	
		List<String> nodeList = new  JSONSerializationUtility().convertJSONToJavaList();  
		
		return nodeList; 
		
	  }
	  
	  
	  
	  /**
	   * Call the serialization utility
	   * to convert the json object to 
	   * java arrayList object
	   * and 
	   * @return
	   */
	  public ScanScheduler getJavaObjectFromJson() {  
	
		ScanScheduler scanSchedObj =  new  JSONSerializationUtility().convertJSONToJavaObj();  
		
		return scanSchedObj; 
		
	  }
	  
	  
	  
	 
	  /**
	   * Get the status of each 
	   * scan and send a message to
	   * scan administrator
	   * @param schedObj
	   * @param statusMessage
	   */
	 public void sendStatusMessage(ScanScheduler schedObj,  String statusMessage) {    	    
		    String   sender = schedObj.getEmail();
	    	String   receiver = schedObj.getEmail();
	    	String   theSubject = "Scan Status Message";
	    	String   theMessage = statusMessage;		 
		 
		 try {
			MailCommunication.sendMail(sender, receiver, theSubject, theMessage, false );
		} catch (MessagingException messageExcep) {
			
			messageExcep.printStackTrace();
		}
	 }
	
	 /**
	  * Retrieve Generated HTML files
	  * @param nodeName
	  */
	 public void retrieveGeneratedFile(String    nodeName) {
	  FileTransferUtility  fileTransfer = new 	FileTransferUtility(); 
	  fileTransfer.retrieveFile(nodeName);
	  }
	 
	 
}
