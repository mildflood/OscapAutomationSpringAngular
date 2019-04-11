/**
 * Author Jonas  Okwara
 * Date 07-18-18
 * Classes and methods that
 * communicate directly to
 * database
 */

package hcqis.cloud.automation.model.services.scanservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import hcqis.cloud.automation.model.domain.ScanScheduler;
import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.utility.jdbc.JdbcConnectionUtility;

public class ScanServiceDaoSvcImpl implements IScanServiceDaoSvc {

	static Logger logger = Logger.getLogger(ScanServiceDaoSvcImpl.class.getName());


	
	/**
	 * @author Jonas Okwara
	 * @date 05-29-18 Write host Data info into database
	 * @param scanHostObj
	 * @return boolean writeScheduledScanData
	 */
	public void writeScheduledData(User userObj, ScanScheduler schedulerObj) {

		String QUERY = "INSERT INTO scheduledData(firstname, lastname, nodename, dateOfScan, timeOfScan, scanGroup, scanJob, scanStatus)"
				+ "VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		Connection conn = null;

		try {

			logger.info("...Beginning the database connection to write Scheduled Scan Data...");

			conn = JdbcConnectionUtility.getConnection();
			if (conn != null) {
				stmt = conn.prepareStatement(QUERY);
				stmt.setString(1, userObj.getFirstname());
				stmt.setString(2, userObj.getLastname());
				stmt.setString(3, schedulerObj.getScanHost());
				stmt.setString(4, schedulerObj.getDateOfScan());
				stmt.setString(5, schedulerObj.getTimeOfScan());
				stmt.setString(6, schedulerObj.getGroup());
				stmt.setString(8, schedulerObj.getStatus());

				logger.info(".... Writing Scheduled scan data to database for Real.....");

				stmt.executeUpdate();
			}

		} catch (SQLException sqlEx1) {
			sqlEx1.getMessage();

		} catch (NamingException nameExcep) {
			nameExcep.printStackTrace();
		} finally {

			JdbcConnectionUtility.close(stmt);
			JdbcConnectionUtility.close(conn);
		}
	}

	/**
	 * Author Jonas Okwara
	 *  Date 08-24-18 
	 *  Read Scheduled Scan
	 *  Data from the Database
	 * 
	 * @return
	 */
	public List<ScanScheduler> readScheduledData() {

		Statement stmt = null;
		ResultSet resultSet = null;
		Connection conn = null;

		String QUERY = "select firstname, lastname, nodename, dateOfScan, timeOfScan, scanGroup, scanStatus from  schedDatatbl";

		List<ScanScheduler> scanDataListObj = new ArrayList<>();

		User userObj = null;

		ScanScheduler schedulerObj = null;
		try {
			logger.info("Beginning the database connection for scheduled Scan List");
			conn = JdbcConnectionUtility.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				resultSet = stmt.executeQuery(QUERY);

				while (resultSet.next()) {

					userObj = new User();
					schedulerObj = new ScanScheduler();
					userObj.setFirstname(resultSet.getString("firstname"));
					userObj.setLastname(resultSet.getString("lastname")); 
					//schedulerObj.setScanInitiator(userObj);
					schedulerObj.setScanAdministrator(userObj);
					schedulerObj.setNodename(resultSet.getString("nodename"));
					schedulerObj.setDateOfScan(resultSet.getString("dateOfScan"));
					schedulerObj.setTimeOfScan(resultSet.getString("timeOfScan"));
					schedulerObj.setGroup(resultSet.getString("scanGroup"));
					schedulerObj.setStatus(resultSet.getString("scanStatus"));

					scanDataListObj.add(schedulerObj);

					logger.info("...Returning Records of The Scheduled Scan Queue...");

				}
			}
		} catch (SQLException sqlEx1) {
			sqlEx1.getMessage();
			sqlEx1.printStackTrace();

		} catch (NamingException nameEx) {
			nameEx.printStackTrace();

		} finally {

			JdbcConnectionUtility.close(stmt);
			JdbcConnectionUtility.close(conn);
		}

