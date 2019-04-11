/**
 * Author Jonas  Okwara
 * Date 11-05-18
 * Class that serializes arrayList Objects
 * to Json and vice versa
 */

package hcqis.cloud.automation.model.utility.serialization;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hcqis.cloud.automation.model.domain.ScanScheduler;



 public class JSONSerializationUtility  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(JSONSerializationUtility.class.getName());    
	
	
	/**
	 * No Arg constructor
	 */
	public JSONSerializationUtility() {
		
	}
	
	
	 /**
	  * Method to convert java 
	  * ArrayList object to JSON
	  * @param nodeList
	  */
	 public  void  convertJavaListToJSON(List<String> nodeList){  		 
		   FileWriter fileWriter = null; 
		   String fileName = "listDB.json";
		   Gson gson = new GsonBuilder().setPrettyPrinting().create();
		   String jsonString = gson.toJson(nodeList);
		   
		   try{
			fileWriter = new FileWriter(createJSONFile(fileName));
			 fileWriter.append(jsonString);
			 
			 fileWriter.close();

		   }catch(IOException ioExcep){
			     ioExcep.printStackTrace();
		   }
	 }
		   
	   

	 
	 
	 /**
	  * Convert Java Object
	  * to JSON 
	  * @param scanSchedObj
	  */
	 public  void  convertJavaObjToJSON(ScanScheduler scanSchedObj){  		 
		   FileWriter fileWriter = null; 
		   String fileName = "ObjDB.json";
		   Gson gson = new GsonBuilder().setPrettyPrinting().create();
		   String jsonString = gson.toJson(scanSchedObj);
		   
		   try{
			fileWriter = new FileWriter(createJSONFile(fileName));
			 fileWriter.append(jsonString);
			 
			 fileWriter.close();

		   }catch(IOException ioExcep){
			     ioExcep.printStackTrace();
		   }
		   
	   }
	 
	 
	
	 /**
	  * Author Jonas  Okwara
	  * Date  11-05-18
	  * De - serialize JSON Object to
	  * Java ArrayList Object
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public List<String> convertJSONToJavaList(){ 
		
		 Gson gson = new Gson();
		 ArrayList<String> nodeList = null;
		 
		 try {
			 Reader reader = new FileReader("/Data/jsonDB/listDB.json"); 
			 nodeList = gson.fromJson(reader, ArrayList.class); 
			 
		 }catch(IOException ioExcep) {
			 ioExcep.printStackTrace();
			 
		 }
		 
		return nodeList;
	
	 }
	 
	
	/**
	 * Author Jonas Okwara
	 * Date 11-14-18
	 * De - serialize JSON Object
	 * to Java Object
	 * @param fileName
	 * @return scanSchedObj
	 */
	public ScanScheduler convertJSONToJavaObj(){ 
		
		 Gson gson = new Gson();
		 ScanScheduler scanSchedObj = null;
		 
		 try {
			 Reader reader = new FileReader("/Data/jsonDB/ObjDB.json"); 
			 scanSchedObj = gson.fromJson(reader, ScanScheduler.class); 
			 
		 }catch(IOException ioExcep) {
			 ioExcep.printStackTrace();
			 
		 }
		 
		return scanSchedObj;
	
	 }

	
	//public static void main(String[]args) {
	// List<String> myList =	new JSONSerializationUtility().convertJSONToJava(); 
	// for(String list: myList) {
	//	 System.out.println(list);
	// }
	// }
	
	 
	
	 /**
	  * Method to create the Json file 
	  * @return File
	  */
	 private  File createJSONFile(String fileName){
			boolean isValid = false;
			File sourceFile = new File("/Data/jsonDB/"+fileName);
			if(!sourceFile.exists()){
			 try {
			 isValid = sourceFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}	
			}
			if(isValid){
			logger.info("File Was Successfully Created");	
			}else{
			 logger.info("File Was Not Created");	
			}
			return sourceFile;
			  
		   } 
}
	
	
	


