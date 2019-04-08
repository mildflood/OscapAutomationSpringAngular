/**
 *@Author: Jonas Okwara
 *@Date: 05-28-18
 *Interface that defines the contract
 *for its implementation class
 */

package org.hcqis.ventech.cloud.automation.service.dataservice;


import org.hcqis.ventech.cloud.automation.service.IService;


public interface IHostDataDaoSvc extends IService { 
	public final String NAME = "IHostDataDaoSvc";
	
	public boolean populateHostTb(String nodeName);
 }
