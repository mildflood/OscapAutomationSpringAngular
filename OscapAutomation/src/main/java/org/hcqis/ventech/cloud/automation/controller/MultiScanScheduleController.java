package org.hcqis.ventech.cloud.automation.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.hcqis.ventech.cloud.automation.model.manager.SchedManager;
import org.hcqis.ventech.cloud.automation.model.ScanScheduler;
import org.hcqis.ventech.cloud.automation.model.User;

@RestController
@SessionAttributes("userObj")
public class MultiScanScheduleController {
	private final static Logger logger = Logger.getLogger(MultiScanScheduleController.class.getName()); 
	 
	@Autowired
	private ScanScheduler scanSchedulerObj;
	
	@Autowired
	private SchedManager schedMgr;

	@PostMapping
	@RequestMapping("/ScanScheduler")
	public void createScanSchedule(@ModelAttribute User userObj, @RequestBody MultiScanScheduleData multiScanScheduleData) {
		System.out.println(multiScanScheduleData.toString());

		userObj.getFirstname();
		userObj.getLastname();

		/**
		 * Obtain inputs from Gui and process them
		 */
		String arrayString = multiScanScheduleData.getNodename();
		String[] nodesArray = arrayString.split("\\r?\\n");

		List<String> nodeList = Arrays.asList(nodesArray);

		// output The Values
		// ==================================
		for (String eachNode : nodeList) {
			logger.info(eachNode);
		}

		scanSchedulerObj.setScanAdministrator(userObj);
		scanSchedulerObj.setGroup(multiScanScheduleData.getGroup());
		scanSchedulerObj.setDateOfScan(multiScanScheduleData.getDateOfScan());
		scanSchedulerObj.setTimeOfScan(multiScanScheduleData.getTimeOfScan());
		scanSchedulerObj.setEmail(multiScanScheduleData.getEmail());

		//String stringValue = request.getParameter("repeatScan");
		//boolean booleanValue = Boolean.parseBoolean(stringValue);
		//scanSchedulerObj.setRepeatScan(booleanValue);

		logger.info(scanSchedulerObj.getGroup());
		logger.info(scanSchedulerObj.getDateOfScan());
		logger.info(scanSchedulerObj.getTimeOfScan());
		logger.info(scanSchedulerObj.getEmail());
		//logger.info(scanSchedulerObj.isRepeatScan());

		schedMgr = SchedManager.getInstance();
		schedMgr.initializeMultiScan(scanSchedulerObj, nodeList);
	}
}
