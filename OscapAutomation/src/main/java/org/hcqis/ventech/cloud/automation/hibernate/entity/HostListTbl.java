package org.hcqis.ventech.cloud.automation.hibernate.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the hostlisttbl database table.
 * Author: 	Matthew  Zhao
 * Date: 	03-20-2019
 */
@Entity
@Table(name="hostlisttbl")
@NamedQuery(name="HostListTbl.findAll", query="SELECT s FROM HostListTbl s")
public class HostListTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int hostID;

	private String nodename;

	public HostListTbl() {
	}

	public int getHostID() {
		return this.hostID;
	}

	public void setHostID(int hostID) {
		this.hostID = hostID;
	}

	public String getNodename() {
		return this.nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

}
