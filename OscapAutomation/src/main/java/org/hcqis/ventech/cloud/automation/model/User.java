package org.hcqis.ventech.cloud.automation.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String firstname;
	private String middlename;
	private String lastname;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String phoneNumber;
	private String emailAddress;
	private String major;
	private List<String> groups;
	
	
	//No Arg Constructor
	//=======================
	public User() {
		
	}

	
   //Over loaded Constructor
   //==========================
	public User(String username, String password, String firstname,
			String middlename, String lastname, String address, String city,
			String state, String zipcode, String phoneNumber,
			String emailAddress, String major, List<String> groups) {
		super();
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.major = major;
		this.groups = groups;
	}

	
	
	// Get and Set Methods
	//====================== 
	public String getFirstname() {
		return firstname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<String> getGroups() {
		return groups;
	}


	public void setGroups(List<String> groups) {
		this.groups = groups;
	}


		//To String Method to format string output
   //========================================
		public String toString() {
			return "User [username=" + username + ", password=" + password
					+ ", firstname=" + firstname + ", middlename=" + middlename
					+ ", lastname=" + lastname + ", address=" + address + ", city="
					+ city + ", state=" + state + ", zipcode=" + zipcode
					+ ", phoneNumber=" + phoneNumber + ", emailAddress="
					+ emailAddress + ", major=" + major + ", group=" + ((groups == null) ? "" :groups.toString()) + "]";
		}


		

		// Equals and HashCode Methods
		//=============================
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((address == null) ? 0 : address.hashCode());
			result = prime * result + ((city == null) ? 0 : city.hashCode());
			result = prime * result
					+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
			result = prime * result
					+ ((firstname == null) ? 0 : firstname.hashCode());
			result = prime * result
					+ ((lastname == null) ? 0 : lastname.hashCode());
			result = prime * result + ((major == null) ? 0 : major.hashCode());
			result = prime * result
					+ ((middlename == null) ? 0 : middlename.hashCode());
			result = prime * result
					+ ((password == null) ? 0 : password.hashCode());
			result = prime * result
					+ ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
			result = prime * result + ((state == null) ? 0 : state.hashCode());
			result = prime * result
					+ ((username == null) ? 0 : username.hashCode());
			result = prime * result
					+ ((zipcode == null) ? 0 : zipcode.hashCode());
			return result;
		}


		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (address == null) {
				if (other.address != null)
					return false;
			} else if (!address.equals(other.address))
				return false;
			if (city == null) {
				if (other.city != null)
					return false;
			} else if (!city.equals(other.city))
				return false;
			if (emailAddress == null) {
				if (other.emailAddress != null)
					return false;
			} else if (!emailAddress.equals(other.emailAddress))
				return false;
			if (firstname == null) {
				if (other.firstname != null)
					return false;
			} else if (!firstname.equals(other.firstname))
				return false;
			if (lastname == null) {
				if (other.lastname != null)
					return false;
			} else if (!lastname.equals(other.lastname))
				return false;
			if (major == null) {
				if (other.major != null)
					return false;
			} else if (!major.equals(other.major))
				return false;
			if (middlename == null) {
				if (other.middlename != null)
					return false;
			} else if (!middlename.equals(other.middlename))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (phoneNumber == null) {
				if (other.phoneNumber != null)
					return false;
			} else if (!phoneNumber.equals(other.phoneNumber))
				return false;
			if (state == null) {
				if (other.state != null)
					return false;
			} else if (!state.equals(other.state))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			if (zipcode == null) {
				if (other.zipcode != null)
					return false;
			} else if (!zipcode.equals(other.zipcode))
				return false;
			return true;
		}
		
}


