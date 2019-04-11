package hcqis.cloud.automation.model.utility.concurrency;

import hcqis.cloud.automation.model.business.manager.ScanManager;
import hcqis.cloud.automation.model.business.manager.SchedManager;
import hcqis.cloud.automation.model.domain.ScanScheduler;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Author Matthew Zhao
 * Date 11-23-2018
 * Class provides concurrent execution of a list of scan tasks.
 */
public class ConcurrentTaskExecuter implements Runnable {
	
	private final static Logger logger = Logger.getLogger(ConcurrentTaskExecuter.class.getName());
	private ScanScheduler schedulerObj = null;
	private String statusMessage = null;
	private String hostname;
    
    public ConcurrentTaskExecuter(ScanScheduler schedulerObj, String hostname){
        this.schedulerObj = schedulerObj;
        this.hostname = hostname;
    }

    @Override
    public void run() {
    	if (hostname != null){
	    	logger.info(Thread.currentThread().getName()+ " Scanning task for " + hostname + " starts .....");
	    	try {
		    	SchedManager schedMgr = SchedManager.getInstance(); 
				ScanManager scanMgr = ScanManager.getInstance();
	
				statusMessage = performHostScan(hostname.trim()); 
				schedulerObj.setStatus(statusMessage);		 
				scanMgr.writeMultiSchedData(schedulerObj);
				schedMgr.sendStatusMessage(schedulerObj, statusMessage);
				//schedMgr.retrieveGeneratedFile(hostname);
	
	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}
	        logger.info(Thread.currentThread().getName()+ " Scanning task for " + hostname + " ends.");
    	}
        else {
			logger.info("Host name is NULL.");
		}
    }
    
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
				exitMessage = ( scanHost + " " + "Was Successful");
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

}
