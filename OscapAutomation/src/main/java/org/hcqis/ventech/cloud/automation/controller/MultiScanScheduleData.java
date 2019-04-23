package org.hcqis.ventech.cloud.automation.controller;

public class MultiScanScheduleData {
	private String nodename;
	private String dateOfScan;
	private String timeOfScan;
	private String group;
	private String email;
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String nodename) {
		this.nodename = nodename;
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
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		String result = "Scan schedule host: " + this.nodename + "\n" 
				+ "Scan schedule date: " + this.dateOfScan + "\n"
				+ "Scan schedule time: " + this.timeOfScan + "\n"
				+ "Scan schedule email: " + this.email + "\n"
				+ "Scan schedule group: " + this.group + "\n";
		return result;
	}

}
