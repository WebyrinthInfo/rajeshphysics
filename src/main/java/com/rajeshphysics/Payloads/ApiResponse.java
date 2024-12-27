package com.rajeshphysics.Payloads;

import java.time.LocalDateTime;



public class ApiResponse {
	private String msg;
    private String status;
    private LocalDateTime timestamp;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public ApiResponse(String msg, String status, LocalDateTime timestamp) {
		super();
		this.msg = msg;
		this.status = status;
		this.timestamp = timestamp;
	}
	public ApiResponse() {
		super();
	}
	
    
}
