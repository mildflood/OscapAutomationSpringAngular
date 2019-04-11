/**
 * Author Jonas  Okwara
 * Date 11-1-18
 * Class that handles simultenous
 * processing of tasks
 */

package hcqis.cloud.automation.model.utility.concurrency;

import java.util.concurrent.Callable;

import hcqis.cloud.automation.model.utility.scanutility.ScanUtility;

public class ProcessHandler implements  Callable<String>{
	
	String  hostname = null;
	
	/**
	 * Overloaded constructor
	 * @param nodename
	 */
	public ProcessHandler(String nodename) {
		hostname = nodename;
		
	}
	
	

    /**
     * Override the call 
     * method
     */
	public String call() throws  Exception{
		
	  return new ScanUtility().performHostScan(hostname);	
		
	}




}
