  package com.ums.entity;



public class User {
	private int id;
	
	public User(int id,String userName, String password, String emailid, String phoneNumber,String address) {
//		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.emailid = emailid;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	 
	
	
	public User(String userName, String password, String emailid, String phoneNumber, String address) {
//		super();
		this.userName = userName;
		this.password = password;
		this.emailid = emailid;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}



	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}



	private String userName;
	private String password;
	private String emailid;
	private String phoneNumber;
	private String address;

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
}
	