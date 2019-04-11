/**
 * Author Jonas  Okwara
 * Date 10-23-18
 * class that implements the
 * mail service contract as
 * stipulated by its interface
 */

package hcqis.cloud.automation.model.services.mailservice;

import hcqis.cloud.automation.model.domain.ScanScheduler;

public class MailSvcImpl implements IMailSvc {

	
	@SuppressWarnings("unused")
	private ScanScheduler  scanSchedulerObj = null;
	
	
	
	
	public boolean sendScanStatus(String emailAddress) {
	
		
		
		return false;
	}

}
