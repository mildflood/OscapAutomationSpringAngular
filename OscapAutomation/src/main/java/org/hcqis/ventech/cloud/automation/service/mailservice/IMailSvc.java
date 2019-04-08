package org.hcqis.ventech.cloud.automation.service.mailservice;

import org.hcqis.ventech.cloud.automation.service.IService;


public interface IMailSvc extends IService {

	public final String NAME = "IMailSvc";
	
	public boolean sendScanStatus(String emailAddress);
	
}
