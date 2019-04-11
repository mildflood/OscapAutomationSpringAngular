package hcqis.cloud.automation.model.utility.jdbc;

/**
 * Author: Jonas  Okwara
 * Date: 05-28-18
 * Database connection utility class
 * that connects the application to
 * the relational database 
*/

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcConnectionUtility {

private static InitialContext context = null;
	
	public static Connection  getConnection() throws SQLException, NamingException{
		
	 if(context == null){
		context = new InitialContext();
	 }

	DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OscapAutomation");
	 return dataSource.getConnection();				
	}
	
	
	public static void close(Connection conn){
		if(conn !=null){
			try{
				conn.close();
			}catch(SQLException sqlEx1){
				sqlEx1.printStackTrace();
			}
		}				

	}
	
	public static void close(Statement stmt){
	  if(stmt != null){
		  try{
			  stmt.close();
		  }catch( SQLException sqlEx2){
			sqlEx2.printStackTrace();
			 
		  }
	  }	
	}
}
	
	
	
	
	
	
	

