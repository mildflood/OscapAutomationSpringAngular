/**
 *@Author: Jonas Okwara
 *@Date: 05-28-18
 *Interface that defines the contract
 *for its implementation class
 */

package hcqis.cloud.automation.model.services.dataservice;

import hcqis.cloud.automation.model.domain.ScanHost;
import hcqis.cloud.automation.model.domain.ScanSession;
import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.services.IService;

import java.util.List;


public interface IHostDataDaoSvc extends IService { 
	public final String NAME = "IHostDataDaoSvc";
	
	public List<ScanSession> getScanData();
	//public List<String> getEachHostData(ScanHost scannedHostObj);
	public boolean writeToDB(ScanHost scanHostObj); 
	public boolean writeScanData(ScanHost  scanHostObj, User userObj);
	
	public boolean populateHostTb(String nodeName);
 }
