package hcqis.cloud.automation.model.business.exception;

public class SaveFileException extends Exception{

	private static final long serialVersionUID = 1L;  
	
	
	public SaveFileException(final String inMessage, final Throwable inNestedException)  {
	     
		super(inMessage, inNestedException);
		
	}
}