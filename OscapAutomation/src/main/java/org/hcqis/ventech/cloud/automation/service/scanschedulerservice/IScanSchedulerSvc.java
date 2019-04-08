package org.hcqis.ventech.cloud.automation.service.scanschedulerservice;

import org.hcqis.ventech.cloud.automation.service.IService;

public interface  IScanSchedulerSvc  extends IService{
	
	public final String NAME = "IScanSchedulerSvc";
		
	public  String performHostScan(String hostname); 
}