		return scanDataListObj;
	}

	/*
	 * @author Jonas Okwara
	 * 
	 * @date 05-29-18 Write host Data info into database
	 * 
	 * @param scanHostObj
	 * 
	 * @return boolean
	 */
	public long writedata(User userObj, ScanScheduler schedulerObj) {
		String QUERY = "INSERT INTO schedDatatbl(firstname, lastname, nodename, dateOfScan, timeOfScan, scanGroup, scanStatus)"
				+ "VALUES(?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int jobID = -1;

		try {
			logger.info("...Beginning the database connection to write host data...");
			conn = JdbcConnectionUtility.getConnection();
			if (conn != null) {
				stmt = conn.prepareStatement(QUERY);
				stmt.setString(1, userObj.getFirstname());
				stmt.setString(2, userObj.getLastname());
				stmt.setString(3, schedulerObj.getNodename());
				stmt.setString(4, schedulerObj.getDateOfScan());
				stmt.setString(5, schedulerObj.getTimeOfScan());
				stmt.setString(6, schedulerObj.getGroup());
				stmt.setString(7, schedulerObj.getStatus());

				logger.info(".... Writing scan host data to database .....");
				stmt.executeUpdate();

				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

				if (rs.next()) {
					jobID = rs.getInt(1);
					System.out.println("Key returned from " + "'SELECT LAST_INSERT_ID()': " + jobID);

				}

			}

		} catch (SQLException sqlEx1) {
			sqlEx1.getMessage();

		} catch (NamingException nameExcep) {
			nameExcep.printStackTrace();
		} finally {

			JdbcConnectionUtility.close(stmt);
			JdbcConnectionUtility.close(conn);
		}

		return jobID;
	}

	/**
	 * Update the CompletedScan table '" + loginusername + "' after every successful
	 * scan
	 * 
	 * @param completeScanResults
	 * @return String
	 */
	public void writeCompletedJobResults(String message, long jobID) {

		logger.info("Job id in the  method is" + jobID);

		Statement stmt = null;
		Connection conn = null;

		String QUERY = "UPDATE schedDatatbl" + "  SET scanStatus = '" + message + "' " + "  where schedID = '" + jobID
				+ "'";

		try {
			logger.info("...Beginning the database connection to write Completed Job data..");
			logger.info("message is:" + message + "Job Id is:" + jobID);
			conn = JdbcConnectionUtility.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				stmt.execute(QUERY);
				logger.info(".... Writing scan host data to database......");
			}

		} catch (SQLException sqlEx1) {
			sqlEx1.getMessage();

		} catch (NamingException nameExcep) {
			nameExcep.printStackTrace();
		} finally {

			JdbcConnectionUtility.close(stmt);
			JdbcConnectionUtility.close(conn);
		}
	}

	
	/*
	 * @author Jonas Okwara
	 * @date 05-29-18 Write multi scan 
	 * Data info into database
	 * @param scanHostObj
	 */
	public int  writeMultiScanData(ScanScheduler schedulerObj) {
		
		String QUERY = "INSERT INTO multiScantbl(firstname, lastname, nodename, scandate, scantime, scanstatus)" + "VALUES(?,?,?,?,?,?)";
				
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int jobID = 0;
	

		try {
			logger.info("...Beginning the database connection to write host data...");
			conn = JdbcConnectionUtility.getConnection();
			if (conn != null) {
				stmt = conn.prepareStatement(QUERY);
				stmt.setString(1, schedulerObj.getScanAdministrator().getFirstname());
				stmt.setString(2, schedulerObj.getScanAdministrator().getLastname());
				stmt.setString(3, schedulerObj.getNodename());
				stmt.setString(4, schedulerObj.getDateOfScan());
				stmt.setString(5, schedulerObj.getTimeOfScan());
				stmt.setString(6, schedulerObj.getStatus()); 
				
				logger.info(".... Writing MultiScan Data to Database for....." + schedulerObj.getNodename());
				stmt.executeUpdate();
				
				rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");

				if (rs.next()) {
					 jobID = rs.getInt(1);
					System.out.println("Key returned from " + "'SELECT LAST_INSERT_ID()': " + jobID);

				}
			
				
			}

		} catch (SQLException sqlEx1) {
			sqlEx1.getMessage();

		} catch (NamingException nameExcep) {
			nameExcep.printStackTrace(); 
			
		} finally {

			JdbcConnectionUtility.close(stmt);
			JdbcConnectionUtility.close(conn);
		}
          return jobID;
	} 
	
	
	
	
	/**
	 * Author Jonas Okwara
	 *  Date 11-19-18 
	 *  Read Scheduled Scan
	 *  Data from the Database
	 * 
	 * @return
	 */
	public List<ScanScheduler> readMultScan() {

		Statement stmt = null;
		ResultSet resultSet = null;
		Connection conn = null;

		String QUERY = "select firstname, lastname, nodename, scandate, scantime, scanstatus from  multiScantbl";

		List<ScanScheduler> multiScanDataListObj = new ArrayList<>();

		User userObj = null;
		ScanScheduler schedulerObj = null; 
		
		try {
			logger.info("Beginning the database connection for scheduled Scan List");
			conn = JdbcConnectionUtility.getConnection();
			if (conn != null) {
				stmt = conn.createStatement();
				resultSet = stmt.executeQuery(QUERY);

				while (resultSet.next()) {

					userObj = new User();
					schedulerObj = new ScanScheduler();
					userObj.setFirstname(resultSet.getString("firstname"));
					userObj.setLastname(resultSet.getString("lastname")); 				
					schedulerObj.setScanAdministrator(userObj);
					schedulerObj.setNodename(resultSet.getString("nodename"));
					schedulerObj.setDateOfScan(resultSet.getString("scandate"));
					schedulerObj.setTimeOfScan(resultSet.getString("scantime"));
					schedulerObj.setStatus(resultSet.getString("scanstatus"));

					multiScanDataListObj.add(schedulerObj);

					logger.info("...Returning Records of The Scheduled Scan Queue...");

				}
			}
		} catch (SQLException sqlEx1) {
			sqlEx1.getMessage();
			sqlEx1.printStackTrace();

		} catch (NamingException nameEx) {
			nameEx.printStackTrace();

		} finally {

			JdbcConnectionUtility.close(stmt);
			JdbcConnectionUtility.close(conn);
		}

		return multiScanDataListObj;
	}

	

} 




