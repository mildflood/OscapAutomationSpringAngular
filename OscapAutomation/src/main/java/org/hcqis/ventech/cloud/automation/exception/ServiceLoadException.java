package org.hcqis.ventech.cloud.automation.exception;

public class ServiceLoadException extends Exception{

	private static final long serialVersionUID = 1L;  
	
	
	public ServiceLoadException(final String inMessage, final Throwable inNestedException)  {
	     
		super(inMessage, inNestedException);
		
	}
}
