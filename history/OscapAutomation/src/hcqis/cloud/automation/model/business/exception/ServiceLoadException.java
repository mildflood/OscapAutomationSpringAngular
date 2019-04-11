package hcqis.cloud.automation.model.business.exception;



public class ServiceLoadException extends Exception{

	private static final long serialVersionUID = 1L;  
	
	
	public ServiceLoadException(final String inMessage, final Throwable inNestedException)  {
	     
		super(inMessage, inNestedException);
		
	}

	
	

}
