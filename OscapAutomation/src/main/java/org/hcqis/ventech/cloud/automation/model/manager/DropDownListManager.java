/**
 * Author Jonas Okwara
 * Date: 02-14-19
 */

package org.hcqis.ventech.cloud.automation.model.manager;

import java.util.List;
import java.util.logging.Logger;

import org.hcqis.ventech.cloud.automation.exception.ServiceLoadException;
import org.hcqis.ventech.cloud.automation.model.ScanHost;
import org.hcqis.ventech.cloud.automation.service.dropdownselectorservice.IDropDownSelectorSvc;
import org.hcqis.ventech.cloud.automation.service.factory.ServiceFactory;


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
