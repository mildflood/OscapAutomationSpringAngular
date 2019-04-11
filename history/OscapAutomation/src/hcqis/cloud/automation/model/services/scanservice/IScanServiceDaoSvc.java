package hcqis.cloud.automation.model.services.scanservice;



import java.util.List;

import hcqis.cloud.automation.model.domain.ScanScheduler;
import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.services.IService;


/**
 * 
 * @author okwaraj
 *
 */
public interface IScanServiceDaoSvc extends IService {
	
	public final String NAME  = "IScanServiceDaoSvc";

	public void  writeScheduledData( User userObj, ScanScheduler schedulerObj);
	
	public List<ScanScheduler> readScheduledData();
	
	public long  writedata(User userObj, ScanScheduler scanSchedObj);  
	
	public int  writeMultiScanData(ScanScheduler scanSchedObj);  
	
	public void writeCompletedJobResults(String message,  long jobID);
	
	
}
