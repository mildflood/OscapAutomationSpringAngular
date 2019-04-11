/**
 * Author Jonas  Okwara
 * Date 02-18-19
 * Service class that moves drop 
 * down list data in and out of 
 * the application
 */

package hcqis.cloud.automation.model.services.dropdownselectorservice;

	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.logging.Logger;

	import javax.naming.NamingException;

	import hcqis.cloud.automation.model.domain.ScanHost;
	import hcqis.cloud.automation.model.utility.jdbc.JdbcConnectionUtility;

	public class DropDownSelectorSvcImpl implements IDropDownSelectorSvc{

	     static Logger logger = Logger.getLogger(DropDownSelectorSvcImpl.class.getName());


	      /**
	      * Author Jonas Okwara
	      * Date 02-14-19
	      * @return List
	      */
	     public List<ScanHost> getDropDownList() {

	         ResultSet resultSet = null;
	         Connection conn = null;
	         Statement stmt = null;
	         ScanHost nodeObj  = null;
	         String QUERY = "select nodename from  hostListTbl";
	         List<ScanHost> dropDownListObj = new ArrayList<>();


	         try{
	              logger.info("Beginning the database connection for host Scan List");
	              conn = JdbcConnectionUtility.getConnection();
	             if (conn != null) {
	                 stmt = conn.createStatement();
	                 resultSet = stmt.executeQuery(QUERY);

	                 while (resultSet.next()) {

	                 nodeObj = new ScanHost();

	                 nodeObj.setHostname(resultSet.getString("nodename"));

	                 dropDownListObj.add(nodeObj);
	                     logger.info("...Returning Records of All scan Nodes in database ...");

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

	         return dropDownListObj;
	      }

	}

	
	
	
	
	
	
	
	
	

