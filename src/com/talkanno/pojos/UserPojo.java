package com.talkanno.pojos;

import java.io.Serializable;

public class UserPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String email;
	private int serial;
	private String password;

	public UserPojo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserPojo [userName=" + userName + ", email=" + email + ", serial=" + serial + ", password=" + password
				+ "]";
	}

}
