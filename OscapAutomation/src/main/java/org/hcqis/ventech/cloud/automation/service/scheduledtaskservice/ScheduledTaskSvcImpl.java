
/**
 * Author  Jonas  Okwara
 * Date  07-12-18
 * Class that  builds the calendar object and 
 * accepts the task and schedules it
 */

package org.hcqis.ventech.cloud.automation.service.scheduledtaskservice;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hcqis.ventech.cloud.automation.model.ScanScheduler;
import org.hcqis.ventech.cloud.automation.model.manager.ScanManager;
import org.hcqis.ventech.cloud.automation.model.manager.SchedManager;
import org.hcqis.ventech.cloud.automation.util.concurrency.ConcurrentTaskExecuter;
import org.hcqis.ventech.cloud.automation.util.fileutility.PropertyFileReaderUtility;

public class ScheduledTaskSvcImpl extends TimerTask implements IScheduledTaskSvc {

	private final static Logger logger = Logger.getLogger(ScheduledTaskSvcImpl.class.getName());

	Timer scheduledTimerObj = new Timer();

	String statusMessage = null;
	List<String> hostList = null;

	String hostname = null;
	

	// The default no - arg constructor
	// =================================
	public ScheduledTaskSvcImpl() {

	}

	/**
	 * Another overloaded constructor that accepts an object
	 * 
	 * @param scanHostObj
	 */

	public ScheduledTaskSvcImpl(ScanScheduler schedObj) {

		hostname = schedObj.getNodename();
	

	

	}

	/**
	 * Another overloaded constructor 
	 * that accepts a string parameter
	 * @param nodename
	 */
	public ScheduledTaskSvcImpl(String nodename) {
		hostname = nodename;
	}

	
	/**
	 * Run the scheduled task 
	 * and send status message 
	 * to the administrator
	 */
	public void run() {
		try {
			SchedManager schedMgr = SchedManager.getInstance();
			List<String> nodeList = schedMgr.getJavaListObjectFromJson();
			runConcurrentSchedTask(nodeList);		
		} catch (Exception excep) {
			excep.printStackTrace();
		} finally {
			scheduledTimerObj = null;
		}
	}

	/**
	 * call this method to cancel 
	 * scheduled tasks and purge them
	 */
	public void cancelTimer() {
		if (scheduledTimerObj != null) {
			logger.info("....... Cancelling Scheduled Jobs ......");
			scheduledTimerObj.cancel();
			scheduledTimerObj.purge();
			scheduledTimerObj = null;
		}

	}

	/**
	 * Author Jonas Okwara Date 08-15-18 Obtain all calendar values and
	 * convert/format them into appropriate data types
	 */

	public void executeScheduledJob(ScanScheduler schedulerObj) {

		/**
		 * Get the various schedule data, convert to appropriate data type and feed to
		 * calender class
		 */
		String hostname = schedulerObj.getNodename();
		int monthOfYear = processMonthValue(schedulerObj);
		int dayOfMonth = processDayValue(schedulerObj);
		int hourOfDay = processHourValue(schedulerObj);
		int minuteOfDay = processMinuteValue(schedulerObj);

		System.out.println("month of year is:" + monthOfYear);
		System.out.println("day of month" + dayOfMonth);
		System.out.println("the hour" + hourOfDay);
		System.out.println("the minute" + minuteOfDay);

		/**
		 * Feed these time values to the Calendar class' set method
		 */
		Calendar scheduleCalendarObj = Calendar.getInstance();
		// scheduleCalendarObj.set(Calendar.YEAR, Calendar.YEAR);
		scheduleCalendarObj.set(Calendar.MONTH, monthOfYear);
		scheduleCalendarObj.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		scheduleCalendarObj.set(Calendar.HOUR_OF_DAY, hourOfDay);
		scheduleCalendarObj.set(Calendar.MINUTE, minuteOfDay);
		scheduleCalendarObj.set(Calendar.SECOND, 0);
		scheduleCalendarObj.set(Calendar.MILLISECOND, 0);

		logger.info("The Calendar Date and Time for This Schedule is:" + " " + scheduleCalendarObj.getTime());

		/**
		 * Then Initialize the timer Object and pass in parameters to its method cancel
		 * out all tasks/jobs after running them
		 */

		scheduledTimerObj.schedule(new ScheduledTaskSvcImpl(hostname), scheduleCalendarObj.getTime());

	}

	/**
	 * @param scanSchedulerObj
	 * @return int
	 */
	private int processMonthValue(ScanScheduler scanSchedulerObj) {

		String scanDate = scanSchedulerObj.getDateOfScan();
		String[] dateValues = null;
		dateValues = scanDate.split("-");
		int theMonth = Integer.parseInt(dateValues[1]);

		return (theMonth - 1);
	}

