package com.assignment.order_management.dto;
import org.springframework.stereotype.Component;

@Component
public class DbPojo {
	private String driverClass;
	private String url;
	private String userName;
	private String password;
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	@Override
	public String toString() {
		return "DbPojo [driverClass=" + driverClass + ", url=" + url + ", userName=" + userName + ", password="
				+ password + "]";
	}
	
	
}
