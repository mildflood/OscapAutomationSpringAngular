package hcqis.cloud.automation.model.services.mailservice;

import hcqis.cloud.automation.model.services.IService;

public interface IMailSvc extends IService {

	public final String NAME = "IMailSvc";
	
	public boolean sendScanStatus(String emailAddress);
	
}
