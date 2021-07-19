package com.runsystem.student.error;

import java.util.Date;

public class ApiError {
	
	private Integer status;
	private String message;
	private Long timestamp;
	private String path;
	
	
	
	
	public ApiError(Integer status, String message, String path) {
		super();
		this.status = status;
		this.message = message;
		this.timestamp = new Date().getTime();
		this.path = path;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	

}
