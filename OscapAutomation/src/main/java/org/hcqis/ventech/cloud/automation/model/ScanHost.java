package org.hcqis.ventech.cloud.automation.model;

/**
 * Domain class representing each scan host
 * Author: Jonas  Okwara
 * Date: 05-11-18
 */  

import java.io.Serializable;


public class ScanHost implements Serializable{ 
		
	private static final long serialVersionUID = 1L; 
	
	/**
	 * Declared Variables
	 * with access modifiers
	 */
	private int    hostID;
	private String hostname;
	private String ipAddress;
	private String serverFQDN;
	private String entryDate;
	private String entryTime;
	private String hostStatus;
	
  
	
	/**
	 * No Arg Constructor
	 */
	public ScanHost(){
		
	}


    /**
     * @Author Jonas Okwara
     * @Date  05-29-18
     * Overloaded Constructor
     * @param hostID
     * @param hostname
     * @param ipAddress
     * @param serverFQDN
     * @param entryDate
     * @param entryTime
     */
	public ScanHost(int hostID, String hostname, String ipAddress,String serverFQDN, String entryDate, String entryTime, String hostStatus) {
		super();
		this.hostID = hostID;
		this.hostname = hostname;
		this.ipAddress = ipAddress;
		this.serverFQDN = serverFQDN;
		this.entryDate = entryDate;
		this.entryTime = entryTime;
		this.hostStatus = hostStatus;
	   }


	
	
	/**
	 * @Author Jonas Okwara
	 * @Date 05-29-18
	 * Get and Set Method
	 * to enforce class encapsulation
	 * and security
	 * @return String
	 */ 
	
	
	public int getHostID() {
		return hostID;
	}


	public void setHostID(int hostID) {
		this.hostID = hostID;
	}


	public String getHostname() {
		return hostname;
	}


	public void setHostname(String hostname) {
		this.hostname = hostname;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getServerFQDN() {
		return serverFQDN;
	}


	public void setServerFQDN(String serverFQDN) {
		this.serverFQDN = serverFQDN;
	}


	public String getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}


	public String getEntryTime() {
		return entryTime;
	}


	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	
	
	public String getHostStatus() {
		return hostStatus;
	}


	public void setHostStatus(String hostStatus) {
		this.hostStatus = hostStatus;
	}

	
	

	/**
	 * To String method to 
	 * format string values
	 */
	public String toString() {
		return "ScanHost [hostID=" + hostID + ", hostname=" + hostname
				+ ", ipAddress=" + ipAddress + ", serverFQDN=" + serverFQDN
				+ ", entryDate=" + entryDate + ", entryTime=" + entryTime
				+ ", hostStatus=" + hostStatus + "]";
	}


	/**
	 * hashCode method to 
	 * object uniqueness
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entryDate == null) ? 0 : entryDate.hashCode());
		result = prime * result
				+ ((entryTime == null) ? 0 : entryTime.hashCode());
		result = prime * result + hostID;
		result = prime * result
				+ ((hostStatus == null) ? 0 : hostStatus.hashCode());
		result = prime * result
				+ ((hostname == null) ? 0 : hostname.hashCode());
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result
				+ ((serverFQDN == null) ? 0 : serverFQDN.hashCode());
		return result;
	}


	/**
	 * Object equality method
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScanHost other = (ScanHost) obj;
		if (entryDate == null) {
			if (other.entryDate != null)
				return false;
		} else if (!entryDate.equals(other.entryDate))
			return false;
		if (entryTime == null) {
			if (other.entryTime != null)
				return false;
		} else if (!entryTime.equals(other.entryTime))
			return false;
		if (hostID != other.hostID)
			return false;
		if (hostStatus == null) {
			if (other.hostStatus != null)
				return false;
		} else if (!hostStatus.equals(other.hostStatus))
			return false;
		if (hostname == null) {
			if (other.hostname != null)
				return false;
		} else if (!hostname.equals(other.hostname))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (serverFQDN == null) {
			if (other.serverFQDN != null)
				return false;
		} else if (!serverFQDN.equals(other.serverFQDN))
			return false;
		return true;
	}


	
	
	
	
	
	
	
}
