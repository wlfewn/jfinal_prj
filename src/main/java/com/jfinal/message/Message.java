package com.jfinal.message;

public class Message {
	
	private Status status;//状态
	private String message;//消息内容
	
	public Message(){}
	
	public Message(Status status,String message){
		this.status = status;
		this.message = message;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
