

package hcqis.cloud.automation.model.services.authentication;

import hcqis.cloud.automation.model.domain.User;
import hcqis.cloud.automation.model.utility.jdbc.JdbcConnectionUtility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import java.sql.Connection;
import java.util.logging.Logger;

public class AuthenticationDaoSvcImpl implements IAuthenticationDaoSvc{   
	
	private final static Logger logger = Logger.getLogger(AuthenticationDaoSvcImpl.class.getName());
	  
    Connection conn = null;
	Statement stmt = null;
	ResultSet resultSet = null;
	User loginUserObj = null;

	
	
	public AuthenticationDaoSvcImpl() {

	  }

	/**
	 * @author Jonas Okwara verify user credentials
	 * @param loginObj
	 * @return boolean
	 */
	public boolean authenticateByUsername(User loginUserObj) {	
		boolean isValid = false;
		logger.info("login Object handed Over for database Processing");
		String loginusername = loginUserObj.getUsername();

		String sqlQUERY = "select username, password from  UserAuthtbl  where username = '" + loginusername + "'";
		try {
			logger.info("Beginning the database connection");
			conn = JdbcConnectionUtility.getConnection();
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sqlQUERY);
			logger.info("........ Checking The Database resultsSet............");

			while (resultSet.next()) {
				logger.info(".............Looking Into Authentication Database Results ........");
				String usernameInDataBase = resultSet.getString("username");
			logger.info("..........Verifying User  login credentials..........");

				if (usernameInDataBase.equals(loginusername)) {
					isValid = true;
					logger.info("..........User Login Credentials Successfully Verified ...........");
					logger.info("....The Final Output is:" + isValid+ "User"+ loginUserObj);
					return isValid;

				} else {
				logger.info(".........User Credentials Not Valid ...........");
					isValid = false;
				}

			} // End while loop

		} catch (SQLException sqlEx1) {

			sqlEx1.getMessage();
			sqlEx1.printStackTrace();

		} catch (NamingException nameExcep) {

			nameExcep.printStackTrace();

		} finally {

			JdbcConnectionUtility.close(stmt);
			JdbcConnectionUtility.close(conn);
		}
		return isValid;
		
	}
	
	

	/**
	 * @author Jonas Okwara 
	 * Query to confirm 
	 * the role the user belongs to.
	 * @param User userLoginObj
	 * @return String RoleName
	 */
	public String isUserInRole(User loginUserObj) {
		String loginusername = loginUserObj.getUsername();
		String SQL_QUERY = "select rolename from  UserRoletbl  where username = '"+ loginusername + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet resultSet;
		String rolename = null;

		try {
			conn = JdbcConnectionUtility.getConnection();
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(SQL_QUERY);
			while (resultSet.next()) {
			logger.info(".............Looking Into the UserRole Database Results ........");
				rolename = resultSet.getString("rolename");
				logger.info(rolename + "exits for user" + loginUserObj);
			}

		} catch (SQLException sqlEx1) {

			sqlEx1.getMessage();
			sqlEx1.printStackTrace();

		} catch (NamingException nameExcep) {
			nameExcep.printStackTrace();
		} finally {
			JdbcConnectionUtility.close(stmt);
			JdbcConnectionUtility.close(conn);
		}
		return rolename;
	}

	
	 /**
	 * Method to Obtain Firstname and Lastname
     * @Author Jonas  Okwara
     * @param userObj
     * @return User Object
     */
    public User getFirstnameLastname(User loginUserObj) {
    String loginusername = loginUserObj.getUsername();
     String SQL_QUERY = "select firstname, lastname from  UserAuthtbl  where username = '" + loginusername + "'";
     Connection conn = null;
     Statement stmt = null; 
     ResultSet resultSet; 
     
     try { 
      conn = JdbcConnectionUtility.getConnection(); 
      stmt = conn.createStatement(); 
      resultSet = stmt.executeQuery(SQL_QUERY);
      
      while (resultSet.next()) { 
      logger.info(".............Looking Into Authentication Database Results ........"); 
      String firstname = resultSet.getString("firstname");
      logger.info("The Users Firstname is:" + firstname);
      String lastname = resultSet.getString("lastname");
      logger.info("The users Lastname is:" + lastname); 
      loginUserObj.setFirstname(firstname);
      loginUserObj.setLastname(lastname);

      return loginUserObj;
      }
	  
      } catch(SQLException sqlEx1) {

      sqlEx1.getMessage(); 
      sqlEx1.printStackTrace();

      } catch (NamingException nameExcep) {
      nameExcep.printStackTrace(); 
      } finally{
    	  JdbcConnectionUtility.close(stmt);
    	  JdbcConnectionUtility.close(conn); 
      }
     
      return loginUserObj;
      }
  
    
    
     /**
      * Method to Obtain The UserID
      * of each User
      * @param loginUserObj
      * @return int
      */
     public int getUserID(User loginUserObj){
    	 String loginusername = loginUserObj.getUsername();
         String SQL_QUERY = "select userID from  UserAuthtbl  where username = '" + loginusername + "'";
         Connection conn = null;
         Statement stmt = null; 
         ResultSet resultSet; 
         
         try { 
          conn = JdbcConnectionUtility.getConnection(); 
          stmt = conn.createStatement(); 
          resultSet = stmt.executeQuery(SQL_QUERY);
          
          while (resultSet.next()) { 
          logger.info("...Looking Into Authentication Database Results ..."); 
          int  userID = resultSet.getInt("userID");
          logger.info("The Users ID is:" + userID);
                   
          return userID;
          }
    	  
          } catch(SQLException sqlEx1) {

          sqlEx1.getMessage(); 
          sqlEx1.printStackTrace();

          } catch (NamingException nameExcep) {
          nameExcep.printStackTrace(); 
          } finally{
        	  JdbcConnectionUtility.close(stmt);
        	  JdbcConnectionUtility.close(conn); 
          }
		    return 0;
           
    	
          }
     }
 



