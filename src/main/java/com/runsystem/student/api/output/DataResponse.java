package com.runsystem.student.api.output;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DataResponse<T> {
	private String errorMessage;
	private T data;
	private String dateTime;
	
	
	public DataResponse(String errorMessage, T data, String dateTime) {
		super();
		this.errorMessage = errorMessage;
		this.data = data;
		this.setDateTime(dateTime);
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}
