package org.hcqis.ventech.cloud.automation.util.concurrency;

import java.util.concurrent.Callable;

import org.hcqis.ventech.cloud.automation.util.scanutility.ScanUtility;

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
