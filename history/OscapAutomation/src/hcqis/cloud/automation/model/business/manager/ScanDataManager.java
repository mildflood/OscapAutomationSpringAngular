/**
 * Author Jonas  Okwara
 * Date 05-29-18
 */

package hcqis.cloud.automation.model.business.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import hcqis.cloud.automation.model.business.exception.ServiceLoadException;
import hcqis.cloud.automation.model.domain.ScanHost;
import hcqis.cloud.automation.model.domain.ScanScheduler;
import hcqis.cloud.automation.model.domain.ScanSession;
import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.services.dataservice.IHostDataDaoSvc;
import hcqis.cloud.automation.model.services.factory.ServiceFactory;
import hcqis.cloud.automation.model.utility.fileutility.GeneratedFileUtility;







public class ScanDataManager {

	//private final static Logger logger = Logger.getLogger(ScanDataManager.class); 
	
	
	 
	 //static {
	//	 Log4JInitialization.initializeLog4J();
	// }
	
	  private static final Logger logger = Logger.getLogger(ScanDataManager.class);
	  
	  

	private static ScanDataManager upLoadMgrInstance; 
	 ServiceFactory serviceFactory = ServiceFactory.getInstance();
	 private ScanSession  scanSessionObj;

	public ScanSession getScanSessionObj() {
		return scanSessionObj;
	}

	public void setScanSessionObj(ScanSession scanSessionObj) {
		this.scanSessionObj = scanSessionObj;
	}

	/**
	 * Author: Jonas Okwara 
	 * Date: 05-28-18 
	 * Method that creates only one instance
	 * of UpLoadDataManager if there is none loaded
	 * @return UpLoadDataManager
	 */  
	
	public static synchronized ScanDataManager getInstance() {
		if (upLoadMgrInstance == null) {
			upLoadMgrInstance = new ScanDataManager();
		}

		return upLoadMgrInstance;
	}

	/**
	 * private constructor to prevent Object initialization
	 */
	private ScanDataManager() {

	}
	
	
	/**
	 * 
	 * @return IHostDataDaoSvc  
	 */
	private  IHostDataDaoSvc getImplementationSvc() { 
		IHostDataDaoSvc  hostDaoSvcImpl = null;
    try {
    	  hostDaoSvcImpl   =  (IHostDataDaoSvc) serviceFactory.getService(IHostDataDaoSvc.NAME); 
    }catch(ServiceLoadException servLoadExcep) {
 	   servLoadExcep.printStackTrace(); 
    }
    
      return hostDaoSvcImpl;
	}
    
	
	
	/**
	 * Author Jonas Okwara
	 * Date 06-18-18
	 * Method to write data to database
	 * @param scanHostObj
	 * @param returnedListObj
	 * @param userObj
	 * @return void
	 */
	public void writeToDb(ScanHost scanHostObj, List<String> returnedListObj, User userObj){
		IHostDataDaoSvc  hostDaoSvc = getImplementationSvc();
	     String[] listValues = null;
	     String scanStatus = null;
	     String nodeName = null;
	   for(String valueString: returnedListObj){
		  
	   
	   listValues    =   valueString.split("-");
	   scanStatus =   listValues[0];
	   nodeName   =   listValues[1];
	   scanHostObj.setHostname(nodeName);
	   scanHostObj.setHostStatus(scanStatus);		
	   hostDaoSvc.writeScanData(scanHostObj, userObj); 
		   
	  }
	}
	

	/**
	 * Author Jonas Okwara 
	 * Date 06-18-18
	 * @return List<String>
	 */
	public List<ScanSession> getNodeList() {
		IHostDataDaoSvc hostDataSvcImp = getImplementationSvc();
		List<ScanSession> nodeListObj = hostDataSvcImp.getScanData(); 
		
		return nodeListObj;
	}
		
	/**
     * Author Jonas Okwara
     * Date 08-16-18
     * @param scanHostObj
     * @return List
     */
       public void callScheduler(ScanHost  scanHostObj, User userObj, ScanScheduler schedulerObj){
    	 
    	//new ScheduledTask(scanHostObj.getHostname()).executeScheduledScanJob(scanHostObj, schedulerObj);   
        }
    
       
       
       
       /**
        * method that writes Non scheduled
        * scan data to database
        * @param scanHostObj
        * @param userObj
        * @return
        */
        //public boolean  writeScanData(ScanHost  scanHostObj, User userObj){ 
        	//HostDataDaoSvcImpl  hostDataSvcImp = new  HostDataDaoSvcImpl(); 	
           //boolean  isValid =  hostDataSvcImp.writeScanData(scanHostObj, userObj); 	
           
          // return isValid;
       // }
        	
       
       
       /**
        * Author Jonas Okwara
        * Date 08-23-18
        * read data On scheduled scan
        * from the database and return
        * same
        */
       //public List<Scheduler>  readScheduledData(){
    	   IHostDataDaoSvc hostDataSvcImp = getImplementationSvc();
    	   //List<Scheduler> schduledScanList = hostDataSvcImp.readScheduledScanData();
    	   //List<Scheduler> schduledScanList = hostDataSvcImp.readCompletedScanData();
    	   
    	   //return schduledScanList;
    	   
     //  }
       
    
      /**
       * Take in a String result output
       * and write it to the database
       * @param resultOutput
       */
       public void writeScheduledScanResults(String  resultOutput) {
    	   //IHostDataDaoSvc  hostDataSvcImp = getImplementationSvc(); 
    	   
    	   ScanHost scanHostObj = new ScanHost();
    	   
	    	  String[]  listValues    =   resultOutput.split("-");	 
	    	  
	   	      String scanStatus =   listValues[0];
	   	      String nodeName   =   listValues[1];
	   	      scanHostObj.setHostname(nodeName);
	   	      scanHostObj.setHostStatus(scanStatus);
    	   
    	    //hostDataSvcImp.writeCompletedScanResults(scanHostObj);
    	  
    	   
       }
    
    
	/**
	 * Author Jonas  Okwara
	 * Date 06-28-2018
	 * @param scanSessionObj
	 * @return  generatedList
	 */
	public List<String> getFiles(ScanSession scanSessionObj){ 	
	 String  nodename = scanSessionObj.getServerName();
	  List<String> fileList = new ArrayList<>();  
	
		 List<String> dataList  = GeneratedFileUtility.processGeneratedFiles(nodename); 
		 for(String dataStrg: dataList){
			 if(dataStrg != null)
			   logger.info(dataStrg);	 
			 fileList.add(dataStrg);
	
	  }
	return fileList;
		
	}

}
