package com.springboot.app.controller;

public class RegistrationRequest {
	private Long userCode;
	private String userName;
	private String email;
	private String password;

	public RegistrationRequest(Long userCode, String userName, String email, String password) {
		this.userCode = userCode;
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public Long getUserCode() {
		return userCode;
	}

	public void setUserCode(Long userCode) {
		this.userCode = userCode;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegistrationRequest [userCode=" + userCode + ", userName=" + userName + ", email=" + email
				+ ", password=" + password + "]";
	}

}
