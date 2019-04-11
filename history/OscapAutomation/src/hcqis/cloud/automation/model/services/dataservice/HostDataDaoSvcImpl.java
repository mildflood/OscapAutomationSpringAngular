/**
 * Author Jonas 
 * Date 05-29-18
 * Implementation class that writes and reads
 * from the database tables
 */

package hcqis.cloud.automation.model.services.dataservice;

import hcqis.cloud.automation.model.domain.ScanHost;
import hcqis.cloud.automation.model.domain.ScanSession;
import hcqis.cloud.automation.model.domain.ScanScheduler;
import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.utility.InputConverterUtility;
import hcqis.cloud.automation.model.utility.jdbc.JdbcConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

public class HostDataDaoSvcImpl implements IHostDataDaoSvc { 
	
	static Logger logger = Logger.getLogger(HostDataDaoSvcImpl.class.getName());
	  
	ResultSet resultSet = null;
    Connection conn = null;
    Statement stmt = null;
     
		/**
		 * No arg constructor
		*/
		public HostDataDaoSvcImpl(){
			
		}
	
      
	    /**
	     * Author Jonas  Okwara
	     * Date 08-24-18
	     * Read Scheduled Scan Data from the 
	     * Database
	     * @return
	     */
	    public List<ScanScheduler>  readCompletedScanData(){    
	    	
	    String QUERY = "SELECT scheduledScantbl.firstname, scheduledScantbl.lastname, scheduledScantbl.nodename,"
		+ "scheduledScantbl.monthOfScan, scheduledScantbl.dayOfMonth, scheduledScantbl.dayOfWeek, "
		+ "scheduledScantbl.hourOfScan, scheduledScantbl.minuteOfScan, scheduledScantbl.am_pm, "
		+ "completedScantbl.status"
		+ "FROM completedScantbl  INNER JOIN scheduledScantbl "
		+ "ON scheduledScantbl.scanID = completedScantbl.scanID";
	    		
	    	 List<ScanScheduler> scanDataListObj = new ArrayList<>();
	    	 
	    	 User userObj = null;
	    	 ScanHost scanHostObj = null;
	    	 ScanScheduler schedulerObj = null;
	    	   try{
	    		 	logger.info("Beginning the database connection for scheduled Scan List");
	    		 	conn = JdbcConnectionUtility.getConnection();
	                if (conn != null) {
	                    stmt = conn.createStatement();
	                    resultSet = stmt.executeQuery(QUERY); 
	                    
	                    while (resultSet.next()) {   
	                    	
	                    	userObj = new User();
	                    	scanHostObj = new ScanHost();
	                    	schedulerObj = new  ScanScheduler();
	                    	
	                    	//Get the value for am or pm and  convert to its String equivalent
	                    	//================================================================
	                    	int  am_pmValue = Integer.parseInt(resultSet.getString("am_pm"));
	                    	@SuppressWarnings("unused")
							String  realValue = new InputConverterUtility().convertAMPMInput(am_pmValue);
	                    	
	                    	//Get the value for Week day and convert to its String equivalent
	                    	//===============================================================
	                    	//int  weekDay = Integer.parseInt(resultSet.getString("dayOfWeek"));
	                    	//String  realWeekDay = new InputConverterUtility().convertWeekDayInput(weekDay); 
	                    	

	                    	//Get the value for Month and convert to its String equivalent
	                    	//===============================================================
	                    	//int  monthOFYear =   resultSet.getInt("monthOfScan");
	                    	//String  realMonthValue = new InputConverterUtility().convertMonthInput(monthOFYear);
	                    	
	                    	
	                        /**
	                         * Pass in the values as parameters to
	                         * the userObj, scanHostObj and schedulerObj Objects
	                         */
	                        userObj.setFirstname(resultSet.getString("firstname"));
	                    	userObj.setLastname(resultSet.getString("lastname"));  
	                      	scanHostObj.setHostname(resultSet.getString("nodename")); 
	                      
	                    	
	                    	
	                    	schedulerObj.setStatus(resultSet.getString("status"));
	                     
	                    		                    	
	                    	scanDataListObj.add(schedulerObj);
	                    		                    	
	                    	logger.info("...Returning Records of The Scheduled Scan Queue...");
	                         
	                    }
	                }
	            } catch (SQLException sqlEx1) {
	            	sqlEx1.getMessage();
	                sqlEx1.printStackTrace();  
	                
	            } catch (NamingException nameEx) {
	    			nameEx.printStackTrace();
	    			
	    		}finally{
	            	
	    			JdbcConnectionUtility.close(stmt);
	    			JdbcConnectionUtility.close(conn);
	            }

	    		return scanDataListObj;
		     } 
	     

	    
	    
