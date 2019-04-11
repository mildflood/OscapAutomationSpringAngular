package hcqis.cloud.automation.model.services.scanschedulerservice;

import hcqis.cloud.automation.model.services.IService;



public interface  IScanSchedulerSvc  extends IService{
	
	public final String NAME = "IScanSchedulerSvc";
		
	public  String performHostScan(String hostname); 
}
