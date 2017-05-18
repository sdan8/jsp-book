package com.book.entity;

import java.util.Map;

public class User {

	int userId;
	String userName;
	String userPassword;
	String userAvatar;
	
	
	public User() {
		super();
	}
	public User(Map<String, Object> map) {
		this.userId = (int) map.get("userId");
		this.userName = (String) map.get("userName");
		this.userPassword = (String) map.get("userPassword");
		this.userAvatar = (String) map.get("userAvatar");
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	
	
}
