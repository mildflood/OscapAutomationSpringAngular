package org.hcqis.ventech.cloud.automation.service.scheduledtaskservice;

import org.hcqis.ventech.cloud.automation.model.ScanScheduler;
import org.hcqis.ventech.cloud.automation.service.IService;


public interface IScheduledTaskSvc extends IService{ 
	
	public final String  NAME = "IScheduledTaskSvc"; 
	public final int THREAD_POOL_SIZE = 5; //adjust concurrent thread pool size to optimize performance
	
	public void executeScheduledJob(ScanScheduler schedulerObj); 
	
	public void cancelTimer();

}
