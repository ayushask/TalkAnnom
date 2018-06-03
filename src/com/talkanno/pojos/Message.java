package com.talkanno.pojos;

public class Message {

	private int serial;
	private String message;
	private int userSerial;
	
	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(int userSerial) {
		this.userSerial = userSerial;
	}

	@Override
	public String toString() {
		return "Message [serial=" + serial + ", message=" + message + ", userSerial=" + userSerial + "]";
	}

}
