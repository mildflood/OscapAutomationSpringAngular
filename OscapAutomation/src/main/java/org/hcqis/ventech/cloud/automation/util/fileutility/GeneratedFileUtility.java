
/**
 * Author Jonas  Okwara
 * Date 06-28-2018
 * Main utility class to process
 * list of generated reports and 
 * send them to the business manager 
 * 
 */
package org.hcqis.ventech.cloud.automation.util.fileutility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class GeneratedFileUtility {   
	
	private static List<String> reportsList = null;
	private static  String  FILESOURCE = "/Data/fileDump"; 
	
   
	
	/**
	 * No Argument Constructor
	 */
	public GeneratedFileUtility(){
		
	}
	
	
	/**
	 * Author Jonas Okwara
	 * Date 06-28-2018
	 * @param nodename
	 * @return List
	 */
	public static List<String> processGeneratedFiles(String nodename){  
		
		Date theDate = new Date();  
		
		String myPattern = "MMddyy"; 
		SimpleDateFormat  dateFormat = new SimpleDateFormat(myPattern);
		String  toDay = dateFormat.format(theDate);
		String  surfixEntry  = toDay + ".html";
		reportsList = new ArrayList<>();	 
		 
	    try{
	       File generatedFiles = new File(FILESOURCE);
	       String[]  listOfFiles = generatedFiles.list();      
	         for(String eachFile: listOfFiles){
	            if(eachFile.startsWith(nodename) && eachFile.endsWith(surfixEntry) == true){
	            	reportsList.add(eachFile);
	            	
	            }
	            
	         }
	          
	         return  reportsList;
	         
	       }catch(Exception ioExcep){

	       ioExcep.printStackTrace();

	   }

	    
		return reportsList;
	   }   
	
	
  }
	
