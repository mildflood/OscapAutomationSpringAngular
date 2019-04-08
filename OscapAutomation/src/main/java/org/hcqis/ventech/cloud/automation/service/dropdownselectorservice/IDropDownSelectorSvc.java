package org.hcqis.ventech.cloud.automation.service.dropdownselectorservice;

import java.util.List;

import org.hcqis.ventech.cloud.automation.model.ScanHost;
import org.hcqis.ventech.cloud.automation.service.IService;

public interface IDropDownSelectorSvc extends IService { 
	
	public final String NAME = "IDropDownSelectorSvc";
	
	public List<ScanHost> getDropDownList();

}
