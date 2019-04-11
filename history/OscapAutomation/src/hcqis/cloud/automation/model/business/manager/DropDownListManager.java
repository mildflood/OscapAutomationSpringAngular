/**
 * Author Jonas Okwara
 * Date: 02-14-19
 */

package hcqis.cloud.automation.model.business.manager;

import java.util.List;
import java.util.logging.Logger;

import hcqis.cloud.automation.model.business.exception.ServiceLoadException;
import hcqis.cloud.automation.model.domain.ScanHost;
import hcqis.cloud.automation.model.services.dropdownselectorservice.IDropDownSelectorSvc;
import hcqis.cloud.automation.model.services.factory.ServiceFactory;

public class DropDownListManager {   
	

	  private final static Logger logger = Logger.getLogger(DropDownListManager.class.getName()); 
	  
	  private static DropDownListManager dropDownMgr; 
	  
	  ServiceFactory serviceFactory = ServiceFactory.getInstance();
	
		/**
		 * Author: Jonas Okwara 
		 * Date: 02-14-19 
		 * Method that creates only one instance
		 * of DropDownListManager if there is none loaded
		 * @return schedMgrInstance
		 */  
		public static synchronized DropDownListManager getInstance() {
			if (dropDownMgr == null) {
				dropDownMgr = new DropDownListManager();
			}

			return dropDownMgr;
	    } 

		/**
		 * Author Jonas Okwara
		 * Lookup service interface
		 * @return scanDaoSvcImpl
		 */
		private  IDropDownSelectorSvc  getImplementationSvc() { 
			IDropDownSelectorSvc  dropDownSvclmplObj = null;
	    try {
	    	dropDownSvclmplObj   =  (IDropDownSelectorSvc) serviceFactory.getService(IDropDownSelectorSvc.NAME); 
	    }catch(ServiceLoadException servLoadExcep) {
	 	   servLoadExcep.printStackTrace(); 
	    }
	    
	      return dropDownSvclmplObj;
		}
	    
	
		/**
		 * Author Jonas Okwara
		 * Date: get the list of
		 * nodes from the database
		 * @return ArrayList
		 */
		public List<ScanHost> getHostList(){
			 IDropDownSelectorSvc dropDownImplObj = getImplementationSvc();
			 
			 logger.info("Get the List of Nodes from the databse");
			 List<ScanHost> nodeList = dropDownImplObj.getDropDownList();
			
			
			return nodeList;
		}
			
	
	
	

}
