package hcqis.cloud.automation.model.services.dropdownselectorservice;

import java.util.List;

import hcqis.cloud.automation.model.domain.ScanHost;
import hcqis.cloud.automation.model.services.IService;

public interface IDropDownSelectorSvc extends IService { 
	
	public final String NAME = "IDropDownSelectorSvc";
	
	public List<ScanHost> getDropDownList();

}
