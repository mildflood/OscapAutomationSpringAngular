package org.hcqis.ventech.cloud.automation.model;

/**
 * Author: Jonas Okwara
 * Date: 06-10-18
 * Class that represents a
 * Vulnerability scan session on each host
 */

import java.io.Serializable;


public class ScanSession implements Serializable { 
 

 private static final long serialVersionUID = 1L;
 private String serverName = null;
 private User   loginUser = null;
 private String scanStatus = null;
 private String scanDate = null;
 private String scanTime = null;
 
 
 /**
  * No Arg Constructor
  */
 public ScanSession(){
	 
 }

 /**
  * Overloaded Constructor
  * Author Jonas 
  * Date 06-10-18
  * @param loginUser
  * @param scanStatus
  * @param scanDate
  * @param scanTime
  */
 public ScanSession(String serverName, User loginUser, String scanStatus, String scanDate, String scanTime) {
		super();
		this.serverName = serverName;
		this.loginUser = loginUser;
		this.scanStatus = scanStatus;
		this.scanDate = scanDate;
		this.scanTime = scanTime;
	}

 
 
 /**
  * Get and Set Methods
  * to enforce encapsulation
  * @return
  */ 
public String getServerName() {
	return serverName;
}

public void setServerName(String serverName) {
	this.serverName = serverName;
}

public User getLoginUser() {
	return loginUser;
}

public void setLoginUser(User loginUser) {
	this.loginUser = loginUser;
}

public String getScanStatus() {
	return scanStatus;
}

public void setScanStatus(String scanStatus) {
	this.scanStatus = scanStatus;
}

public String getScanDate() {
	return scanDate;
}

public void setScanDate(String scanDate) {
	this.scanDate = scanDate;
}

public String getScanTime() {
	return scanTime;
}

public void setScanTime(String scanTime) {
	this.scanTime = scanTime;
}

/**
 * Author: Jonas  Okwara
 * Date: 06-10-18
 * toString method to format
 * String outputs
 */
public String toString() {
	return "ScanSession [serverName=" + serverName + ", loginUser=" + loginUser + ", scanStatus=" + scanStatus
			+ ", scanDate=" + scanDate + ", scanTime=" + scanTime + "]";
}

/**
 * HashCode method to enforce
 * Object uniqueness
 */
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((loginUser == null) ? 0 : loginUser.hashCode());
	result = prime * result + ((scanDate == null) ? 0 : scanDate.hashCode());
	result = prime * result + ((scanStatus == null) ? 0 : scanStatus.hashCode());
	result = prime * result + ((scanTime == null) ? 0 : scanTime.hashCode());
	result = prime * result + ((serverName == null) ? 0 : serverName.hashCode());
	return result;
}


public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ScanSession other = (ScanSession) obj;
	if (loginUser == null) {
		if (other.loginUser != null)
			return false;
	} else if (!loginUser.equals(other.loginUser))
		return false;
	if (scanDate == null) {
		if (other.scanDate != null)
			return false;
	} else if (!scanDate.equals(other.scanDate))
		return false;
	if (scanStatus == null) {
		if (other.scanStatus != null)
			return false;
	} else if (!scanStatus.equals(other.scanStatus))
		return false;
	if (scanTime == null) {
		if (other.scanTime != null)
			return false;
	} else if (!scanTime.equals(other.scanTime))
		return false;
	if (serverName == null) {
		if (other.serverName != null)
			return false;
	} else if (!serverName.equals(other.serverName))
		return false;
	return true;
}


}
