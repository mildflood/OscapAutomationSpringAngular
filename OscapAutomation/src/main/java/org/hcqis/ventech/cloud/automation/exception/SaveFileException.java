package org.hcqis.ventech.cloud.automation.exception;

public class SaveFileException extends Exception{

	private static final long serialVersionUID = 1L;  
	
	
	public SaveFileException(final String inMessage, final Throwable inNestedException)  {
	     
		super(inMessage, inNestedException);
		
	}
}