	/**
	 * Author Jonas Okwara Date 07-12-18
	 * 
	 * @param scanSchedulerObj
	 * @return int
	 */
	private int processDayValue(ScanScheduler scanSchedulerObj) {

		String scanDate = scanSchedulerObj.getDateOfScan();
		String[] dateValues = null;
		dateValues = scanDate.split("-");
		int theDay = Integer.parseInt(dateValues[2]);

		return theDay;
	}

	/**
	 * Author Jonas Okwara Date 07-12-18
	 * @param scanSchedulerObj
	 * @return int
	 */
	private int processHourValue(ScanScheduler scanSchedulerObj) {

		String scanTime = scanSchedulerObj.getTimeOfScan();
		String[] hourValues = null;
		hourValues = scanTime.split(":");
		int theHour = Integer.parseInt(hourValues[0]);

		return theHour;

	}

	/**
	 * Author Jonas Okwara Date 07-12-18
	 * @param scanSchedulerObj
	 * @return int
	 */
	private int processMinuteValue(ScanScheduler scanSchedulerObj) {

		String scanTime = scanSchedulerObj.getTimeOfScan();
		String[] minuteValues = null;
		minuteValues = scanTime.split(":");
		int theMinute = Integer.parseInt(minuteValues[1]);

		return theMinute;

	}

	
	/**
	 * @Author: Jonas Okwara
	 * @Date: 05-16-18 
	 * Call the Openscap scan 
	 * command and perform a scan on a host
	 * then send the status update.
	 * @return String
	 */
	private String performHostScan(String scanHost) { 
		
		String surfixString = "-mgt";
		String nodename = scanHost+surfixString;
		String exitMessage = null;
		Process OSCmdProcess = null;
		String OScmd = null;

		OScmd = new String("/usr/bin/ssh  oscapscn@" + nodename + " " + "/home/oscapscn/openscap.bash");

		logger.info(OScmd);

		try {
			OSCmdProcess = Runtime.getRuntime().exec(OScmd);
			OSCmdProcess.waitFor();
			int exitStatus = OSCmdProcess.exitValue();

			logger.info("The Exit Value is:" + exitStatus);
			if (exitStatus != 0) {
				exitMessage = ( scanHost + " " + "Successful");
			} else {
				exitMessage = ( scanHost + " " + "Was Successful");
			}
		} catch (IOException ioExcep) {
			ioExcep.printStackTrace();
		} catch (InterruptedException interExcep) {
			interExcep.printStackTrace();

			logger.info(exitMessage);
		}

		return exitMessage;
	}

	
	/**
	 * Author Jonas  Okwara
	 * Date 11-16- 18
	 * Accept nodeList Object from JSON
	 * derive necessary data from object
	 * scan each node, send mail message and
	 * call database service objects
	 * @param nodeList
	 */
	private void runSchedTask(List<String> nodeList) { 
		SchedManager schedMgr = SchedManager.getInstance(); 
		ScanManager scanMgr = ScanManager.getInstance();
		
		ScanScheduler schedulerObj =  schedMgr.getJavaObjectFromJson(); 
		
		schedulerObj.getScanAdministrator().getFirstname();
		schedulerObj.getScanAdministrator().getLastname(); 		

		for (String eachNode : nodeList) {
			statusMessage = new ScheduledTaskSvcImpl().performHostScan(eachNode.trim()); 
			
			schedulerObj.setNodename(eachNode);
			schedulerObj.setStatus(statusMessage);		 
			
			 scanMgr.writeMultiSchedData(schedulerObj);
			
			schedMgr.sendStatusMessage(schedulerObj, statusMessage);
			
			
			}
		}

	/**
	 * Author Matthew Zhao
	 * Date 11-26-2018
	 * Accept nodeList Object from JSON
	 * derive necessary data from object
	 * scan each node concurrently, send mail message and
	 * call database service objects
	 * @param nodeList
	 */
	private void runConcurrentSchedTask(List<String> nodeList) { 
		SchedManager schedMgr = SchedManager.getInstance(); 
		//ScanScheduler schedulerObj =  schedMgr.getJavaObjectFromJson(); 	
		
		PropertyFileReaderUtility appProp = new PropertyFileReaderUtility();
		int threadPoolSize = appProp.getPropertyFileValues();
		
		 ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
		 for (String eachNode : nodeList) { 
			 ScanScheduler schedulerObj =  schedMgr.getJavaObjectFromJson(); 
			 schedulerObj.setNodename(eachNode);
             Runnable scanTaskThread = new ConcurrentTaskExecuter(schedulerObj, eachNode); 
             executor.execute(scanTaskThread); // start kicking off a new thread  ....
         }
         executor.shutdown();
         while (!executor.isTerminated()) {
         }
         logger.info("Finished all host scan threads.");
		}
	}


