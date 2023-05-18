package com.irctc.onlinetrainticketbooking.repsonse;

import org.springframework.stereotype.Component;

@Component
public class ResponseStructure<T> {

	private int statusCode;
	private String msg;
	private String description;
	private T Data;
	
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public T getData() {
		return Data;
	}
	public void setData(T data) {
		Data = data;
	}
	
	
}
