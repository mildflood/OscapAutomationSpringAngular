package org.hcqis.ventech.cloud.automation.model;


/**
 * Author Jonas  Okwara
 * Date 05-12-18
 * represents the domain object
 * of a scan schedule
 */
public class ScanScheduler {   
	
	private  String nodename;
	private  long      jobID;
	private  String    scanHost;  
	private  User      scanAdministrator;
	private  String    dateOfScan;
	private  String    timeOfScan;
	private  boolean   repeatScan; 
	private  String    email;
	private  String    status;
	private  String    group;

	
	/**
	 * No Arg Constructor
	 */
	public ScanScheduler() {
		
	}
	
	/**
	 * 
	 * @param nodeList
	 * @param hostID
	 * @param scanHost
	 * @param user
	 * @param dateOfScan
	 * @param timeOfScan
	 * @param repeatScan
	 * @param am_pm
	 * @param email
	 * @param status
	 * @param group
	 * @param job
	 * @param jobID
	 */
	public ScanScheduler(String nodename, long jobID, String scanHost, User scanAdministrator, String dateOfScan,
			String timeOfScan, boolean repeatScan, String email, String status, String group) {
			
		super();
		this.nodename = nodename;    
		this.jobID  = jobID;
		this.scanHost = scanHost;
		this.scanAdministrator = scanAdministrator;
		this.dateOfScan = dateOfScan;
		this.timeOfScan = timeOfScan;
		this.repeatScan = repeatScan;
		this.email = email;
		this.status = status;
		this.group = group;
		
	}


     /**
      * Add Getters and Setters
      * @return
      */


	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}
	
	
	public String getScanHost() {
		return scanHost;
	}


	public void setScanHost(String scanHost) {
		this.scanHost = scanHost;
	}

	

	public User getScanAdministrator() {
		return scanAdministrator;
	}

	public void setScanAdministrator(User scanAdministrator) {
		this.scanAdministrator = scanAdministrator;
	}

	public String getDateOfScan() {
		return dateOfScan;
	}

	public void setDateOfScan(String dateOfScan) {
		this.dateOfScan = dateOfScan;
	}


	public String getTimeOfScan() {
		return timeOfScan;
	}


	public void setTimeOfScan(String timeOfScan) {
		this.timeOfScan = timeOfScan;
	}

	

	public boolean isRepeatScan() {
		return repeatScan;
	}

	public void setRepeatScan(boolean repeatScan) {
		this.repeatScan = repeatScan;
	}

	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}



	public long getJobID() {
		return jobID;
	}

	public void setJobID(long jobID) {
		this.jobID = jobID;
	}

	/**
	 * The toString Method
	 */
	public String toString() {
		return "ScanScheduler [nodename=" + nodename + ", jobID=" + jobID + ", scanHost=" + scanHost
				+ ", user=" + scanAdministrator + ", dateOfScan=" + dateOfScan + ", timeOfScan=" + timeOfScan
				+ ", repeatScan=" + repeatScan + ", email=" + email + ", status=" + status + ", group=" + group
				+ "]";
	}

	/**
	 * HashCode
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfScan == null) ? 0 : dateOfScan.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + (int) (jobID ^ (jobID >>> 32));
		result = prime * result + ((nodename == null) ? 0 : nodename.hashCode());
		result = prime * result + (repeatScan ? 1231 : 1237);
		result = prime * result + ((scanHost == null) ? 0 : scanHost.hashCode());
		result = prime * result + ((scanAdministrator == null) ? 0 : scanAdministrator.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((timeOfScan == null) ? 0 : timeOfScan.hashCode());
		return result;
	}

	
	
	/**
	 * Equals Method
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScanScheduler other = (ScanScheduler) obj;
		if (dateOfScan == null) {
			if (other.dateOfScan != null)
				return false;
		} else if (!dateOfScan.equals(other.dateOfScan))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (jobID != other.jobID)
			return false;
		if (nodename == null) {
			if (other.nodename != null)
				return false;
		} else if (!nodename.equals(other.nodename))
			return false;
		if (repeatScan != other.repeatScan)
			return false;
		if (scanHost == null) {
			if (other.scanHost != null)
				return false;
		} else if (!scanHost.equals(other.scanHost))
			return false;
		if (scanAdministrator == null) {
			if (other.scanAdministrator != null)
				return false;
		} else if (!scanAdministrator.equals(other.scanAdministrator))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (timeOfScan == null) {
			if (other.timeOfScan != null)
				return false;
		} else if (!timeOfScan.equals(other.timeOfScan))
			return false;
		return true;
	}



}
