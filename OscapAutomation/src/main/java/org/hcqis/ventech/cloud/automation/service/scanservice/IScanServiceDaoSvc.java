package org.hcqis.ventech.cloud.automation.service.scanservice;

import java.util.List;

import org.hcqis.ventech.cloud.automation.model.ScanScheduler;
import org.hcqis.ventech.cloud.automation.service.IService;

/**
 * 
 * @author okwaraj
 *
 */
public interface IScanServiceDaoSvc extends IService {
	
	public final String NAME  = "IScanServiceDaoSvc";
	
	public List<ScanScheduler> readMultScan();
	public int  writeMultiScanData(ScanScheduler scanSchedObj);  
}
