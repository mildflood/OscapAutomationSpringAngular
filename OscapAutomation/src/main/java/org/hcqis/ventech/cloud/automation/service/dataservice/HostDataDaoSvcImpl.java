package org.hcqis.ventech.cloud.automation.service.dataservice;

import java.util.logging.Logger;

import org.hcqis.ventech.cloud.automation.hibernate.entity.HostListTbl;
import org.hcqis.ventech.cloud.automation.hibernate.service.HostListTblService;
import org.springframework.beans.factory.annotation.Autowired;


public class HostDataDaoSvcImpl implements IHostDataDaoSvc {

	static Logger logger = Logger.getLogger(HostDataDaoSvcImpl.class.getName());

	@Autowired
	HostListTblService hostListTblService;

	public HostDataDaoSvcImpl() {

	}
	
	/*
	 * Matthew Zhao 
	 * Date 03-20-2019
	 * Implementation class that populate hostlisttbl 
	 */
	public boolean populateHostTb(String nodeName) {

		boolean result = true;
		HostListTbl hostListTbl = new HostListTbl();
		hostListTbl.setNodename(nodeName);
		HostListTbl savedHost = hostListTblService.save(hostListTbl);
		if (savedHost.getHostID() <= 0) {
			result = false;
		}

		return result;
	}
}
