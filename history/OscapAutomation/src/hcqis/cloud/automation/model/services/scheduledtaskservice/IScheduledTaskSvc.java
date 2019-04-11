package hcqis.cloud.automation.model.services.scheduledtaskservice;

import hcqis.cloud.automation.model.domain.ScanScheduler;
import hcqis.cloud.automation.model.services.IService;

public interface IScheduledTaskSvc extends IService{ 
	
	public final String  NAME = "IScheduledTaskSvc"; 
	public final int THREAD_POOL_SIZE = 5; //adjust concurrent thread pool size to optimize performance
	
	public void executeScheduledJob(ScanScheduler schedulerObj); 
	
	public void cancelTimer();

}
