/**
 * Author Jonas  Okwara
 * Date 10-15-2018
 * class that implements 
 * file transfer interface
 * contracts
 */

package org.hcqis.ventech.cloud.automation.service.fileservice;

import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class FileRetrievalSvcImpl implements IFileRetrievalSvc { 
	
	 private String sftpHost ;
	 private int    sftpPort ;
	 private String sftpUser ;
	 private String sftpPassword ;
	 private String sftpDir ;
	 
	 
	 /**
	  * No Arg Constructor
	  */
	 public FileRetrievalSvcImpl() {
		 
	 }
	 
	 
	 /**
	  * Overloaded Constructor
	  * @param sftpHost
	  * @param sftpPort
	  * @param sftpUser
	  * @param sftpPassword
	  * @param sftpDir
	  */
	 public FileRetrievalSvcImpl(String sftpHost, int sftpPort, String sftpUser, String sftpPassword, String sftpDir) {  

	  this.sftpHost = "sben1a003";
	  this.sftpPort = 22;
	  this.sftpUser = "oscapscn";
	  this.sftpPassword = "oscapscn";
	  this.sftpDir = "/Data/fileDump";
	 }
	
	
	
	public String getSftpHost() {
		return sftpHost;
	}

	public void setSftpHost(String sftpHost) {
		this.sftpHost = sftpHost;
	}


	public int getSftpPort() {
		return sftpPort;
	}


	public void setSftpPort(int sftpPort) {
		this.sftpPort = sftpPort;
	}


	public String getSftpUser() {
		return sftpUser;
	}

	public void setSftpUser(String sftpUser) {
		this.sftpUser = sftpUser;
	}


	public String getSftpPassword() {
		return sftpPassword;
	}


	public void setSftpPassword(String sftpPassword) {
		this.sftpPassword = sftpPassword;
	}



	public String getSftpDir() {
		return sftpDir;
	}



	public void setSftpDir(String sftpDir) {
		this.sftpDir = sftpDir;
	}



	public  boolean transferGeneratedFile(String localFilePath, String remoteDirPath, String remoteFileName) {
		
		  this.sftpHost = "sben1a003";
		  this.sftpPort = 22;
		  this.sftpUser = "root";
		  this.sftpPassword = "okwu";
		  this.sftpDir = "/home/oscapscn";
		  boolean returnResult = false;
		  boolean transferSuccess = false;
		  Session     session     = null;
		  Channel     channel     = null;
		  ChannelSftp channelSftp = null; 
		  
		  
		  try{
			   JSch jsch = new JSch();
			   session = jsch.getSession(this.sftpUser,this.sftpHost,this.sftpPort);
			   session.setPassword(this.sftpPassword);
			   java.util.Properties config = new java.util.Properties();
			   config.put("StrictHostKeyChecking", "no");
			   session.setConfig(config);
			   session.connect();
			   channel = session.openChannel("sftp");
			   channel.connect();
			   channelSftp = (ChannelSftp)channel;
			   if(null != remoteDirPath)
			    channelSftp.cd(remoteDirPath);
			   else
				    channelSftp.cd(this.sftpDir);
				 
				   File f = new File(localFilePath);
				   String fileName = f.getName();
				   if(null != remoteFileName && remoteFileName.length() > 0)
				    fileName = remoteFileName;
				 
				   channelSftp.put(new FileInputStream(f), fileName);
			  
			   
			   channel.disconnect();
			 
			   session.disconnect();
			  
			   returnResult = transferSuccess;
			  }catch(Exception ex){
			   ex.printStackTrace();
			  }
			 
			  return returnResult;
			 }
		 
	
	      public static void main(String[]args) {  
	    	  String  dirName = "/Data/fileDump/sben1a003.1681.rheldisa.101418.html"; 
	    	  String  remoteHome = "/home/oscapscn";
	    	  String  remoteFile  = "sben1a003.1681.rheldisa.101418.html";
	    	  new FileRetrievalSvcImpl().transferGeneratedFile(dirName, remoteHome, remoteFile);
	      }
	}  
 	
	      



