/**
 * Author Jonas  Okwara
 * Date 08-15-18
 * utility class that implements scheduled scan of nodes
 */

package org.hcqis.ventech.cloud.automation.util;

import java.io.IOException;
import java.util.logging.Logger;




public class SchedulerUtility { 
	
	
	
	
	Logger logger = Logger.getLogger("ScanScheduleUtility");
	private Process OSCmdProcess = null;
	String OScmd = null;
	String exitMessage = null;

	/**
	 * @Author: Jonas Okwara
	 * @Date: 05-16-18 
	 * Call the Openscap scan command and perform a scan on a
	 * host and send the status update.
	 * @return String
	 */
	public String performHostScan(String nodeName) {
 
		String OScmd = new String("/usr/bin/ssh   oscapscn@"  + nodeName + " " + "/home/oscapscn/openscap.bash  > scan.out" );	
			
		logger.info(OScmd);
			
		try {
			OSCmdProcess = Runtime.getRuntime().exec(OScmd);
			int  exitValue = OSCmdProcess.waitFor(); 
			
			if (exitValue != 0) {
			
				exitMessage = ("Scan Failed" + "-" + nodeName); 
			
			} else {
				exitMessage = ("Scan Succeeded" + "-"+ nodeName);
			
			}
		} catch (IOException ioExcep) {
			ioExcep.printStackTrace();
		} catch (InterruptedException interExcep) {
			interExcep.printStackTrace();
		} 
		return exitMessage;

	}   
		
	
	
	
	
	
	/**
	 * 
	 * @param emailAddress
	 
	 private void notifyAdmin(String emailAddress) {
	
		
  	} 
	
  **/	
	
	
	
	
	
	
	
	
	

}
