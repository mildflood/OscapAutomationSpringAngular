package org.hcqis.ventech.cloud.automation.hibernate.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the multiscantbl database table.
 * Author: 	Matthew  Zhao
 * Date: 	03-20-2019
 */
@Entity
@Table(name="multiscantbl")
@NamedQuery(name="MultiScanTbl.findAll", query="SELECT s FROM MultiScanTbl s")
public class MultiScanTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int scanID;

	private String firstname;

	private String lastname;

	private String nodename;

	private String scandate;

	private String scanstatus;

	private String scantime;

	public MultiScanTbl() {
	}

	public int getScanID() {
		return this.scanID;
	}

	public void setScanID(int scanID) {
		this.scanID = scanID;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNodename() {
		return this.nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getScandate() {
		return this.scandate;
	}

	public void setScandate(String scandate) {
		this.scandate = scandate;
	}

	public String getScanstatus() {
		return this.scanstatus;
	}

	public void setScanstatus(String scanstatus) {
		this.scanstatus = scanstatus;
	}

	public String getScantime() {
		return this.scantime;
	}

	public void setScantime(String scantime) {
		this.scantime = scantime;
	}

}
