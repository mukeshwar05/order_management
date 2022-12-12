package com.assignment.order_management.dto;


public class ResponsePojo {

	private boolean status;
    private Integer statusCode;
    private String message;
    private Object data;
    
	public ResponsePojo(boolean status, Integer statusCode, String message, Object data) {
		super();
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}
	public ResponsePojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Responsepojo [status=" + status + ", statusCode=" + statusCode + ", message=" + message + ", data="
				+ data + "]";
	}
	
}
