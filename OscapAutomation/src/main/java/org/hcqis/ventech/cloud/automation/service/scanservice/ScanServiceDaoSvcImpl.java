/**
 * Author Jonas  Okwara
 * Date 07-18-18
 * Classes and methods that
 * communicate directly to
 * database
 */

package org.hcqis.ventech.cloud.automation.service.scanservice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.hcqis.ventech.cloud.automation.hibernate.entity.MultiScanTbl;
import org.hcqis.ventech.cloud.automation.hibernate.service.MultiScanTblService;
import org.hcqis.ventech.cloud.automation.model.ScanScheduler;
import org.hcqis.ventech.cloud.automation.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class ScanServiceDaoSvcImpl implements IScanServiceDaoSvc {

	static Logger logger = Logger.getLogger(ScanServiceDaoSvcImpl.class
			.getName());

	@Autowired
	MultiScanTblService multiScanTblService;

	/*
	 * @author Matthew Zhao
	 * @date 03-20-2019 Write multi scan Data info into multiscantbl table
	 * @param scanHostObj
	 */
	public int writeMultiScanData(ScanScheduler schedulerObj) {
		int jobID = 0;
		MultiScanTbl scanJob = new MultiScanTbl();
		scanJob.setFirstname(schedulerObj.getScanAdministrator().getFirstname());
		scanJob.setLastname(schedulerObj.getScanAdministrator().getLastname());
		scanJob.setNodename(schedulerObj.getNodename());
		scanJob.setScandate(schedulerObj.getDateOfScan());
		scanJob.setScantime(schedulerObj.getTimeOfScan());
		scanJob.setScanstatus(schedulerObj.getStatus());

		logger.info(".... Writing MultiScan Data to Database for ....."
				+ scanJob.getNodename());
		multiScanTblService.save(scanJob);
		jobID = multiScanTblService.save(scanJob).getScanID();
		logger.info("Created scan job id " + jobID + " for host " + scanJob.getNodename());
		return jobID;
	}

	/*
	 * Author Matthew Zhao Date 03-20-2019 Read Scheduled Scan Job Data from
	 * multiscantbl table.
	 * 
	 * @return
	 */
	public List<ScanScheduler> readMultScan() {
		List<ScanScheduler> multiScanDataListObj = new ArrayList<>();

		User userObj = null;
		ScanScheduler schedulerObj = null;

		logger.info("Retrieving scheduled scan job list ....... ");
		List<MultiScanTbl> mutiScanTbls = multiScanTblService.findAll();
		for (MultiScanTbl item : mutiScanTbls) {
			userObj = new User();
			schedulerObj = new ScanScheduler();
			userObj.setFirstname(item.getFirstname());
			userObj.setLastname(item.getLastname());
			schedulerObj.setScanAdministrator(userObj);
			schedulerObj.setNodename(item.getNodename());
			schedulerObj.setDateOfScan(item.getScandate());
			schedulerObj.setTimeOfScan(item.getScantime());
			schedulerObj.setStatus(item.getScanstatus());

			multiScanDataListObj.add(schedulerObj);
		}
		logger.info("There are total of " + multiScanDataListObj.size()
				+ " scheduled scan jobs returned. ");
		return multiScanDataListObj;
	}

}