	    /**
	       * @author Jonas Okwara
	       * @date 05-29-18
	       * Write host Data info
	       * into  database
	       * @param scanHostObj
	       * @return boolean
	       */
		    public boolean writeScanData(ScanHost  scanHostObj, User userObj) {      	
			String QUERY = "INSERT INTO scanDatatbl(firstname, lastname, nodename, scanstatus, scandate, scantime)" +  "VALUES(?,?,?,?,?,?)";
				PreparedStatement stmt = null;
				boolean isCorrect = true; 
				
				try {
					logger.info("...Beginning the database connection to write host data...");
					conn = JdbcConnectionUtility.getConnection();
					if (conn != null) {
						stmt = conn.prepareStatement(QUERY); 
						stmt.setString(1, userObj.getFirstname());
						stmt.setString(2, userObj.getLastname());
						stmt.setString(3, scanHostObj.getHostname());
						stmt.setString(4, scanHostObj.getHostStatus());
						stmt.setString(5, scanHostObj.getEntryDate());
						stmt.setString(6, scanHostObj.getEntryTime());  
						
						logger.info(".... Writing scan host data to database .....");
						stmt.executeUpdate();
					}

				} catch (SQLException sqlEx1) {
					sqlEx1.getMessage(); 
					
				}catch (NamingException nameExcep){
					nameExcep.printStackTrace();
				}finally{
		        	
					JdbcConnectionUtility.close(stmt);
					JdbcConnectionUtility.close(conn);
		        }

				return isCorrect;
			} 
		    
	    	    
	    /**
	     * Author Jonas Okwara
	     * Date 05-30-18
	     * @return List
	     */
	    public List<ScanSession> getScanData(){  	   
	    	ScanSession scanSessionObj = null;
	    	User userObj = null;
	    	String QUERY = "select firstname, lastname, nodename, scanstatus,  scandate, scantime from  scanDatatbl"; 
	    	
	    	 List<ScanSession> scanDataListObj = new ArrayList<>();
	    	   try{
	    		 	logger.info("Beginning the database connection for host Scan List");
	    		 	conn = JdbcConnectionUtility.getConnection();
	                if (conn != null) {
	                    stmt = conn.createStatement();
	                    resultSet = stmt.executeQuery(QUERY); 
	                    
	                    while (resultSet.next()) {    	
	                  
	                    	scanSessionObj = new ScanSession();
	                    	userObj = new User();
	                    	
	                    	
	                    	userObj.setFirstname(resultSet.getString("firstname"));
	                    	userObj.setLastname(resultSet.getString("lastname")); 
	                    	scanSessionObj.setLoginUser(userObj);
	                    	
	                    	scanSessionObj.setServerName(resultSet.getString("nodename"));
	                    	scanSessionObj.setScanStatus(resultSet.getString("scanstatus"));
	                    	scanSessionObj.setScanDate(resultSet.getString("scandate"));
	                    	scanSessionObj.setScanTime(resultSet.getString("scantime")); 
	         
	                    	
	                    	scanDataListObj.add(scanSessionObj); 
	                    
	                    	logger.info("...Returning Records of All scan hosts ...");
	                         
	                    }
	                }
	            } catch (SQLException sqlEx1) {
	            	sqlEx1.getMessage();
	                sqlEx1.printStackTrace();  
	                
	            } catch (NamingException nameEx) {
	    			nameEx.printStackTrace();
	    			
	    		}finally{
	            	
	    			JdbcConnectionUtility.close(stmt);
	    			JdbcConnectionUtility.close(conn);
	            }

	    		return scanDataListObj;
		     } 
	     
	    
	    
