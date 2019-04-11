package hcqis.cloud.automation.model.domain;

import java.io.Serializable;

public class LoginUser extends User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	
	//No arg Constructor
	//===================
	public LoginUser() {
		super();
	}

    
	//Overloaded Constructor
   //========================
	public LoginUser(String username, String password) {
	super();
	this.username = username;
	this.password = password;
    }

    /**
     * Get and Set Methods
     * @return
     */
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


	/**
	 * To String Method
	 */
	public String toString() {
		return "LoginUser [username=" + username + ", password=" + password
				+ "]";
	}


    /**
     * HashCode Method
     */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		LoginUser other = (LoginUser) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}  

}
