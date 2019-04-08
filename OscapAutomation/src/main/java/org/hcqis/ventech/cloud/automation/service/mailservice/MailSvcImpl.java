/**
 * Author Jonas  Okwara
 * Date 10-23-18
 * class that implements the
 * mail service contract as
 * stipulated by its interface
 */

package org.hcqis.ventech.cloud.automation.service.mailservice;

import org.hcqis.ventech.cloud.automation.model.ScanScheduler;

public class MailSvcImpl implements IMailSvc {
	@SuppressWarnings("unused")
	private ScanScheduler  scanSchedulerObj = null;
	
	public boolean sendScanStatus(String emailAddress) {	
		return false;
	}
}