	      /**
	       * Author Jonas Okwara
	       * Date 06-26-2018
	       * return boolean
	       */
	      public boolean writeToDB(ScanHost scanHostObj){  
	    	  String QUERY = "INSERT INTO scanHostbl (hostID, hostname, ipaddress, serverFQDN, entryDate, entryTime)" +  "VALUES(?,?,?,?,?,?)";
				PreparedStatement stmt = null;
				boolean isCorrect = true;
				try {
					logger.info("...Beginning the database connection to write host data...");
					conn = JdbcConnectionUtility.getConnection();
					if (conn != null) {
						stmt = conn.prepareStatement(QUERY); 
						stmt.setInt(1,    scanHostObj.getHostID());
						stmt.setString(2, scanHostObj.getHostname());
						stmt.setString(3, scanHostObj.getIpAddress());
						stmt.setString(4, scanHostObj.getServerFQDN());
						stmt.setString(5, scanHostObj.getEntryDate());
						stmt.setString(6, scanHostObj.getEntryTime());
						logger.info(".... Writing scan host data to database .....");
						stmt.executeUpdate();
					}

				} catch (SQLException sqlEx1) {
					sqlEx1.getMessage(); 
					
				}catch (NamingException nameExcep){
					nameExcep.printStackTrace();
				}finally{
		        	
					JdbcConnectionUtility.close(stmt);
					JdbcConnectionUtility.close(conn);
		        }

				return isCorrect;
	    	  
	    	  
	    	  
	       }  
	      
	      
	      /**
	       * Update the CompletedScan table
	       * after every successful scan
	       * @param completeScanResults
	       * @return String 
	       */
	      public  void  writeCompletedScanResults(ScanHost  scanHostObj) { 	
	    	  
	    	  String QUERY = "UPDATE CompletedScantbl JOIN ScheduledScantbl\n" + 
	    	  		"    ON CompletedScantbl.scanID = ScheduledScantbl.scanID\n" + 
	    	  		"    SET CompletedScantbl.status = '?'\n" + 
	    	  		"    where CompletedScantbl.status = 'ready'";
	    	  		
				PreparedStatement stmt = null;
				
				try {
					logger.info("...Beginning the database connection to write Completed Scan data...");
					conn = JdbcConnectionUtility.getConnection();
					if (conn != null) {
						stmt = conn.prepareStatement(QUERY); 	
						stmt.setString(1, scanHostObj.getHostStatus());
						
						logger.info(".... Writing scan host data to database......");
						stmt.executeUpdate();
					}

				} catch (SQLException sqlEx1) {
					sqlEx1.getMessage(); 
					
				}catch (NamingException nameExcep){
					nameExcep.printStackTrace();
				}finally{
		        	
					JdbcConnectionUtility.close(stmt);
					JdbcConnectionUtility.close(conn);
		        }

				
	    
	      }
	    
	    
	      /**
	       * 
	       */
	      public boolean populateHostTb(String nodeName) { 
	    	  
	    	  String QUERY = "INSERT INTO hostListTbl (nodeName)" +  "VALUES(?)";
				PreparedStatement stmt = null;
				boolean isCorrect = true;
				try {
					logger.info("...Beginning the database connection to write Node data...");
					conn = JdbcConnectionUtility.getConnection();
					if (conn != null) {
						stmt = conn.prepareStatement(QUERY); 
						stmt.setString(1,  nodeName);
						
						logger.info(".... Writing Node data to database .....");
						stmt.executeUpdate();
					}

				} catch (SQLException sqlEx1) {
					sqlEx1.getMessage(); 
					
				}catch (NamingException nameExcep){
					nameExcep.printStackTrace();
				}finally{
		        	
					JdbcConnectionUtility.close(stmt);
					JdbcConnectionUtility.close(conn);
		        }

				return isCorrect;
	      }
}
	    	  
	    	  
	      
	      
	      
	      

	    
	    
	

