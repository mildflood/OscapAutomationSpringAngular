package org.hcqis.ventech.cloud.automation.service.dropdownselectorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hcqis.ventech.cloud.automation.hibernate.entity.HostListTbl;
import org.hcqis.ventech.cloud.automation.hibernate.service.HostListTblService;
import org.hcqis.ventech.cloud.automation.model.ScanHost;
import org.springframework.beans.factory.annotation.Autowired;

public class DropDownSelectorSvcImpl implements IDropDownSelectorSvc {

	static Logger logger = Logger.getLogger(DropDownSelectorSvcImpl.class
			.getName());

	@Autowired
	HostListTblService hostListTblService;

	/**
	 * Author Matthew Zhao Date 03-20-19
	 * 
	 * @return List
	 */
	public List<ScanHost> getDropDownList() {

		ScanHost nodeObj = null;
		List<ScanHost> dropDownListObj = new ArrayList<>();

		logger.info("Retrieving scan host List ...... ");
		List<HostListTbl> hostListTbls = hostListTblService.findAll();

		for (HostListTbl item : hostListTbls) {
			nodeObj = new ScanHost();
			nodeObj.setHostname(item.getNodename());
			dropDownListObj.add(nodeObj);
		}
		logger.info("...Returning total records of " + dropDownListObj.size()
				+ " scan nodes in database.");

		return dropDownListObj;
	}

}
